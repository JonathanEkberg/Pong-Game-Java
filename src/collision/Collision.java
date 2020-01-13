package collision;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import input.Movement;
import main.Pong;
import panel.PongPanel;
import sound.SoundHandler;

public class Collision {

    boolean hitSound = true;

    public void check() {
        if      (playerIntersectSide())   playerSideCollision();
        else if (playerIntersectTop())    playerTopCollision();
        else if (playerIntersectBottom()) playerBottomCollision();

        else if (topIntersect())          topCollision();
        else if (bottomIntersect())       bottomCollision();
        else if (leftIntersect())         PongPanel.reset("Enemy");
        else if (rightIntersect())        PongPanel.reset("Player");

        else if (enemyIntersectSide())    enemySideCollision();
        else if (enemyIntersectTop())     enemyTopCollision();
        else if (enemyIntersectBottom())  enemyBottomCollision();
    }


    private boolean topIntersect() { return Ball.ball.getMinY() <= 0; }

    private boolean bottomIntersect() { return Ball.ball.getMaxY() >= Pong.HEIGHT; }

    private boolean leftIntersect() { return Ball.ball.getMinX() <= 0; }

    private boolean rightIntersect() { return Ball.ball.getMaxX() >= Pong.WIDTH; }


    // Player collision check
    private boolean playerIntersectSide() {
        if (Ball.ball.getX() <= Player.player.getMaxX())
            return Ball.ball.getMinY() >= Player.player.getMinY() && Ball.ball.getMaxY() <= Player.player.getMaxY();
        return false;
    }

    private boolean playerIntersectTop() {
        if (Ball.lastMaxY < Player.player.getMinY() && Ball.ball.getMaxY() >= Player.player.getMinY())
            return Ball.ball.getMinX() <= Player.player.getMaxX();
        return false;
    }

    private boolean playerIntersectBottom() {
        if (Ball.ball.getMinX() <= Player.player.getMaxX())
            return Ball.lastMinY > Player.player.getMaxY() && Ball.ball.getMinY() <= Player.player.getMaxY();
        return false;
    }


    // Enemy collision check
    private boolean enemyIntersectSide() {
        if (Ball.ball.getMaxX() >= Enemy.enemy.getMinX())
            return Ball.ball.getCenterY() >= Enemy.enemy.getMinY() && Ball.ball.getCenterY() <= Enemy.enemy.getMaxY();
        return false;
    }

    private boolean enemyIntersectTop() {
        if (Ball.lastCenterY <= Enemy.enemy.getMinY() && Ball.ball.getCenterY() >= Enemy.enemy.getMinY())
            return Ball.ball.getMaxX() >= Enemy.enemy.getMinX();
        return false;
    }

    private boolean enemyIntersectBottom() {
        if (Ball.lastCenterY >= Enemy.enemy.getMaxY() && Ball.ball.getCenterY() <= Enemy.enemy.getMaxY())
            return Ball.ball.getMinX() <= Player.player.getMinX();
        return false;
    }


    // Top and bottom collision check
    private void topCollision() {
        System.out.print("Collision - Wall top | Angle: " + Ball.angle);
        Ball.angle = -Ball.angle;
        Ball.randomAngle();
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

    private void bottomCollision() {
        System.out.print("Collision - Wall bottom | Angle: " + Ball.angle);
        Ball.angle = -Ball.angle;
        Ball.randomAngle();
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }


    // Player collision movement
    private void playerSideCollision() {
        System.out.print("Collision - Player side | Angle: " + Ball.angle);
        Ball.angle = -Ball.angle + 180;
        Ball.x = (int) Player.player.getMaxX();
        Ball.randomAngle();
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

    private void playerTopCollision() {
        System.out.print("Collision - Player top | Angle: " + Ball.angle);
        Ball.angle += 90;
        Ball.y = (int) Player.player.getMinY() - Ball.size; 
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

    private void playerBottomCollision() {
        System.out.print("Collision - Player bottom | Angle: " + Ball.angle);
        Ball.angle -= 90;
        Ball.y = (int) Player.player.getMaxY();
        Movement.ball(); 
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }



    // Enemy collision movement
    private void enemySideCollision() {
        System.out.print("Collision - Enemy side | Angle: " + Ball.angle);
        Ball.angle = -Ball.angle + 180;
        Ball.x = (int) Enemy.enemy.getMinX() - Ball.size;
        Ball.randomAngle();
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

    private void enemyTopCollision() {
        System.out.print("Collision - Enemy top | Angle: " + Ball.angle);
        Ball.angle -= 90;
        Ball.y = (int) Enemy.enemy.getMinY() - Ball.size; 
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

    private void enemyBottomCollision() {
        System.out.print("Collision - Enemy bottom | Angle: " + Ball.angle);
        Ball.angle += 90;
        Ball.y = (int) Enemy.enemy.getMaxY(); 
        Movement.ball();
        if (hitSound) SoundHandler.playSound();
        System.out.print("   New angle: " + Ball.angle + "\n");
    }

}