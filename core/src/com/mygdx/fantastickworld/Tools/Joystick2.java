package com.mygdx.fantastickworld.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.fantastickworld.Actor.Player;
import com.mygdx.fantastickworld.screen.GameSc;

public class Joystick2 {

    private static Texture CircleImg, StickImg;
    private static Circle CircleBounds, StickBounds;
    private static float Rcircle, Rstick;
    private Point2D direction;
    private int pointer = -1;

    public Joystick2(Texture cimg, Texture simg, float Size,Player player) {
        CircleImg = cimg;
        StickImg = simg;
        Rcircle = Size / 2;
        Rstick = Rcircle / 2;
        CircleBounds = new Circle(new Point2D( player.position.x / 4 + 800 * 2,player.position.y / 4 ), Rcircle);
        StickBounds = new Circle(new Point2D( player.position.x / 4 + 800 * 2 + 25,player.position.y / 4 + 50), Rstick);
        direction = new Point2D(0, 0);
    }

    public void draw2(SpriteBatch batch, Player player){
        batch.draw(CircleImg, player.position.x  + 750 - Rcircle, player.position.y - 378  - Rcircle , Rcircle * 2, Rcircle * 2);
        batch.draw(StickImg, player.position.x  + 760 * 3  + (Rstick * 2) - StickBounds.pos.getX() + 100, player.position.y - 100 - Rstick * 2 - StickBounds.pos.getY() - 25, Rstick * 2, Rstick * 2);

    }

    public void update2(float x, float y, boolean isDownTouch, int pointer) {
        Point2D touch = new Point2D(x, y);
        if (CircleBounds.isContains(touch) && isDownTouch && this.pointer == -1)
            this.pointer = pointer;
        if (CircleBounds.Overlaps(StickBounds) && isDownTouch && pointer == this.pointer)
            atControl2(new Point2D(x, y));
        if ((!isDownTouch && pointer == this.pointer) || (isDownTouch && pointer == this.pointer && !CircleBounds.Overlaps(StickBounds)))
            returnStick();
    }

    public void atControl2(Point2D point) {
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

    public void dispose() {
        GameSc.joystick2.dispose();
    }
}