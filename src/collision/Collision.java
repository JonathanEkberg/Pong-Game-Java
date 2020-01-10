package collision;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import main.Pong;
import panel.PongPanel;
import sound.SoundHandler;

public class Collision {

    public void check() {

        // Player
        if (Ball.x <= Player.player.getMaxX())  {
            if (Ball.y <= Player.player.getMaxY() || Ball.ball.getMaxY() >= Player.y) {
                Ball.x = (int) Player.player.getMaxX();
                updateAngle();
                SoundHandler.playSound();
            }
        }
        // Enemy
        if (Ball.ball.intersects(Enemy.enemy)) {
            if ((Ball.ball.getMaxX() >= Enemy.y || Ball.y <= Enemy.enemy.getMaxX()) && Ball.x < Player.player.getMaxX() && Ball.ball.getMaxX() > Player.x) {
                Ball.angle = -Ball.angle;
            } else {
                Ball.angle = -Ball.angle + 180;
            }
            SoundHandler.playSound();
        }

        // Top and bottom collision
        if (Ball.ball.getMaxY() >= Pong.pongFrame.getHeight() || Ball.ball.getMinY() <= 0) {
            Ball.angle = -Ball.angle;
            Ball.posUpdate();
            System.out.println(Ball.ball.getY() < 50 ? "Top " + Ball.angle : "Bottom. " + Ball.angle);
        }

        // Right and left collision
        if (Ball.x + Ball.size >= Pong.pongFrame.getWidth() || Ball.x <= 0) {
            PongPanel.reset(Ball.x == 0 ? "Player" : "Enemy");
        }
    }

    // private boolean playerIntersectSide() {
    //     if (Ball.ball.getX() <= Player.player.getMaxX()) {
    //         return Ball.ball.getMaxY() >= Player.player.getMinY() && Ball.ball.getMinY() <= Player.player.getMaxY();
    //     }
    //     return false;
    // }

    // private boolean playerIntersectTop() {
    //     if (Ball.ball.getX() <= Player.player.getMaxX()) {
    //         return Ball.ball.getMaxY() >= Player.player.getMinY() && Ball.ball.getMinY() <= Player.player.getMaxY();
    //     }
    //     return false;
    // }

    // private boolean playerIntersectBottom() {
    
    // }

    private void updateAngle() {
        if (Ball.angle > 360) {
            Ball.angle = -(Ball.angle - 360);
        } else {
            Ball.angle = -Ball.angle;
        }
        Ball.posUpdate();
    }
}