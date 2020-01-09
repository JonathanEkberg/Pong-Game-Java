package panel;

import frame.PongFrame;
import main.Pong;
import options.Options;
import panel.components.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private static MenuButton startButton = new MenuButton("Start");
    private static MenuButton optionsButton = new MenuButton("Options");
    private static MenuButton quitButton = new MenuButton("Quit");

    public MenuPanel() {
        setPreferredSize(Pong.preferred);
        setMaximumSize(Pong.maximum);
        setMinimumSize(Pong.minimum);
        setBackground(Color.black);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 6, 6, 6);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(startButton, gbc);
        startButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(optionsButton, gbc);
        optionsButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(quitButton, gbc);
        quitButton.addActionListener(this);
    }
/*
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Made by Jonathan Ekberg", 10, (int) (Pong.pongFrame.getSize().getHeight() - 50));
    }
*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            PongPanel.gameVisible = true;
            Pong.pongFrame.changePanel(PongFrame.pongPanel);
        }

        if (e.getSource() == optionsButton) {
            Pong.pongFrame.changePanel(PongFrame.optionPanel);
        }

        if (e.getSource() == quitButton) {
            Pong.options.saveSettings();
            Pong.pongFrame.dispose();
            System.exit(0);
        }
    }
}
