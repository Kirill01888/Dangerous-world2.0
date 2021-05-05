package com.mygdx.fantastickworld.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.Tools.Circle;
import com.mygdx.fantastickworld.Tools.Point2D;

public class Enemy extends Actor {

    private int health, score, rank;

    public Enemy(Texture img, Point2D position,int rank) {
        super(img, position);
        switch (rank){
            case 1: R = Main.WIDTH / 25;speed = 20;score = health = 50;break;
            case 2: R = Main.WIDTH / 15;speed = 20;score = health = 70;break;
            case 3: R = Main.WIDTH / 10;speed = 20;score = health = 100;break;
            case 4: R = Main.WIDTH / 5;speed = 20;score = health = 140;break;
            case 5: R = Main.WIDTH / 2;speed = 20;score = health = 170;break;
        }
        bounds = new Circle(position,R / 2);
        direction.setX((float) Math.sin(Math.toRadians(Math.random() * 360)));
        direction.setY((float) Math.cos(Math.toRadians(Math.random() * 360)));
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX() - R,position.getY() - R,R*2,R * 2);
    }

    @Override
    public void update() {
        if (position.getX() + R > 3000) direction.setX(-direction.getX());
        if (position.getX() - R < -50) direction.setX(-direction.getX());
        if (position.getY() + R > 3000) direction.setY(-direction.getY());
        if (position.getY() - R < -50) direction.setY(-direction.getY());

        position.add(direction.getX() * speed,direction.getY() * speed);
        bounds.pos.setPoint(position);
    }

    public void hit(){
        health--;
    }
    public int getHealth(){
        return health;
    }
}
