package com.mygdx.fantastickworld.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.Tools.Point2D;

public class Bullet extends  Actor {


    public boolean isOut;

    public Bullet(Texture img, Point2D position, float speed, float R,Point2D direction) {
        super(img, position, speed, R);
        this.direction = new Point2D(direction);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX() - R,position.getY() - R,R , R );
    }

    @Override
    public void update() {
        isOut = ((position.getX() - R > 6000 || position.getX() + R < -3000) || (position.getY() - R > 6000) || (position.getY() + R < -3000)) ? true:false;
        position.add(direction.getX() * speed, direction.getY() * speed);
        bounds.pos.setPoint(position);
    }


}