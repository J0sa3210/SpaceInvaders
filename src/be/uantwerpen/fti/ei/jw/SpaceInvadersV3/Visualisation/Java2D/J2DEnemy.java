package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

/**
 * The <code>J2DEnemy</code> class represents an enemy in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsEnemy} class and is responsible for visualizing the enemy on the screen.
 */
public class J2DEnemy extends AbsEnemy {
    J2DFactory f;

    /**
     * Constructs a <code>J2DEnemy</code> object with the specified coordinates and factory.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @param f The factory used for creating visual elements.
     *
     * @see AbsFactory
     */
    public J2DEnemy(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (J2DFactory) f;
    }

    /**
     * Visualizes the enemy on the screen using the Java 2D graphics.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.GREEN);
        Point pos = getMovementComponent().getPosition();
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), this.getWidth() * scale, this.getHeight() * scale);
    }
}