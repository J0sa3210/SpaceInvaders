package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class that will receive user input via keyboard.
 */
public class KeyboardInput1 extends AbsInput implements KeyListener {

    public KeyboardInput1(int amOfPlayers) {
        super(amOfPlayers);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed.set(0, true);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed.set(0, true);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed.set(0, true);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed.set(0, true);
        }
        if (keyCode == KeyEvent.VK_NUMPAD0) {
            shootPressed.set(0, true);
        }
        if (keyCode == KeyEvent.VK_P){
            pausePressed = !pausePressed;
        }
        try {
            if (keyCode == KeyEvent.VK_Z) {
                upPressed.set(1, true);
            }
            if (keyCode == KeyEvent.VK_S) {
                downPressed.set(1, true);
            }
            if (keyCode == KeyEvent.VK_Q) {
                leftPressed.set(1, true);
            }
            if (keyCode == KeyEvent.VK_D) {
                rightPressed.set(1, true);
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                shootPressed.set(1, true);
            }
        } catch (IndexOutOfBoundsException ignored) {}

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed.set(0, false);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed.set(0, false);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed.set(0, false);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed.set(0, false);
        }
        if (keyCode == KeyEvent.VK_NUMPAD0) {
            shootPressed.set(0, false);
        }
        try {
            if (keyCode == KeyEvent.VK_Z) {
                upPressed.set(1, false);
            }
            if (keyCode == KeyEvent.VK_S) {
                downPressed.set(1, false);
            }
            if (keyCode == KeyEvent.VK_Q) {
                leftPressed.set(1, false);
            }
            if (keyCode == KeyEvent.VK_D) {
                rightPressed.set(1, false);
            }
            if (keyCode == KeyEvent.VK_SPACE) {
                shootPressed.set(1, false);
            }
        } catch (IndexOutOfBoundsException ignored) {
        }

    }


}