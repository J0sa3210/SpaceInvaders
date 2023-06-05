package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * An <code>AbsInput</code> class is an abstract class that contains all the necessary information for the game logic to work.
 * It holds different booleans that can be switched on or off based on the current inputs.
 */
public class AbsInput {

    /**
     * A list of booleans indicating whether the "up" button is pressed for each player.
     */
    public List<Boolean> upPressed = new ArrayList<>();

    /**
     * A list of booleans indicating whether the "down" button is pressed for each player.
     */
    public List<Boolean> downPressed = new ArrayList<>();

    /**
     * A list of booleans indicating whether the "left" button is pressed for each player.
     */
    public List<Boolean> leftPressed = new ArrayList<>();

    /**
     * A list of booleans indicating whether the "right" button is pressed for each player.
     */
    public List<Boolean> rightPressed = new ArrayList<>();

    /**
     * A list of booleans indicating whether the "shoot" button is pressed for each player.
     */
    public List<Boolean> shootPressed = new ArrayList<>();

    /**
     * A boolean indicating whether the "pause" button is pressed.
     */
    public boolean pausePressed;

    /**
     * Constructs an <Code>AbsInput</Code> object with the specified number of players.
     *
     * @param amOfPlayers The number of players.
     */
    public AbsInput(int amOfPlayers) {
        for (int i = 0; i < amOfPlayers; i++) {
            upPressed.add(false);
            downPressed.add(false);
            leftPressed.add(false);
            rightPressed.add(false);
            shootPressed.add(false);
        }
        pausePressed = false;
    }

    /**
     * Returns the list of booleans indicating whether the "left" button is pressed for each player.
     *
     * @return The list of booleans indicating whether the "left" button is pressed.
     */
    public List<Boolean> getLeftPressed() {
        return leftPressed;
    }

    /**
     * Returns the list of booleans indicating whether the "right" button is pressed for each player.
     *
     * @return The list of booleans indicating whether the "right" button is pressed.
     */
    public List<Boolean> getRightPressed() {
        return rightPressed;
    }

    /**
     * Returns the list of booleans indicating whether the "shoot" button is pressed for each player.
     *
     * @return The list of booleans indicating whether the "shoot" button is pressed.
     */
    public List<Boolean> getShootPressed() {
        return shootPressed;
    }
}
