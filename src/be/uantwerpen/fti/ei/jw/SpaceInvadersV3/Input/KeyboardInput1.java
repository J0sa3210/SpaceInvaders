package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class that will receive user input via keyboard.
 */
public class KeyboardInput1 extends AbsInput implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed1 = true;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed1 = true;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed1 = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed1 = true;
        }
        if (keyCode == KeyEvent.VK_NUMPAD0) {
            shootPressed1 = true;
        }
        if (keyCode == KeyEvent.VK_Z) {
            upPressed2 = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            downPressed2 = true;
        }
        if (keyCode == KeyEvent.VK_Q) {
            leftPressed2 = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightPressed2 = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            shootPressed2 = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed1 = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed1 = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed1 = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed1 = false;
        }
        if (keyCode == KeyEvent.VK_NUMPAD0) {
            shootPressed1 = false;
        }
        if (keyCode == KeyEvent.VK_Z) {
            upPressed2 = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            downPressed2 = false;
        }
        if (keyCode == KeyEvent.VK_Q) {
            leftPressed2 = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightPressed2 = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            shootPressed2 = false;
        }
    }
}