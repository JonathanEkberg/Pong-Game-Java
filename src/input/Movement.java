package input;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import frame.PongFrame;
import main.Pong;
import options.Options;

import java.awt.event.KeyEvent;

public class Movement {

    public static void player() {
        exitKey();

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

    public static void enemy() {
        if (!(Boolean) Options.map.get("Multiplayer")) {
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
            } else {
                Enemy.y = Ball.y - Enemy.height / 2;

                if (Enemy.y + Enemy.height > Pong.HEIGHT) Enemy.y = Pong.HEIGHT - Enemy.height;
                if (Enemy.y < 0) Enemy.y = 0;
            }
        } else {
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
    }

    private static void exitKey() {
        if (PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            Pong.pongFrame.exitDialog();
        }
    }
}
