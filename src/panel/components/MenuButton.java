package panel.components;


import main.Pong;
import options.Options;
import panel.OptionPanel;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String text) {
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
