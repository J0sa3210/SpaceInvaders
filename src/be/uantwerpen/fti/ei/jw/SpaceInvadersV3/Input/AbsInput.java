package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class containing all the necessary information for the game logic to work.
 * The object contains different booleans that will be switched on or off depending on the current inputs.
 */
public class AbsInput {

    // Movement controls
    public List<Boolean> upPressed = new ArrayList<>();
    public List<Boolean> downPressed = new ArrayList<>();
    public List<Boolean> leftPressed = new ArrayList<>();
    public List<Boolean> rightPressed = new ArrayList<>();
    public List<Boolean> shootPressed = new ArrayList<>();

    // Menu controls
    public boolean pausePressed;

    public AbsInput(int amOfPlayers) {
        for(int i = 0; i < amOfPlayers; i++){
            upPressed.add(false);
            downPressed.add(false);
            leftPressed.add(false);
            rightPressed.add(false);
            shootPressed.add(false);
        }
        pausePressed = false;
    }

    public List<Boolean> getLeftPressed() {
        return leftPressed;
    }

    public List<Boolean> getRightPressed() {
        return rightPressed;
    }

    public List<Boolean> getShootPressed() {
        return shootPressed;
    }

    public boolean isPausePressed() {
        return pausePressed;
    }
}
