package collision;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import main.Pong;
import panel.PongPanel;
import sound.SoundHandler;

public class Collision {

    boolean hitSound = false;

    public void check() {
        if      (topIntersect())          topCollision();
        else if (bottomIntersect())       bottomCollision();
        else if (leftIntersect())         PongPanel.reset("Enemy");
        else if (rightIntersect())        PongPanel.reset("Player");

        else if (playerIntersectSide())   playerSideCollision();
        else if (playerIntersectTop())    playerTopCollision();
        else if (playerIntersectBottom()) playerBottomCollision();

        else if (enemyIntersectSide())    enemySideCollision();
        else if (enemyIntersectTop())     enemyTopCollision();
        else if (enemyIntersectBottom())  enemyBottomCollision();
    }



    private boolean topIntersect() {
        return Ball.ball.getMinY() <= 0;
    }

    private boolean bottomIntersect() {
        return Ball.ball.getMaxY() >= Pong.HEIGHT;
    }

    private boolean leftIntersect() { return Ball.ball.getMinX() <= 0; }

    private boolean rightIntersect() { return Ball.ball.getMaxX() >= Pong.WIDTH; }

    private boolean playerIntersectSide() {
     if (Ball.ball.getX() <= Player.player.getMaxX()) {
         return Ball.ball.getCenterY() >= Player.player.getMinY() && Ball.ball.getCenterY() <= Player.player.getMaxY();
     }
     return false;
    }

    private boolean playerIntersectTop() {
     if (Ball.lastCenterY <= Player.player.getMinY() && Ball.ball.getCenterY() >= Player.player.getMinY()) {
         return Ball.ball.getMinX() <= Player.player.getMaxX();
     }
     return false;
    }

    private boolean playerIntersectBottom() {
     if (Ball.lastCenterY >= Player.player.getMaxY() && Ball.ball.getCenterY() <= Player.player.getMaxY()) {
         return Ball.ball.getMinX() <= Player.player.getMaxX();
     }
     return false;
    }

    private boolean enemyIntersectSide() {
        if (Ball.ball.getMaxX() >= Enemy.enemy.getMinX()) {
            return Ball.ball.getCenterY() >= Enemy.enemy.getMinY() && Ball.ball.getCenterY() <= Enemy.enemy.getMaxY();
        }
        return false;
    }

    private boolean enemyIntersectTop() {
        if (Ball.lastCenterY <= Enemy.enemy.getMinY() && Ball.ball.getCenterY() >= Enemy.enemy.getMinY()) {
            return Ball.ball.getMaxX() >= Enemy.enemy.getMinX();
        }
        return false;
    }

    private boolean enemyIntersectBottom() {
        if (Ball.lastCenterY >= Enemy.enemy.getMaxY() && Ball.ball.getCenterY() <= Enemy.enemy.getMaxY()) {
            return Ball.ball.getMinX() <= Player.player.getMinX();
        }
        return false;
    }

    private void topCollision() {
        Ball.angle = -Ball.angle;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Top collision");
    }

    private void bottomCollision() {
        Ball.angle = -Ball.angle;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Bottom collision");
    }

    private void playerTopCollision() {
        Ball.angle += 90;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Player top collision");
    }

    private void playerBottomCollision() {
        Ball.angle -= 90;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Player bottom collision");
    }

    private void playerSideCollision() {
//        if (Ball.angle > 360) {
//            Ball.angle += -180 - 360;
//        } else {
//            Ball.angle += 180;
//        }
        Ball.angle = -Ball.angle + 180;
        Ball.x = (int) Player.player.getMaxX() + Ball.size;
        Ball.posUpdate();
        if (hitSound) SoundHandler.playSound();
        System.out.println("Player side collision");
    }

    private void enemyTopCollision() {
        Ball.angle -= 90;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Enemy top collision");
    }

    private void enemyBottomCollision() {
        Ball.angle += 90;
        if (hitSound) SoundHandler.playSound();
        System.out.println("Enemy bottom collision");
    }

    private void enemySideCollision() {
//        if (Ball.angle > 360) {
//            Ball.angle += -180 - 360;
//        } else {
//            Ball.angle += -180;
//        }
        Ball.angle = -Ball.angle + 180;
        Ball.x = (int) Enemy.enemy.getMinX() - Ball.size;
        Ball.posUpdate();
        if (hitSound) SoundHandler.playSound();
        System.out.println("Enemy side collision");
    }
}