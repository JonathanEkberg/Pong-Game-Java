package panel;

import frame.PongFrame;
import main.Pong;
import options.Options;
import panel.components.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OptionPanel extends JPanel implements ActionListener, ItemListener {

    private static MenuButton testButton = new MenuButton("Test");
    private static MenuButton backButton = new MenuButton("Back");
    private static JButton playerColorButton = new MenuButton("Player color");
    private static JRadioButton easyRadio = new JRadioButton("Easy", true);
    private static JRadioButton normalRadio = new JRadioButton("Normal");
    private static JRadioButton hardRadio = new JRadioButton("Hard");
    //private static JColorChooser playerColor = new JColorChooser();

    public OptionPanel() {
        setPreferredSize(Pong.PREFERRED);
        setMaximumSize(Pong.MAXIMUM);
        setMinimumSize(Pong.MINIMUM);
        setBackground(Color.black);
        setLayout(new GridBagLayout());
        addComponents();
    }

    private void addComponents() {

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(testButton, gbc);
        testButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(playerColorButton, gbc);

        playerColorButton.addActionListener(this);


//        ColorSelectionModel model = playerColor.getSelectionModel();
//        ChangeListener changeListener = new ChangeListener() {
//            public void stateChanged(ChangeEvent changeEvent) {
//                Options.playerColor = playerColor.getColor();
//            }
//        };
//        model.addChangeListener(changeListener);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        add(backButton, gbc);
        backButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(easyRadio, gbc);
        easyRadio.addItemListener(this);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(normalRadio, gbc);
        normalRadio.addItemListener(this);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        add(hardRadio, gbc);
        hardRadio.addItemListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("Made by Jonathan Ekberg", 10, (int) (Pong.pongFrame.getSize().getHeight() - 50));
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton)
            Pong.pongFrame.changePanel(PongFrame.menuPanel);
        if (e.getSource() == playerColorButton) {
            Color pColor = JColorChooser.showDialog(null, "Player Color", (Color) Options.map.get("PlayerColor"));
            if (pColor != null) {
                Options.map.put("PlayerColor", pColor);
            }
        }
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

//    @Override
//    public void stateChanged(ChangeEvent e) {
//        if (e.getSource() == playerColor);
//    }

    static final long serialVersionUID = 1;
}