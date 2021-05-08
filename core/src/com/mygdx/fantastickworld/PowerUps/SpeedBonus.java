package com.mygdx.fantastickworld.PowerUps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.Tools.Circle;
import com.mygdx.fantastickworld.Tools.Point2D;

public class SpeedBonus {

    private Texture texture;
    private Point2D position;
    private Circle bounds;
    private float R;

    public SpeedBonus(Texture texture, Point2D position, float R) {
        this.texture = texture;
        this.position = position;
        this.bounds = new Circle(position,R);
        this.R = R;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,position.getX() - R,position.getY() - R,75,75);
    }

    public Circle getBounds() {
        return bounds;
    }
}
