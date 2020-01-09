package main;

import frame.PongFrame;
import options.Options;

import javax.swing.*;
import java.awt.*;

public class Pong  {

    public static PongFrame pongFrame;
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int screenWidth = screenSize.width;
    public static final int screenHeight = screenSize.height;
    public static final Dimension preferred = screenSize;
    public static final Dimension maximum = new Dimension(screenWidth, screenHeight);
    public static final Dimension minimum = new Dimension(screenWidth / 3, screenHeight / 3);

    public static int width = preferred.width;
    public static int height = preferred.height;

    public static Options options;

    public Pong() {
        options = new Options();
        options.readSettings();
        new Playground();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.err.println(e);
        }
        pongFrame = new PongFrame();
    }

    public static void main(String[] args) {
        new Pong();
    }
}