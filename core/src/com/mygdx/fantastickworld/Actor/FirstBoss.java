package com.mygdx.fantastickworld.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.Tools.Animation;
import com.mygdx.fantastickworld.Tools.Circle;
import com.mygdx.fantastickworld.Tools.Point2D;
import com.mygdx.fantastickworld.screen.GameSc;

public class FirstBoss extends Actor {

    private int health;
    private Animation animation;
    private int hit;

    public FirstBoss(Texture img, Point2D position, float speed, float R,int health,Animation animation) {
        super(img, position, speed, R);
        this.health = health;
        bounds = new Circle(position,R);
        direction.setX((float) Math.sin(Math.toRadians(Math.random() * 360)));
        direction.setY((float) Math.cos(Math.toRadians(Math.random() * 360)));
        this.animation = animation;
    }


    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(animation.getFrame(),position.getX() - R,position.getY() - R,R*2,R * 2);
    }

    @Override
    public void update() {
        if (position.getX() + R > 6000) {
            direction.setX(-direction.getX());
            setAnimation(GameSc.enemyAnimationOnLeft);
        }
        if (position.getX() - R < -3000) {
            direction.setX(-direction.getX());
            setAnimation(GameSc.enemyAnimationOnRight);
        }
        if (position.getY() + R > 6000) {
            direction.setY(-direction.getY());
        }
        if (position.getY() - R < -3000) {
            direction.setY(-direction.getY());
        }

        position.add(direction.getX() * speed,direction.getY() * speed);
        bounds.pos.setPoint(position);
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public void hit(){
        this.hit = GameSc.enemy.getHit();
        this.health -= hit;
    }

    public int getHealth() {
        return health;
    }

}