package com.mygdx.fantastickworld.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.fantastickworld.Actor.Player;
import com.mygdx.fantastickworld.Main;

public class GameOverState implements Screen {

    private Main main;
    private Texture texture;
    private BitmapFont bitmapFont1, bitmapFont2,bitmapFont3,bitmapFont4;
    private GlyphLayout gl1, gl2,gl3,gl4;

    public GameOverState(Main main) {
        this.main = main;
        texture = new Texture("magma.png");
        bitmapFont1 = new BitmapFont();
        bitmapFont2 = new BitmapFont();
        bitmapFont3 = new BitmapFont();
        bitmapFont4 = new BitmapFont();
        gl1 = new GlyphLayout();
        gl2 = new GlyphLayout();
        gl3 = new GlyphLayout();
        gl4 = new GlyphLayout();
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth() / 15;
        parameter.color = new Color(0.7f, 0.5f, 0.5f, 1);
        FreeTypeFontGenerator fontGenerator2 = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = Gdx.graphics.getWidth() / 25;
        parameter2.color = new Color(0.7f, 0.5f, 0.5f, 1);
        bitmapFont1 = fontGenerator.generateFont(parameter);
        bitmapFont2 = fontGenerator2.generateFont(parameter2);
        bitmapFont3 = fontGenerator2.generateFont(parameter2);
        bitmapFont4 = fontGenerator2.generateFont(parameter2);
        gl1.setText(bitmapFont1, "Game Over");
        gl2.setText(bitmapFont2, "Score: " + GameSc.player.getScore());
        gl3.setText(bitmapFont3, "Time: " + GameSc.second);
        gl4.setText(bitmapFont4, "Tap to start");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        bitmapFont1.draw(Main.batch,gl1,Main.WIDTH / 2 - gl1.width / 2,Main.HEIGHT  - gl1.height);
        bitmapFont2.draw(Main.batch,gl2,Main.WIDTH / 2 - gl2.width / 2,Main.HEIGHT / 2 - gl2.height * 2);
        bitmapFont3.draw(Main.batch,gl3,Main.WIDTH / 2 - gl3.width / 2,Main.HEIGHT / 2 - gl3.height * 3.5f);
        bitmapFont4.draw(Main.batch,gl4,Main.WIDTH / 2 - gl4.width / 2,Main.HEIGHT / 2 - gl4.height * 5);
        Main.batch.end();
        if (Gdx.input.justTouched()){
            main.setScreen(new GameSc(main));
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
        texture.dispose();
        bitmapFont1.dispose();
        bitmapFont2.dispose();
        bitmapFont3.dispose();
        bitmapFont4.dispose();
        main.dispose();
    }
}
