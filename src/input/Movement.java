package input;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import frame.PongFrame;
import main.Pong;
import options.Options;
import panel.PongPanel;

import java.awt.event.KeyEvent;

public class Movement {

    public static void player() {
        if (PongPanel.gameStart) exitKey();

        Player.moveUp = PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_W);
        Player.moveDown = PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_S);

        if (Player.moveUp && Player.speedY > -Player.speedLimit) {
            Player.speedY -= Player.accel;
        }
        if (Player.moveDown && Player.speedY < Player.speedLimit) {
            Player.speedY += Player.accel;
        }
        if (!Player.moveUp && !Player.moveDown) {
            if (Player.speedY > 0) {
                Player.speedY -= Player.accel;
            }
            if (Player.speedY < 0) {
                Player.speedY += Player.accel;
            }
        }

        if (Player.y + Player.height > Pong.HEIGHT) Player.y = Pong.HEIGHT - Player.height;
        if (Player.y < 0) Player.y = 0;

        Player.y += Player.speedY;
    }

    public static void ball() {
        Ball.lastMinX = (int) Ball.ball.getMinX();
        Ball.lastMinY = (int) Ball.ball.getMinY();
        Ball.lastMaxX = (int) Ball.ball.getMaxX();
        Ball.lastMaxY = (int) Ball.ball.getMaxY();
        Ball.lastCenterX = (int) Ball.ball.getCenterX();
        Ball.lastCenterY = (int) Ball.ball.getCenterY();
        Ball.x += (Ball.speed * (float) Math.cos(Math.toRadians(Ball.angle)));
        Ball.y += (Ball.speed * (float) Math.sin(Math.toRadians(Ball.angle)));
        Ball.speed += (float) Options.map.get("BallAcceleration");
    }

    public static void enemy() {
        if (!((Boolean) Options.map.get("Multiplayer")))
            computerMove();
        else enemyMove();
    }

    private static void enemyMove() {
        Enemy.moveUp = PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_UP);
        Enemy.moveDown = PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_DOWN);

        if (Enemy.moveUp && Enemy.speedY > -Enemy.speedLimit) {
            Enemy.speedY -= Enemy.accel;
        }
        if (Enemy.moveDown && Enemy.speedY < Enemy.speedLimit) {
            Enemy.speedY += Enemy.accel;
        }
        if (!Enemy.moveUp && !Enemy.moveDown) {
            if (Enemy.speedY > 0) {
                Enemy.speedY -= Enemy.accel;
            }
            if (Enemy.speedY < 0) {
                Enemy.speedY += Enemy.accel;
            }
        }

        if (Enemy.y + Enemy.height > Pong.HEIGHT) Enemy.y = Pong.HEIGHT - Enemy.height;
        if (Enemy.y < 0) Enemy.y = 0;

        Enemy.y += Enemy.speedY;
    }

    private static void exitKey() {
        if (PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            Pong.pongFrame.exitDialog();
        }
    }

    private static void computerMove() {
        int difficulty = (Integer) Options.map.get("Difficulty");
        if (Ball.ball.getMaxY() > (Enemy.enemy.getCenterY() + ((Enemy.enemy.getHeight() * 1/3f) * difficulty))) {
            if (Enemy.speedY < Enemy.speedLimit) Enemy.speedY += Enemy.accel;

        } else if (Ball.ball.getMinY() < Enemy.enemy.getCenterY() - ((Enemy.enemy.getHeight() * 1/3f) * difficulty)) {
            if (Enemy.speedY > -Enemy.speedLimit) Enemy.speedY -= Enemy.accel;

        } else {
            if (Enemy.speedY > 0) Enemy.speedY -= Enemy.accel;
            else if (Enemy.speedY < 0) Enemy.speedY += Enemy.accel;
        }

        if (Enemy.enemy.getMaxY() >= Pong.HEIGHT) Enemy.y = (int) (Pong.HEIGHT - Enemy.enemy.getHeight());
        if (Enemy.enemy.getMinY() <= 0) Enemy.y = 0;
        Enemy.y += Enemy.speedY;
    }

    private static void computerMoveOld() {
        if ((Integer) Options.map.get("Difficulty") == 1) {
            if (Ball.y > Enemy.enemy.getCenterY() + (Enemy.height / 2f)) {
                if (Enemy.speedY < Enemy.speedLimit) {
                    Enemy.speedY += Enemy.accel;
                }
            }
            if (Ball.y < Enemy.enemy.getCenterY() - (Enemy.height / 2f)) {
                if (Enemy.speedY > -Enemy.speedLimit) {
                    Enemy.speedY -= Enemy.accel;
                }
            }
            if (Enemy.y + Enemy.height > Pong.HEIGHT) Enemy.y = Pong.HEIGHT - Enemy.height;
            if (Enemy.y < 0) Enemy.y = 0;
            Enemy.y += Enemy.speedY;
        }
    }
}
