package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The <code>J2DActionListener</code> class implements the {@link ActionListener} interface to handle button actions in a Java2D-based visualization.
 * It provides functionality for handling button clicks and performing corresponding actions in the game.
 */
public class J2DActionListener implements ActionListener {
    JButton button;
    Game game;
    J2DVisualManager visualManager;

    /**
     * Constructs a <code>J2DActionListener</code> object with the specified button, {@link Game} and visual manager.
     *
     * @param button        The button associated with the action.
     * @param game          The game object to perform game-related actions.
     * @param visualManager The visual manager to update the game screen.
     *
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsVisualManager
     * @see J2DVisualManager
     */
    public J2DActionListener(JButton button, Game game, J2DVisualManager visualManager) {
        this.button = button;
        this.game = game;
        this.visualManager = visualManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If the button has text "Play again", reset the game
        if (button.getText().equals("Play again")) {
            System.out.println("Play again!");
            // Reset the game
            game.resetGame();
            // Update the game screen in the visual manager
            visualManager.getGameScreen();

        // If the button has text "Quit", exit the game
        } else if (button.getText().equals("Quit")) {
            System.out.println("Quit");
            // Exit the application
            System.exit(0);
        }
    }
}
