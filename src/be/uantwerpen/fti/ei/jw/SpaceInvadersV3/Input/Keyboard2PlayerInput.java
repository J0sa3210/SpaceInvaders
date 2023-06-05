package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The <code>KeyboardInput2</code> class is responsible for receiving user input via the keyboard.
 * It extends the {@link AbsInput} class and implements the {@link KeyListener} interface.
 */
public class Keyboard2PlayerInput extends AbsInput implements KeyListener {

    /**
     * Constructs a <code>KeyboardInput2</code> object with the specified number of players.
     */
    public Keyboard2PlayerInput() {
        super(2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Empty implementation
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1
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

        // Player 2
        if (keyCode == KeyEvent.VK_P) {
            pausePressed = !pausePressed;
        }
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

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Player 1
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

        // Player 2
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
    }
}
