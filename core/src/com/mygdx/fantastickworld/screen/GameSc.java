package com.mygdx.fantastickworld.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fantastickworld.Actor.Bullet;
import com.mygdx.fantastickworld.Actor.Enemy;
import com.mygdx.fantastickworld.Actor.FirstBoss;
import com.mygdx.fantastickworld.Actor.Player;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.PowerUps.AttackBonus;
import com.mygdx.fantastickworld.PowerUps.AttackBonusGenerator;
import com.mygdx.fantastickworld.PowerUps.HealthBonus;
import com.mygdx.fantastickworld.PowerUps.HealthBonusGenerator;
import com.mygdx.fantastickworld.PowerUps.SpeedBonus;
import com.mygdx.fantastickworld.PowerUps.SpeedBonusGenerator;
import com.mygdx.fantastickworld.Tools.Animation;
import com.mygdx.fantastickworld.Tools.BulletGenerator;
import com.mygdx.fantastickworld.Tools.Joystick;
import com.mygdx.fantastickworld.Tools.Joystick2;
import com.mygdx.fantastickworld.Tools.Point2D;
import com.mygdx.fantastickworld.Tools.Wave;

public class GameSc implements Screen {

    public static Joystick joystick;
    public static Joystick2 joystick2;
    public static Player player;
    public Main main;
    public static com.badlogic.gdx.utils.Array<Bullet> bullets;
    public static BulletGenerator bulgen;
    public static Array<Enemy> enemies;
    public static Array<HealthBonus> healthBonuses;
    public static Array<SpeedBonus> speedBonus;
    public static Array<AttackBonus> attackBonuses;
    public static Wave wave;
    private BitmapFont scoreFont, healthFont, timeFont,attackFont,speedFont;
    private GlyphLayout glyphLayout1, glyphLayout2, gl3,attackGl,speedGl;
    private FreeTypeFontGenerator fontGenerator;
    public static int seconds,minutes;
    private Sound fireSound;
    public static Animation animationWalkOnRight, animationWalkOnLeft, enemyAnimationOnRight, enemyAnimationOnLeft, animation;
    public HealthBonusGenerator healthBonusGenerator;
    public SpeedBonusGenerator speedBonusGenerator;
    public AttackBonusGenerator attackBonusGenerator;
    public static Enemy enemy;
    public static Array<FirstBoss> bossesArray;
    private long startTime = System.currentTimeMillis();

