package com.mygdx.fantastickworld.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.fantastickworld.Actor.Actor;
import com.mygdx.fantastickworld.Actor.Player;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.screen.GameSc;

public class Joystick {

    private static Texture CircleImg, StickImg;
    private static Circle CircleBounds, StickBounds;
    private static float Rcircle, Rstick;
    private Point2D direction;
    private int pointer = -1;
    private Point2D point2D;

    public Joystick(Texture cimg, Texture simg, float Size, Player player) {
        CircleImg = cimg;
        StickImg = simg;
        Rcircle = Size / 2;
        Rstick = Rcircle / 2;
        CircleBounds = new Circle(new Point2D(player.position.x / 4,player.position.y / 4 + 50), Rcircle);
        StickBounds = new Circle(new Point2D(player.position.x / 4,player.position.y / 4 + 50), Rstick);
        direction = new Point2D(0, 0);
    }

    public void draw(SpriteBatch batch,Player player) {
        batch.draw(CircleImg, player.position.x - 800 - Rcircle , player.position.y - 375 - Rcircle , Rcircle * 2, Rcircle * 2);
        batch.draw(StickImg, player.position.x - 900 - (Rstick * 3) + StickBounds.pos.getX() , player.position.y - 375 - (Rstick * 3) + StickBounds.pos.getY(), Rstick * 2, Rstick * 2);
    }

    public void update(float x, float y, boolean isDownTouch, int pointer) {
        Point2D touch = new Point2D(x, y);
        if (CircleBounds.isContains(touch) && isDownTouch && this.pointer == -1)
            this.pointer = pointer;
        if (CircleBounds.Overlaps(StickBounds) && isDownTouch && pointer == this.pointer)
            atControl(new Point2D(x, y));
        if ((!isDownTouch && pointer == this.pointer) || (isDownTouch && pointer == this.pointer && !CircleBounds.Overlaps(StickBounds)))
            returnStick();
    }

    public void atControl(Point2D point) {
        StickBounds.pos.setPoint(point);
        float dx = CircleBounds.pos.getX() - StickBounds.pos.getX();
        float dy = CircleBounds.pos.getY() - StickBounds.pos.getY();
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        direction.setPoint(-(dx / dist), -(dy / dist));
    }

    public void returnStick() {
        StickBounds.pos.setPoint(CircleBounds.pos);
        direction.setPoint(0, 0);
        pointer = -1;
    }

    public Point2D getDir() {
        return direction;
    }

}