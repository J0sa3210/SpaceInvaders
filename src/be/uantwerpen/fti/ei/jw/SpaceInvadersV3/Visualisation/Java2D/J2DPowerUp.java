package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPowerUp;

import java.awt.*;

/**
 * The {@code J2DPowerUp} class represents a power-up in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsPowerUp} class and is responsible for visualizing the power-up on the screen.
 */
public class J2DPowerUp extends AbsPowerUp {
    J2DFactory f;

    /**
     * Constructs a {@code J2DPowerUp} object with the specified position and factory.
     *
     * @param x The x-coordinate of the power-up.
     * @param y The y-coordinate of the power-up.
     * @param f The factory used for creating visual elements.
     *
     * @see AbsFactory
     */
    public J2DPowerUp(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (J2DFactory) f;
    }

    /**
     * Visualizes the power-up on the screen using the Java 2D graphics.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d(); // Get the Graphics2D object from the factory
        int scale = f.getScale(); // Get the scale from the factory
        g2d.setColor(Color.BLUE); // Set the color of the graphics object to blue
        if (this.getHealth() == 1) {
            g2d.setColor(Color.CYAN); // If the power-up has low health, set the color to cyan
        }
        Point pos = getMovementComponent().getPosition(); // Get the position of the power-up
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), this.getWidth() * scale, this.getHeight() * scale); // Draw a filled rectangle representing the power-up on the screen
    }
}
