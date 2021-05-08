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

    public Player(Texture img, Point2D position, float speed, float R, float health,int score,Animation animation) {
        super(img, position, speed, R);
        this.health = health;
        this.score = score;
        this.animation = animation;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getFrame(), position.getX() - R, position.getY() - R, 95, 150);
    }

    @Override
    public void update() {
        if (position.getX() + R > 3000) position.setX(3000 - R);
        if (position.getX() - R < 0) position.setX(R);
        if (position.getY() + R > 3000) position.setY(3000 - R);
        if (position.getY() - R < 0) position.setY(R);
        position.add(direction.getX() * speed, direction.getY() * speed);
        bounds.pos.setPoint(position);
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

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }
}
