package entities;

import collision.Collision;
import input.Movement;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {

    public Collision collision = new Collision();

    public static int size = Options.ballSize;
    public static int x = Pong.WIDTH / 2 - size / 2;
    public static int y = Pong.HEIGHT / 2 - size / 2;
    public static float speed = (float) Options.map.get("BallSpeed");
    public static int angle = startAngle();
    public static int lastMinX, lastMinY, lastMaxX, lastMaxY, lastCenterX, lastCenterY;
    public static Ball lastPos;

    public static Ellipse2D ball;

    public void update() {
        collision.check();
        Movement.ball();
    }

    

    public static void reset() {
        x = Pong.WIDTH / 2 - size / 2;
        y = Pong.HEIGHT / 2 - size / 2;
        speed =(float) Options.map.get("BallSpeed");
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor((Color) Options.map.get("BallColor"));
        g2d.fill(ball = new Ellipse2D.Double(x, y, size, size));
    }

    private static int startAngle() {
        int randomAngle = (int) (Math.random() * 45 + 1);
        return (int) (Math.random() * 2) == 0 ? randomAngle : -randomAngle;
    }
}
