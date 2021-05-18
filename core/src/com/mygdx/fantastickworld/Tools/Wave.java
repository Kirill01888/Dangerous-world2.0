package com.mygdx.fantastickworld.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.fantastickworld.Actor.Enemy;
import com.mygdx.fantastickworld.Actor.FirstBoss;
import com.mygdx.fantastickworld.Main;
import com.mygdx.fantastickworld.screen.GameSc;

public class Wave {

    private int delay, minEnemy;
    private long startTimer,startTimer2;
    private int waveNumber;

    public Wave(int delay, int waveNumber, int minEnemy) {
        this.delay = delay;
        this.waveNumber = waveNumber;
        this.minEnemy = minEnemy;
    }

    public void update() {
        if (GameSc.enemies.size == 0 && startTimer == 0) {
            startTimer = System.currentTimeMillis();
        }
        if (GameSc.bossesArray.size == 0 && startTimer2 == 0){
            startTimer2 = System.currentTimeMillis();
        }
        int seconds = 0;
        int seconds2 = 0;
        if (startTimer > 0) {
            seconds = (int) ((System.currentTimeMillis() - startTimer) / 1000);
        }
        if (startTimer2 > 0) {
            seconds2 = (int) ((System.currentTimeMillis() - startTimer) / 1000);
        }
        if (seconds >= delay) {
            if (waveNumber == 10){
                setFirstBoss();
            }
            if (waveNumber == 20){
                setSecondBoss();
            }
            if (waveNumber == 30){
                setThirdBoss();
            }
            setEnemy();
            waveNumber++;
            startTimer = 0;
            seconds = 0;
        }
    }

    public void setEnemy() {
        int enemies = minEnemy + waveNumber * 2;

        int maxRang = 1;
        if (waveNumber > 5) maxRang = 2;
        if (waveNumber > 10) maxRang = 3;
        if (waveNumber > 15) maxRang = 4;
        if (waveNumber > 20) maxRang = 5;

        for (int i = 0; i < enemies; i++) {
            GameSc.enemies.add(new Enemy(null, new Point2D(300 + (float) (Math.random() * 2000), 200 + (float) (Math.random() * 2000)), (int) (Math.random() * maxRang), GameSc.enemyAnimationOnRight));
        }
    }

    public void setFirstBoss() {
        int bossCount = 1;
        for (int i = GameSc.bossesArray.size; i < bossCount; i++) {
            GameSc.bossesArray.add(new FirstBoss(null, new Point2D(Main.place2.getWidth() / 2, Main.place2.getHeight() / 2), 30, (int) (Main.HEIGHT / 1.5), 1000, GameSc.enemyAnimationOnRight));
        }
    }

    public void setSecondBoss() {
        int bossCount = 1;
        for (int i = GameSc.bossesArray.size; i < bossCount; i++) {
            GameSc.bossesArray.add(new FirstBoss(null, new Point2D(Main.place2.getWidth() / 2, Main.place2.getHeight() / 2), 60, Main.WIDTH / 15, 500, GameSc.enemyAnimationOnRight));
        }
    }

    public void setThirdBoss() {
        int bossCount = 1;
        for (int i = GameSc.bossesArray.size; i < bossCount; i++) {
            GameSc.bossesArray.add(new FirstBoss(null, new Point2D(Main.place2.getWidth() / 2, Main.place2.getHeight() / 2), 60, Main.WIDTH / 5, 500, GameSc.enemyAnimationOnRight));
        }
    }
}
