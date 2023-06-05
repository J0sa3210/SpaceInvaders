package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayerBullet;

import java.awt.*;

/**
 * The {@code J2DPlayerBullet} class represents a player bullet in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsPlayerBullet} class and is responsible for visualizing the player bullet on the screen.
 */
public class J2DPlayerBullet extends AbsPlayerBullet {
    J2DFactory f;

    /**
     * Constructs a {@code J2DPlayerBullet} object based on the specified player and factory.
     *
     * @param p The player that fired the bullet.
     * @param f The factory used for creating visual elements.
     *
     * @see AbsPlayer
     * @see AbsFactory
     */
    public J2DPlayerBullet(AbsPlayer p, AbsFactory f) {
        super(p);
        this.f = (J2DFactory) f;
    }

    /**
     * Visualizes the player bullet on the screen using the Java 2D graphics.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d(); // Get the Graphics2D object from the factory
        int scale = f.getScale(); // Get the scale from the factory
        g2d.setColor(Color.WHITE); // Set the color of the graphics object to white
        Point pos = getMovementComponent().getPosition(); // Get the position of the player bullet
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), (this.getWidth() * scale), (this.getHeight() * scale)); // Draw a filled rectangle representing the player bullet on the screen
    }
}
