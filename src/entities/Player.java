package entities;

import input.Movement;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {

    public static int width = Options.paddleWidth;
    public static int height = Options.paddleHeight;
    public static int x = 50;
    public static int y = Pong.height / 2 - height / 2;
    public static float speedY = 0f;
    public static float accel = (float) Options.map.get("Acceleration");
    public static float speedLimit = 10f;

    public static boolean moveUp = false;
    public static boolean moveDown = false;

    public static Rectangle2D player;

    public void update() {
        Movement.player();
    }

    public static void reset() {
        x = 50;
        y = Pong.height / 2 - height / 2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor((Color) Options.map.get("PlayerColor"));
        player = new Rectangle2D.Double(x, y, width, height);
        g2d.fill(player);
    }
}
