package com.mygdx.fantastickworld.Tools;

import com.mygdx.fantastickworld.Actor.Bullet;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.screen.GameSc;

public class BulletGenerator {
    public boolean isFire;

    public  void  update(Joystick2 joy){
        isFire = (joy.getDir().getX() == 0 && joy.getDir().getY() == 0)?false:true;

        if (isFire) {
            GameSc.bullets.add(new Bullet(Main.bullet, new Point2D(GameSc.player.position.getX(), GameSc.player.position.getY() ), 35, GameSc.player.R, joy.getDir()));
        }
    }

    public void dispose() {
        GameSc.bulgen.dispose();
    }
}