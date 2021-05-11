package com.mygdx.fantastickworld.PowerUps;

import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.Tools.Point2D;
import com.mygdx.fantastickworld.screen.GameSc;

public class AttackBonusGenerator {

    private int delay;
    private long startTimer;

    public AttackBonusGenerator(int delay) {
        this.delay = delay;
    }

    public void update(){
        if (GameSc.attackBonuses.size == 0 && startTimer == 0){
            startTimer = System.currentTimeMillis();
        }
        int seconds = 0;
        if (startTimer > 0) {
            seconds = (int) ((System.currentTimeMillis() - startTimer) / 1000);
        }
        if (seconds >= delay){
            GameSc.attackBonuses.add(new AttackBonus(Main.attackBonus,new Point2D((float) (Math.random() * 3000),(float) (Math.random() * 3000)),50f));
            startTimer = 0;
            seconds = 0;
        }
    }
}