    public GameSc(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int) screenX, (int) screenY, true, pointer);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int) screenX, (int) screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.HEIGHT - screenY;
                multitouch((int) screenX, (int) screenY, true, pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });
        loadActors();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        drawMap(Main.batch);
        GameRender(Main.batch);
        Main.batch.end();
        Main.camera.update();
        Main.batch.setProjectionMatrix(Main.camera.combined);
        CameraUpdate();
        GameUpdate(delta);
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer) {
        for (int i = 0; i < 10; i++) {
            joystick.update(x, y, isDownTouch, pointer);
            joystick2.update2(x, y, isDownTouch, pointer);
        }
    }

    public void loadActors() {
        bulgen = new BulletGenerator();
        enemies = new Array<>();
        bullets = new Array<>();
        bossesArray = new Array<>();
        enemy = new Enemy(Main.enemy, new Point2D(300 + (float) (Math.random() * 2000), 200 + (float) (Math.random() * 2000)), 1, GameSc.enemyAnimationOnRight);
        attackBonuses = new Array<>();
        attackBonusGenerator = new AttackBonusGenerator(60);
        speedBonus = new Array<>();
        speedBonusGenerator = new SpeedBonusGenerator(60);
        healthBonuses = new Array<>();
        healthBonusGenerator = new HealthBonusGenerator(30);
        fireSound = Gdx.audio.newSound(Gdx.files.internal("sound_3964d.mp3"));
        animation = new Animation(new TextureRegion(Main.animation), 4, 10f);
        enemyAnimationOnLeft = new Animation(new TextureRegion(Main.monsterWalkOnLeft), 10, 1f);
        enemyAnimationOnRight = new Animation(new TextureRegion(Main.monsterWalkOnRight), 10, 1f);
        animationWalkOnRight = new Animation(new TextureRegion(Main.wizardWalkOnRight), 5, 0.5f);
        animationWalkOnLeft = new Animation(new TextureRegion(Main.wizardWalkOnLeft), 5, 0.5f);
        player = new Player(null, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 15, Main.HEIGHT / 10, 200, 0, animationWalkOnRight);
        joystick = new Joystick(Main.circle, Main.actor, Main.HEIGHT / 3, player);
        joystick2 = new Joystick2(Main.circle, Main.actor, Main.HEIGHT / 3, player);
        wave = new Wave(1, 1, 1);
        scoreFont = new BitmapFont();
        healthFont = new BitmapFont();
        timeFont = new BitmapFont();
        attackFont = new BitmapFont();
        speedFont = new BitmapFont();
        glyphLayout1 = new GlyphLayout();
        glyphLayout2 = new GlyphLayout();
        gl3 = new GlyphLayout();
        attackGl = new GlyphLayout();
        speedGl = new GlyphLayout();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.color = new Color(0.7f, 0.5f, 0.5f, 1);
        scoreFont = fontGenerator.generateFont(parameter);
        healthFont = fontGenerator.generateFont(parameter);
        timeFont = fontGenerator.generateFont(parameter);
        attackFont = fontGenerator.generateFont(parameter);
        speedFont = fontGenerator.generateFont(parameter);
    }

    public void GameUpdate(float delta) {
        player.setDirection(joystick.getDir());
        player.update();
        bulgen.update(joystick2);
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update();
            if (bullets.get(i).isOut) {
                bullets.removeIndex(i--);
            }
        }
        for (int i = 0; i < enemies.size; i++) {
            enemies.get(i).update();
            if (enemies.get(i).getHealth() < 1) {
                enemies.removeIndex(i);
            }
        }
        for (int i = 0; i < bossesArray.size; i++) {
            bossesArray.get(i).update();
            if (bossesArray.get(i).getHealth() < 1){
                bossesArray.removeIndex(i);
            }
        }
        collision();
        wave.update();
        if (player.getHealth() < 1) {
            String score = Integer.toString(player.getScore());
            String timeM = Integer.toString(minutes);
            String timeS = Integer.toString(seconds);
            main.setScreen(new GameOverState(main,score,timeS,timeM));
        }
        playerSideUpdate(joystick);
        animation.update(delta);
        animationWalkOnRight.update(delta);
        animationWalkOnLeft.update(delta);
        enemyAnimationOnRight.update(delta);
        enemyAnimationOnLeft.update(delta);
        healthBonusGenerator.update();
        speedBonusGenerator.update();
        attackBonusGenerator.update();
    }

    public void GameRender(SpriteBatch batch) {
        player.draw(batch);
        for (int i = 0; i < bossesArray.size; i++) {bossesArray.get(i).draw(batch);}
        for (int i = 0; i < bullets.size; i++) {bullets.get(i).draw(batch);}
        for (int i = 0; i < enemies.size; i++) {enemies.get(i).draw(batch);}
        for (int i = 0; i < healthBonuses.size; i++) {healthBonuses.get(i).draw(batch);}
        for (int i = 0; i < speedBonus.size; i++) {speedBonus.get(i).draw(batch);}
        for (int i = 0; i < attackBonuses.size; i++) {attackBonuses.get(i).draw(batch);}
        joystick.draw(batch, player);
        joystick2.draw2(batch, player);
        long totalTime = (startTime - System.currentTimeMillis()) / 1000;
        minutes = (int) (totalTime / 60);
        seconds = (int) (totalTime % 60);
        gl3.setText(timeFont, -minutes + ":" + -seconds);
        scoreFont.draw(batch, glyphLayout1, player.position.getX(), player.position.getY() + 500);
        healthFont.draw(batch, glyphLayout2, player.position.getX() + 850, player.position.getY() + 500);
        timeFont.draw(batch, gl3, player.position.getX() - 1100, player.position.getY() + 500);
        attackFont.draw(batch,attackGl,player.position.getX() + 850,player.position.getY() + 400);
        speedFont.draw(batch,speedGl,player.position.getX() + 850,player.position.getY() + 300);
        gl3.setText(timeFont, seconds + "");
        glyphLayout1.setText(scoreFont, player.getScore() + "");
        glyphLayout2.setText(healthFont, (int)(player.getHealth()) + "");
        attackGl.setText(attackFont,"atc:" + enemy.getHit());
        speedGl.setText(speedFont,"spd:" + (int)(player.getSpeed()));
    }

    public void CameraUpdate() {
        Vector3 position = Main.camera.position;
        position.x = player.position.x;
        position.y = player.position.y;
        Main.camera.position.set(position);
        Main.camera.update();
    }

    public void drawMap(SpriteBatch batch) {
        batch.draw(Main.place, -3000, 0, 3000, 3000);
        batch.draw(Main.place, 3000, 0, 3000, 3000);
        batch.draw(Main.place, -3000, -3000, 3000, 3000);
        batch.draw(Main.place, 0, -3000, 3000, 3000);
        batch.draw(Main.place, -3000, 3000, 3000, 3000);
        batch.draw(Main.place, 0, 3000, 3000, 3000);
        batch.draw(Main.place, 3000, 3000, 3000, 3000);
        batch.draw(Main.place, 3000, -3000, 3000, 3000);
        batch.draw(Main.place2, 0, 0, 3000, 3000);
    }

    public void collision() {
        for (int i = 0; i < attackBonuses.size; i++) {
            if (player.bounds.Overlaps(attackBonuses.get(i).getBounds())){
                enemy.setHit(1);
                attackBonuses.removeIndex(i);
            }
        }
        for (int i = 0; i < bullets.size; i++) {
            for (int j = 0; j < enemies.size; j++) {
                if (bullets.get(i).bounds.Overlaps(enemies.get(j).bounds)) {
                    enemies.get(j).hit();
                    player.setScore(1);
                    bullets.removeIndex(i--);
                    break;
                }
            }
        }
        for (int i = 0; i < bullets.size; i++) {
            for (int j = 0; j < bossesArray.size; j++) {
                if (bullets.get(i).bounds.Overlaps(bossesArray.get(j).bounds)) {
                    bossesArray.get(j).hit();
                    player.setScore(1);
                    bullets.removeIndex(i--);
                    break;
                }
            }
        }
        for (int i = 0; i < bossesArray.size; i++) {
            if (player.bounds.Overlaps(bossesArray.get(i).bounds)){
                player.minusHealth(1);
            }
        }
        for (int i = 0; i < enemies.size; i++) {
            if (player.bounds.Overlaps(enemies.get(i).bounds)) {
                player.minusHealth(1);
            }
        }
        for (int i = 0; i < healthBonuses.size; i++) {
            if (player.bounds.Overlaps(healthBonuses.get(i).getBounds())) {
                player.addHealth(40);
                healthBonuses.removeIndex(i);
            }
        }
        for (int i = 0; i < speedBonus.size; i++) {
            if (player.bounds.Overlaps(speedBonus.get(i).getBounds())) {
                player.setSpeed(1);
                speedBonus.removeIndex(i);
            }
        }
    }

    public void playerSideUpdate(Joystick joystick) {
        if (joystick.getDir().x > 0) {
            player.setAnimation(animationWalkOnRight);
        }
        if (joystick.getDir().x < 0) {
            player.setAnimation(animationWalkOnLeft);
        }
        if (joystick.getDir().x == 0 && joystick.getDir().y == 0) {
            player.setAnimation(animation);
        }
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        player.dispose();
        joystick.dispose();
        joystick2.dispose();
        main.dispose();
        bulgen.dispose();
        healthFont.dispose();
        scoreFont.dispose();
        timeFont.dispose();
        fontGenerator.dispose();
        main.dispose();
        animationWalkOnRight.dispose();
        fireSound.dispose();
    }
}