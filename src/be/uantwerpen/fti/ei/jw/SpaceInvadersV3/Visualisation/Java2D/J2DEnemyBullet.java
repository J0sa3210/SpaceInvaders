package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemyBullet;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

/**
 * The <code>J2DEnemyBullet</code> class represents an enemy bullet in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsEnemyBullet} class and is responsible for visualizing the enemy bullet on the screen.
 */
public class J2DEnemyBullet extends AbsEnemyBullet {
    J2DFactory f;

    /**
     * Constructs a <code>J2DEnemyBullet</code> object based on the specified enemy and factory.
     *
     * @param e The enemy that fired the bullet.
     * @param f The factory used for creating visual elements.
     * @see AbsEnemy
     * @see AbsFactory
     */
    public J2DEnemyBullet(AbsEnemy e, AbsFactory f) {
        super(e);
        this.f = (J2DFactory) f;
    }

    /**
     * Visualizes the enemy bullet on the screen using the Java 2D graphics.
     */
    @Override
    public void visualize() {
        // Get the graphics context from the factory
        Graphics2D g2d = f.getG2d();
        // Get the scale factor from the factory
        double scale = f.getScale();
        // Set the color to white
        g2d.setColor(Color.WHITE);
        // Get the position of the bullet
        Point pos = getMovementComponent().getPosition();
        // Fill a rectangle on the screen representing the bullet's position and size scaled by the scale factor
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), (int) (this.getWidth() * scale), (int) (this.getHeight() * scale));
    }
}
