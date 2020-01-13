package entities;

import logic.Movement;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {

    public static int width = Options.paddleWidth;
    public static int height = Options.paddleHeight;
    public static int x = 50;
    public static int y = Pong.HEIGHT / 2 - height / 2;
    public static float speedY = 0f;
    public static float accel = Options.getFloat("Acceleration");
    public static float speedLimit = Options.getFloat("SpeedLimit");

    public static boolean moveUp = false;
    public static boolean moveDown = false;

    public static Rectangle2D player;

    public void update() { Movement.player(); }

    public static void reset() {
        x = 50;
        y = Pong.HEIGHT / 2 - height / 2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Options.getColor("PlayerColor"));
        g2d.fill(player = new Rectangle2D.Double(x, y, width, height));
    }
}
