package com.mygdx.fantastickworld.states;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.screen.GameSc;

public class GameOverState extends State {

    private Texture texture;
    private BitmapFont bitmapFont1, bitmapFont2;
    private GlyphLayout gl1, gl2;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        Main.camera.setToOrtho(false,Main.WIDTH / 2, Main.HEIGHT / 2);
        texture = new Texture("place2.png");
        bitmapFont1 = new BitmapFont();
        bitmapFont2 = new BitmapFont();
        gl1 = new GlyphLayout();
        gl2 = new GlyphLayout();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(new GameSc(GameSc.main));
        }
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(Main.camera.combined);
        batch.draw(texture,0,0);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
