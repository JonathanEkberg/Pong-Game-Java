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
    public static int maxScore = 2;
    public static boolean gameStart = false;
    public static boolean gameVisible = false;
    public static boolean gameOver = false;
    public Timer timer = new Timer(1000 / (int) Options.map.get("Fps"), this);

    public static String winner;
    private static int tick = 0;
    public static Graphics2D g2d;

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
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                (Boolean) Options.map.get("Antialiasing") ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setColor(Color.green);

        g2d.setFont(Options.startFont);
        if (!gameStart && !gameOver) {
            g2d.drawString("Press SPACE to start!", (Pong.WIDTH / 2f - (Pong.WIDTH / 4.8f)), Pong.HEIGHT / 2f - 150f);
        } else if (!gameStart) {
            g2d.drawString("Press SPACE to restart!", (Pong.WIDTH / 2f - (Pong.WIDTH / 4.8f)), Pong.HEIGHT / 2f - 200f);
        }

        g2d.drawString(Integer.toString(playerScore), Pong.WIDTH / 3, 90);
        g2d.drawString(Integer.toString(enemyScore), Pong.WIDTH * 2/3, 90);
        if (gameVisible) {
            player.draw(g2d);
            enemy.draw(g2d);
            ball.draw(g2d);
        }
        g2d.drawString(Double.toString(Ball.angle % (2 * Math.PI)), Pong.WIDTH / 2, 90);

        if (gameVisible && gameOver) drawCenteredString(g2d, winner);
    }

    public void drawCenteredString(Graphics g, String text) {
        FontMetrics metrics = g.getFontMetrics(Options.font);
        Rectangle rect = new Rectangle();
        rect.width = Pong.WIDTH;
        rect.height = Pong.HEIGHT;
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = (int) (rect.y + ((rect.height - metrics.getHeight()) * 0.4) + metrics.getAscent());
        g.setFont(Options.font);
        g.drawString(text, x, y);
    }

    public static void reset(String winner) {
        gameStart = false;

        if (winner.toLowerCase().equals("player")) {
            if (playerScore != maxScore - 1) {
                playerScore++;
                System.out.println("Player won!");
            } else winner("Player");
        }

        if (winner.toLowerCase().equals("enemy")) {
            if (enemyScore != maxScore - 1) {
                enemyScore++;
                System.out.println("Enemy won!");
            } else winner ("Enemy");
        }

        Player.reset();
        Enemy.reset();
        Ball.reset();
    }

    public static void winner(String winner1) {
        gameOver = true;
        winner = winner1 + " won the game!";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PongFrame.keyDetector.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (!gameStart && gameVisible) gameStart = true;

            if (gameOver) {
                gameOver = false;
                playerScore = 0;
                enemyScore = 0;
            }
        }

            
        if (gameStart && gameVisible) {
            player.update();
            enemy.update();
            ball.update();
        }
        repaint();

        tick++;
        if (tick % (int) Options.map.get("Fps") == 0) System.out.println(tick / (int) Options.map.get("Fps"));
    }
}
