package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

/**
 * An <code>AbsVisualManager</code> is an  abstract class that represents a visual manager in the game.
 *
 * <p>
 * It contains methods for setting up the game environment, rendering the game, and displaying different screens such as the paused screen, game over screen, and victory screen.
 * </p>
 */
public abstract class AbsVisualManager {

    /**
     * Sets up the game environment with the provided input and score board.
     *
     * @param input      The input object to handle user input
     * @param scoreBoard The score board object to display scores
     */
    public abstract void setupGameEnv(AbsInput input, AbsScoreBoard scoreBoard);

    /**
     * Renders the game.
     */
    public abstract void render();

    /**
     * Shows the paused screen.
     */
    public abstract void showPaused();

    /**
     * Shows the game over screen.
     */
    public abstract void getGameOverScreen();

    /**
     * Shows the victory screen.
     */
    public abstract void getVictoryScreen();

    /**
     * Shows the game screen.
     */
    public abstract void getGameScreen();
}