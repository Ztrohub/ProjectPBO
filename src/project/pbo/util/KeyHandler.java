package project.pbo.util;

import project.pbo.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>();

    public static class Key {
        public int presses, absrobs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }

        public void tick() {
            if (absrobs < presses) {
                absrobs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }

    public void releaseAll() {
        for (Key key : keys) {
            key.down = false;
        }
    }

    public void tick() {
        for (Key key : keys){
            key.tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        Do Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
}
