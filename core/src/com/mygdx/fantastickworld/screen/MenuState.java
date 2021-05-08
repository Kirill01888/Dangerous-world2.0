package com.mygdx.fantastickworld.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.fantastickworld.Main;

public class MenuState implements Screen {

    private Main main;
    private Texture backGround;
    private BitmapFont bitmapFont1, bitmapFont2;
    private GlyphLayout gl1, gl2;

    public MenuState(Main main) {
        this.main = main;
        backGround = new Texture("place2.png");
        bitmapFont1 = new BitmapFont();
        bitmapFont2 = new BitmapFont();
        gl1 = new GlyphLayout();
        gl2 = new GlyphLayout();
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getWidth() / 15;
        parameter.color = new Color(0.7f, 0.5f, 0.5f, 1);
        FreeTypeFontGenerator fontGenerator2 = new FreeTypeFontGenerator(Gdx.files.internal("a SignboardCpsNr BoldItalic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = Gdx.graphics.getWidth() / 25;
        parameter2.color = new Color(0.7f, 0.5f, 0.5f, 1);
        bitmapFont2 = fontGenerator2.generateFont(parameter2);
        bitmapFont1 = fontGenerator.generateFont(parameter);
        gl1.setText(bitmapFont1, "Dangerous world");
        gl2.setText(bitmapFont2, "Tap to start");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Main.batch.begin();
        Main.batch.draw(backGround, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bitmapFont1.draw(Main.batch, gl1, Gdx.graphics.getWidth() / 2 - gl1.width / 2, Gdx.graphics.getHeight() - gl1.height);
        bitmapFont2.draw(Main.batch, gl2, Gdx.graphics.getWidth() / 2 - gl2.width / 2, Gdx.graphics.getHeight() / 4);
        Main.batch.end();
        if (Gdx.input.justTouched()) {
            main.setScreen(new GameSc(main));
        }
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause(){}
    @Override
    public void resume(){}
    @Override
    public void hide(){}

    @Override
    public void dispose() {
        backGround.dispose();
        bitmapFont2.dispose();
        bitmapFont1.dispose();
        main.dispose();
    }
}
