package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The <code>KeyboardInput1</code> class is responsible for receiving user input via the keyboard.
 * It extends the {@link AbsInput} class and implements the {@link KeyListener} interface.
 */
public class Keyboard1PlayerInput extends AbsInput implements KeyListener {

    /**
     * Constructs a <Code>KeyboardInput1</Code> object with the specified number of players.
     */
    public Keyboard1PlayerInput() {
        super(1);
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
        if (keyCode == KeyEvent.VK_P) {
            pausePressed = !pausePressed;
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
    }
}
