package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

/**
 * The {@code J2DPlayer} class represents a player in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsPlayer} class and is responsible for visualizing the player on the screen.
 */
public class J2DPlayer extends AbsPlayer {
    J2DFactory f;

    /**
     * Constructs a {@code J2DPlayer} object with the specified coordinates, player name, factory, and input.
     *
     * @param x           The x-coordinate of the player.
     * @param y           The y-coordinate of the player.
     * @param playerName  The name of the player.
     * @param f           The factory used for creating visual elements.
     * @param input       The input component for controlling the player.
     *
     * @see AbsFactory
     * @see AbsInput
     */
    public J2DPlayer(int x, int y, String playerName, AbsFactory f, AbsInput input) {
        super(x, y, playerName, input);
        this.f = (J2DFactory) f;
    }

    /**
     * Visualizes the player on the screen using the Java 2D graphics.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d(); // Get the Graphics2D object from the factory
        int scale = f.getScale(); // Get the scale from the factory
        g2d.setColor(getOwnColor()); // Set the color of the graphics object to the player's own color
        Point pos = getMovementComponent().getPosition(); // Get the position of the player
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), this.getWidth() * scale, this.getHeight() * scale); // Draw a filled rectangle representing the player on the screen
    }
}
