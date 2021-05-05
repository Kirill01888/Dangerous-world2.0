package com.mygdx.fantastickworld.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.Tools.Animation;
import com.mygdx.fantastickworld.Tools.Point2D;
import com.mygdx.fantastickworld.screen.GameSc;

import java.awt.Rectangle;

public class Player extends Actor {

    private int score;
    private float health;
    private Animation animation;
    private Rectangle rectangle;
    private Texture texture;

    public Player(Texture img, Point2D position, float speed, float R, float health,int score) {
        super(img, position, speed, R);
        Point2D point2D = position;
        texture = new Texture("Sprites_wizard2.2.png");
        animation = new Animation(new TextureRegion(img),5,1f);
        rectangle = new Rectangle(Main.WalkOnRight1.getWidth() / 5,Main.WalkOnRight1.getHeight());
        this.health = health;
        this.score = score;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.getX() - R, position.getY() - R, rectangle.width * 5, rectangle.height * 5);
    }

    @Override
    public void update() {
        animation.update(0.5f);
        if (position.getX() + R > 3000) position.setX(3000 - R);
        if (position.getX() - R < 0) position.setX(R);
        if (position.getY() + R > 3000) position.setY(3000 - R);
        if (position.getY() - R < 0) position.setY(R);
        position.add(direction.getX() * speed, direction.getY() * speed);
        bounds.pos.setPoint(position);
    }

    public void isDeath() {
        health = 0;
    }

    public int getScore() {
        return score;
    }

    public float getHealth() {
        return health;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void setHealth(float health) {
        this.health -= health;
    }

    public void dispose() {
        GameSc.player.dispose();
    }


}
