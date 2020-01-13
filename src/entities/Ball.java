package entities;

import logic.Collision;
import logic.Movement;
import main.Pong;
import options.Options;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {

    public Collision collision = new Collision();

    public static int size = Options.ballSize;
    public static int x = Pong.WIDTH / 2 - size / 2;
    public static int y = Pong.HEIGHT / 2 - size / 2;
    public static float speed = Options.getFloat("BallSpeed");
    public static float angle = startAngle();
    public static int lastMinX, lastMinY, lastMaxX, lastMaxY, lastCenterX, lastCenterY;

    public static Ellipse2D ball;

    public void update() {
        collision.check();
        Movement.ball();
    }

    public static void reset() {
        x = Pong.WIDTH / 2 - size / 2;
        y = Pong.HEIGHT / 2 - size / 2;
        speed = Options.getFloat("BallSpeed");
        angle = startAngle();
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Options.getColor("BallColor"));
        g2d.fill(ball = new Ellipse2D.Double(x, y, size, size));
        g2d.setColor(Color.green);
        g2d.drawLine((int) Ball.ball.getCenterX(), (int) Ball.ball.getCenterY(), (int) (Ball.ball.getCenterX() + Math.cos(Ball.angle) * (Ball.ball.getWidth() * 1.5)),  (int) (Ball.ball.getCenterY() + Math.sin(Ball.angle) * (Ball.ball.getWidth() * 1.5)));
    }

    private static float startAngle() {
        int randomAngle = (int) (Math.random() * Options.getFloat("MaxStartAngle") + Collision.radianConst / 90);
        return (int) (Math.random() * 2 + 1) == 0 ? randomAngle : -randomAngle;
    }

    public static void randomAngle() {
        if (angle == 0 || angle == -Collision.radianConst * 2)
            angle += Math.random() * Options.getFloat("CollisionRandomAngle") + Collision.radianConst / 90;

        else if (angle > 0)
            angle += Math.random() * Options.getFloat("CollisionRandomAngle") + Collision.radianConst / 90;

        else
            angle -= Math.random() * Options.getFloat("CollisionRandomAngle") + Math.PI / 90;
    }
}
