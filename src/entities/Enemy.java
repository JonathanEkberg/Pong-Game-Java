package entities;

import logic.Movement;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Enemy {

    public static int width = Options.paddleWidth;
    public static int height = Options.paddleHeight;
    public static int x = Pong.WIDTH - 50 - Options.paddleWidth;
    public static int y = Pong.HEIGHT / 2 - height / 2;
    public static float speedY = 0f;
    public static float accel = Options.getFloat("EnemyAcceleration");
    public static float speedLimit = Options.getFloat("SpeedLimit");
    public static boolean moveUp = false;
    public static boolean moveDown = false;

    public static Rectangle2D enemy;

    public void update() { Movement.enemy(); }

    public static void reset() {
        x = Pong.WIDTH - 50 - Options.paddleWidth;
        y = Pong.HEIGHT / 2 - height / 2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Options.getColor("EnemyColor"));
        g2d.fill(enemy = new Rectangle2D.Double(x, y, width, height));
    }
}
