package panel;

import entities.Ball;
import entities.Enemy;
import entities.Player;
import frame.PongFrame;
import main.Pong;
import options.Options;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class PongPanel extends JPanel implements ActionListener {

    public static final Player player = new Player();
    public static final Enemy enemy = new Enemy();
    public static final Ball ball = new Ball();

    public static int playerScore = 0;
    public static int enemyScore = 0;
    public static boolean gameStart = false;
    public static boolean gameVisible = false;
    public Timer timer = new Timer(1000 / (Integer) Options.map.get("Fps"), this);


    public PongPanel() {
        setLayout(null);
        setPreferredSize(Pong.PREFERRED);
        setMaximumSize(Pong.MAXIMUM);
        setMinimumSize(Pong.MINIMUM);
        setBackground((Color) Options.map.get("BackgroundColor"));

        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                (Boolean) Options.map.get("Antialiasing") ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setColor(Color.green);
        g2d.setFont(Options.startFont);
        if (!gameStart) {
            g2d.drawString("Press SPACE to start!", Pong.WIDTH / 2 - 200, Pong.HEIGHT / 2 - 150);
        }
        g2d.drawString(Integer.toString(playerScore), Pong.WIDTH / 3, 90);
        g2d.drawString(Integer.toString(enemyScore), Pong.WIDTH * 2/3, 90);
        if (gameVisible) {
            player.draw(g2d);
            enemy.draw(g2d);
            ball.draw(g2d);
        }
        g2d.drawString(Double.toString(Ball.angle), Pong.WIDTH / 2, 90);
    }

    public static void reset(String winner) {
        gameStart = false;

        Player.reset();
        Enemy.reset();
        Ball.reset();

        if (winner.toLowerCase().equals("player")) {
            playerScore++;
            System.out.println("Player won!");
        }
        if (winner.toLowerCase().equals("enemy")) {
            enemyScore++;
            System.out.println("Enemy won!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameStart && gameVisible) {
            if (PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_SPACE)) {
                PongPanel.gameStart = true;
            }
        }
        if (gameStart && gameVisible) {
            player.update();
            enemy.update();
            ball.update();
        }
        repaint();
    }
}
