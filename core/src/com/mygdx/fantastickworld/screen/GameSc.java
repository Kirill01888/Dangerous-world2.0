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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.fantastickworld.Actor.Bullet;
import com.mygdx.fantastickworld.Actor.Enemy;
import com.mygdx.fantastickworld.Actor.Player;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.Tools.Animation;
import com.mygdx.fantastickworld.Tools.BulletGenerator;
import com.mygdx.fantastickworld.Tools.Joystick;
import com.mygdx.fantastickworld.Tools.Joystick2;
import com.mygdx.fantastickworld.Tools.Point2D;
import com.mygdx.fantastickworld.Tools.Wave;
import com.mygdx.fantastickworld.states.GameOverState;
import com.mygdx.fantastickworld.states.GameStateManager;
import com.mygdx.fantastickworld.states.MenuState;
import com.mygdx.fantastickworld.states.State;

public class GameSc extends State implements Screen {

    public static Joystick joystick;
    public static Joystick2 joystick2;
    public static Player player;
    public static Main main;
    public static com.badlogic.gdx.utils.Array<Bullet> bullets;
    public static BulletGenerator bulgen;
    public static Array<Enemy> enemies;
    public static Wave wave;
    public static GameStateManager gameStateManager;
    private BitmapFont scoreFont, healthFont, timeFont;
    private GlyphLayout glyphLayout1, glyphLayout2, gl3;
    private FreeTypeFontGenerator fontGenerator;
    private long startTime;
    private int second;
    private Animation WizardWalkOnRightAnimation;
    private Sound fireSound;
   // private Array<Player> playerArray;

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
        GameUpdate();
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer) {
        for (int i = 0; i < 10; i++) {
            joystick.update(x, y, isDownTouch, pointer);
            joystick2.update2(x, y, isDownTouch, pointer);
        }
    }

    public void loadActors() {
        gameStateManager = new GameStateManager();
        bulgen = new BulletGenerator();
        enemies = new Array<>();
        bullets = new Array<>();
        fireSound = Gdx.audio.newSound(Gdx.files.internal("sound_3964d.mp3"));
        player = new Player(Main.WalkOnBot1, new Point2D(Main.WIDTH/2,Main.HEIGHT/2), 10, Main.HEIGHT / 10, 20, 0);
        //WizardWalkOnRight = new TextureAtlas("WizardWalkOnRight.atlas");
        //WizardWalkOnRightAnimation = new Animation(1 / 30f, WizardWalkOnRight.getRegions());
        joystick = new Joystick(Main.circle, Main.actor, Main.HEIGHT / 3, player);
        joystick2 = new Joystick2(Main.circle, Main.actor, Main.HEIGHT / 3, player);
        wave = new Wave(5, 1, 5);
        startTime = System.currentTimeMillis();
        scoreFont = new BitmapFont();
        healthFont = new BitmapFont();
        timeFont = new BitmapFont();
        glyphLayout1 = new GlyphLayout();
        glyphLayout2 = new GlyphLayout();
        gl3 = new GlyphLayout();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth() / 25;
        parameter.color = new Color(0.7f, 0.5f, 0.5f, 1);
        scoreFont = fontGenerator.generateFont(parameter);
        healthFont = fontGenerator.generateFont(parameter);
        timeFont = fontGenerator.generateFont(parameter);
        gameStateManager.push(new MenuState(gameStateManager));
    }

    public void GameUpdate() {
        player.setDirection(joystick.getDir());
        player.update();
        bulgen.update(joystick2);
        /*if (player.getHealth() < 1){
            gameStateManager.set(new GameOverState(gameStateManager));
        }*/
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
        collision();
        wave.update();
    }

    public void GameRender(SpriteBatch batch) {
        player.draw(batch);
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).draw(batch);
        }
        for (int i = 0; i < enemies.size; i++) {
            enemies.get(i).draw(batch);
        }
        joystick.draw(batch, player);
        joystick2.draw2(batch, player);
        //timePassedWWOR += Gdx.graphics.getDeltaTime();
        second = (int) ((System.currentTimeMillis() - startTime) / 1000);
        scoreFont.draw(batch, glyphLayout1, player.position.getX(), player.position.getY() + 500);
        healthFont.draw(batch, glyphLayout2, player.position.getX() - Gdx.graphics.getWidth() / 2, player.position.getY() + 500);
        timeFont.draw(batch, gl3, player.position.getX() + 900, player.position.getY() + 500);
        gl3.setText(timeFont, second + "");
        glyphLayout1.setText(scoreFont, player.getScore() + "");
        glyphLayout2.setText(healthFont, player.getHealth() + "");
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);

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
        for (int i = 0; i < enemies.size; i++) {
            if (player.bounds.Overlaps(enemies.get(i).bounds)) {
                player.setHealth(0.5f);
            }
        }
    }

   /* public void playerSideUpdate(Joystick joystick) {
        if (joystick.getStickBounds().pos.x > 240.5) {
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.WalkOnRight1, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
        if (joystick.getStickBounds().pos.x < 240.5) {
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.WalkOnLeft1, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
        if (joystick.getStickBounds().pos.y < 181.75) {
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.WalkOnBot1, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
        if (joystick.getStickBounds().pos.y > 181.75) {
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.WalkOnTop1, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
        if (joystick.getStickBounds().pos.y < 181.75 && joystick.getStickBounds().pos.x < 240.5){
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.walk2, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
        if (joystick.getStickBounds().pos.y < 181.75 && joystick.getStickBounds().pos.x > 240.5){
            playerArray.removeIndex(0);
            playerArray.add(player = new Player(Main.walk1, new Point2D(Main.WIDTH / 2, Main.HEIGHT / 2), 10, Main.HEIGHT / 20, 20, 0));
            playerArray.get(0).update();
        }
    }*/

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
    protected void handleInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void dispose() {
        player.dispose();
        joystick.dispose();
        joystick2.dispose();
        main.dispose();
        bulgen.dispose();
        gameStateManager.dispose();
        healthFont.dispose();
        scoreFont.dispose();
        timeFont.dispose();
        fontGenerator.dispose();
    }
}