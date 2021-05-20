package com.mygdx.fantastickworld.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.fantastickworld.GraphicsObj.GraphicsObj;
import com.mygdx.fantastickworld.Tools.Circle;
import com.mygdx.fantastickworld.Tools.Point2D;

public abstract class Actor extends GraphicsObj {

    public Point2D position;
    public float speed,R;
    public Circle bounds;
    public Point2D direction;

    public Actor(Texture img, Point2D position, float speed, float R) {
        super(img);
        this.position = new Point2D(position);
        this.speed = speed;
        this.R = R;
        bounds = new Circle(position,R);
        direction = new Point2D(0,0);
    }

    public Actor(Texture img, Point2D position) {
        super(img);
        this.position = new Point2D(position);
        direction = new Point2D(0,0);
    }

    public void setDirection(Point2D dir){
        direction = dir;
    }

    public void setSpeed(float speed) {
        this.speed += speed;
    }

    public float getSpeed() {
        return speed;
    }
}