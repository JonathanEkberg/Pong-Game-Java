package panel.components;


import main.Pong;
import options.Options;

import javax.swing.*;
import java.awt.*;

public class MainOptionButton extends JButton {

    public MainOptionButton(String text) {
        super(text);
        setFocusPainted(false);
        setFocusable(false);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setForeground(Color.BLACK);
        setVisible(true);
        setFont(Options.font);
        setPreferredSize(new Dimension((int) (Pong.width / 2), Pong.height / 6));
    }
}
