package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDetector implements KeyListener {

    private boolean[] keys = new boolean[256];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode() ] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode() ] = false;
    }

    public boolean isKeyPressed(int key) {
        return keys[key];
    }

    public boolean isKeyReleased(int key) {
        return !keys[key];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
