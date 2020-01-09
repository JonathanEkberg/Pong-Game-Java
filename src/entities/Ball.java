package entities;

import collision.Collision;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {

    public Collision collision = new Collision();

    public static int size = Options.ballSize;
    public static int x = Pong.width / 2 - size / 2;
    public static int y = Pong.height / 2 - size / 2;
    public static float speed = (float) Options.map.get("BallSpeed");
    public static int angle = startAngle();

    public static Ellipse2D ball;

    public void update() {
        collision.check();
        posUpdate();
    }

    public static void posUpdate() {
        x += (speed * (float) Math.cos(Math.toRadians(angle)));
        y += (speed * (float) Math.sin(Math.toRadians(angle)));
    }

    public static void reset() {
        x = Pong.width / 2 - size / 2;
        y = Pong.height / 2 - size / 2;
        speed =(float) Options.map.get("BallSpeed");
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor((Color) Options.map.get("BallColor"));
        g2d.fill(ball = new Ellipse2D.Double(x, y, size, size));
    }

    private static int startAngle() {
        int plusMinus = (int) (Math.random() * 1);
        int randomAngle = (int) (Math.random() * 15);
        return plusMinus == 0 ? randomAngle : -randomAngle;
    }
}
