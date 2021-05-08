package com.mygdx.fantastickworld.Tools;

import com.mygdx.fantastickworld.Actor.Enemy;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.screen.GameSc;

public class Wave {

    private int delay,minEnemy;
    private long startTimer;
    private String str = "wave -";
    private int waveNumber;

    public Wave(int delay,int waveNumber,int minEnemy){
        this.delay = delay;
        this.waveNumber = waveNumber;
        this.minEnemy = minEnemy;
    }

    public void update(){
        if (GameSc.enemies.size == 0 && startTimer == 0){
            startTimer = System.currentTimeMillis();
        }
        int seconds = 0;
        if (startTimer > 0) {
            seconds = (int) ((System.currentTimeMillis() - startTimer) / 1000);
        }
        if (seconds >= delay){
            setEnemy();
            waveNumber++;
            startTimer = 0;
            seconds = 0;
        }
    }

    public void setEnemy(){
        int enemies = minEnemy + waveNumber * 2;

        int maxRang = 1;
        if (waveNumber > 5)maxRang = 2;
        if (waveNumber > 10)maxRang = 3;
        if (waveNumber > 15)maxRang = 4;
        if (waveNumber > 20)maxRang = 5;

        for (int i = 0; i < enemies; i++) {
            GameSc.enemies.add(new Enemy(Main.enemy, new Point2D( 300 +(float)(Math.random() * 2000) ,200 + (float)(Math.random() * 2000)  ) , (int) (Math.random() * maxRang),GameSc.enemyAnimationOnRight));
        }
    }
}
