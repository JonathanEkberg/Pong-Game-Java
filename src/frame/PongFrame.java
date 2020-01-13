package frame;

import input.KeyDetector;
import main.Pong;
import options.Options;
import panel.MenuPanel;
import panel.OptionPanel;
import panel.PongPanel;

import javax.swing.*;

public class PongFrame extends JFrame {

    public static final PongPanel pongPanel = new PongPanel();
    public static final MenuPanel menuPanel = new MenuPanel();
    public static final OptionPanel optionPanel = new OptionPanel();
    public static final KeyDetector keyDetector = new KeyDetector();

    public PongFrame() {
        setUndecorated(true);
        setTitle("Pong");
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(Options.font);
        setResizable(true);
        setVisible(true);
        add(menuPanel);
        addKeyListener(keyDetector);
        pack();
        setLocationRelativeTo(null);
    }

    public void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        invalidate();
        revalidate();
        repaint();
    }

    public void exitDialog() {
        int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to the exit game?", "Exit dialog",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            pongPanel.timer.stop();
            Pong.options.saveSettings();
            dispose();
            System.exit(0);
        }
    }
}
