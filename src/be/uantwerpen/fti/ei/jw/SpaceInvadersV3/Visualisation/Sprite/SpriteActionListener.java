package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The <code>SpriteActionListener</code> class implements the ActionListener interface and is responsible for handling button actions in the SpriteVisualManager class.
 * It listens for button clicks and performs the appropriate actions based on the button text.
 */
public class SpriteActionListener implements ActionListener {
    JButton button;
    Game game;
    SpriteVisualManager visualManager;

    /**
     * Creates a new SpriteActionListener instance with the specified button, game, and visualManager.
     *
     * @param button         The button associated with this listener.
     * @param game           The Game instance.
     * @param visualManager  The SpriteVisualManager instance.
     */
    public SpriteActionListener(JButton button, Game game, SpriteVisualManager visualManager){
        this.button = button;
        this.game = game;
        this.visualManager = visualManager;
    }

    /**
     * Performs the appropriate action based on the button text when a button is clicked.
     *
     * @param e The ActionEvent that occurred.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Play again")) {
            // If the button text is "Play again", reset the game and update the game screen.
            System.out.println("Play again!");
            game.resetGame();
            visualManager.getGameScreen();
        } else if (button.getText().equals("Quit")) {
            // If the button text is "Quit", exit the program.
            System.out.println("Quit");
            System.exit(0);
        }
    }
}
