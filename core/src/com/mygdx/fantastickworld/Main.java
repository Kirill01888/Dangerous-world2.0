package com.mygdx.fantastickworld;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.fantastickworld.screen.GameSc;
import com.mygdx.fantastickworld.screen.MenuState;

public class Main extends Game {

    public static Main main;
    public static SpriteBatch batch;
    public static int HEIGHT, WIDTH;
    public static Texture circle, actor;
    public static OrthographicCamera camera;
    public static Texture place, bullet, enemy, place2,
            monsterWalkOnRight, monsterWalkOnLeft, wizardWalkOnLeft,
            wizardWalkOnRight, animation, healthBonus,speedBonus,attackBonus;
    private Music backgroundMusic;
    public static int Record,TimeRecord,recordMin,recordSec;

    @Override
    public void create() {
        if (!Gdx.files.local("rec.txt").exists())Write("0");
        Record = Read();
        if (!Gdx.files.local("TRec.txt").exists())Write2("0");
        TimeRecord = Read2();
        recordMin = TimeRecord / 60;
        recordSec = TimeRecord % 60;
        main = new Main();
        HEIGHT = Gdx.graphics.getHeight();
        WIDTH = Gdx.graphics.getWidth();
        batch = new SpriteBatch();
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("b19fd19cd041148.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.play();
        attackBonus = new Texture("attackBonus.png");
        speedBonus = new Texture("wing.png");
        healthBonus = new Texture("healthBonus.png");
        animation = new Texture("animation.png");
        wizardWalkOnLeft = new Texture("wizardWalkOnLeft.png");
        wizardWalkOnRight = new Texture("wizardWalkOnRight.png");
        place2 = new Texture("place2.png");
        enemy = new Texture("monster.png");
        bullet = new Texture("fire-ball.png");
        monsterWalkOnLeft = new Texture("monsterWalkOnLeft.png");
        monsterWalkOnRight = new Texture("monsterWalkOnRight.png");
        place = new Texture("magma.png");
        circle = new Texture("Circle.png");
        actor = new Texture("Stick.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        int record = Read2();
        setScreen(new MenuState(this));
        Gdx.app.log("Log", "" + record);
    }

    @Override
    public void dispose() {
        batch.dispose();
        circle.dispose();
        actor.dispose();
        place.dispose();
        bullet.dispose();
        enemy.dispose();
        place2.dispose();
        backgroundMusic.dispose();
    }

    public static void Write(String string){
        FileHandle fileHandle = Gdx.files.local("rec.txt");
        fileHandle.writeString(string,false);
    }

    public static int Read(){
        FileHandle fileHandle = Gdx.files.local("rec.txt");
        return Integer.parseInt(fileHandle.readString());
    }

    public static void Write2(String string2){
        FileHandle fileHandle2 = Gdx.files.local("TRec.txt");
        fileHandle2.writeString(string2,false);
    }

    public static int Read2(){
        FileHandle fileHandle2 = Gdx.files.local("TRec.txt");
        return Integer.parseInt(fileHandle2.readString());
    }
}
