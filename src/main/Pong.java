package main;

import frame.PongFrame;
import options.Options;

import javax.swing.*;
import java.awt.*;

public class Pong {

    public static PongFrame pongFrame;
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final Dimension PREFERRED = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    public static final Dimension MAXIMUM = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    public static final Dimension MINIMUM = new Dimension(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 3);
    public static final int WIDTH = PREFERRED.width;
    public static final int HEIGHT = PREFERRED.height;

    public static Options options;

    public Pong() {
        options = new Options();
        options.readSettings();
        new Playground();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        pongFrame = new PongFrame();
    }

    public static void main(String[] args) {
        new Pong();
    }
}