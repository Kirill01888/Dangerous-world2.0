package com.mygdx.fantastickworld;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

	@Override
	public void create () {
		HEIGHT = Gdx.graphics.getHeight();
		WIDTH = Gdx.graphics.getWidth();
		batch = new SpriteBatch();
		place2 = new Texture("plase2.png");
		enemy = new Texture("monster.png");
		bullet = new Texture("fire-ball.png");
		mage = new Texture("WizardWalkOnRight.png");
		WalkOnLeft1 = new Texture("WalkOnLeft1.png");
		WalkOnRight1 = new Texture("Sprites_wizard2.2.png");
		WalkOnBot1 = new Texture("WalkOnBot1.png");
		WalkOnTop1 = new Texture("WalkOnBot1.png");
		walk1 = new Texture("walk1.png");
		walk2 = new Texture("walk2.png");
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
		WalkOnTop1.dispose();
		walk2.dispose();
		walk1.dispose();
		WalkOnBot1.dispose();
		WalkOnLeft1.dispose();
		WalkOnRight1.dispose();
	}
}
