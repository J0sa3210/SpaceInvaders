package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input;

/**
 * An abstract class containing all the necessary information for the game logic to work.
 * The object contains different booleans that will be switched on or off depending on the current inputs.
 */
public class AbsInput {

    // Movement controls
    public boolean upPressed1;
    public boolean downPressed1;
    public boolean leftPressed1;
    public boolean rightPressed1;

    public boolean upPressed2;
    public boolean downPressed2;
    public boolean leftPressed2;
    public boolean rightPressed2;

    // Activity controls
    public boolean shootPressed1;
    public boolean shootPressed2;
    public boolean powerUpPressed;

    // Menu controls
    public boolean pausePressed;

}
