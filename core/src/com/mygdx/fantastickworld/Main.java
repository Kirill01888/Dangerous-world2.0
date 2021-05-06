package com.mygdx.fantastickworld;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.screen.GameSc;

public class Main extends Game {
	public static SpriteBatch batch;
	public static int HEIGHT,WIDTH;
	public static Texture circle, actor;
	public static OrthographicCamera camera;
	public static Texture mage;
	public static Texture place,bullet,enemy,place2, WalkOnRight1,WalkOnLeft1,WalkOnTop1,WalkOnBot1,walk1,walk2;
	private Music backgroundMusic;

	@Override
	public void create () {
		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b19fd19cd041148.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.2f);
		backgroundMusic.play();
		place2 = new Texture("place2.png");
		enemy = new Texture("monster.png");
		bullet = new Texture("fire-ball.png");
		WalkOnBot1 = new Texture("WalkOnBot1.png");
		place = new Texture("magma.png");
		circle = new Texture("Circle.png");
		actor = new Texture("Stick.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		setScreen(new GameSc(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		circle.dispose();
		actor.dispose();
		mage.dispose();
		place.dispose();
		bullet.dispose();
		enemy.dispose();
		place2.dispose();
		WalkOnBot1.dispose();
		backgroundMusic.dispose();
	}
}
