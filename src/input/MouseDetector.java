package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseDetector implements MouseListener {

    private int mouseX, mouseY;
    private boolean clicked;

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        clicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClicked(e);
        clicked = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isMousePressed() {
        return clicked;
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

}
