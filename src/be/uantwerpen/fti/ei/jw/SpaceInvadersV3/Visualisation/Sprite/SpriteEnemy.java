package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The <code>SpriteEnemy</code> class extends the AbsEnemy class and represents an enemy sprite in the Space Invaders game.
 * It handles the visualization of the enemy sprite.
 */
public class SpriteEnemy extends AbsEnemy {
    private final SpriteFactory f;
    private final BufferedImage imageNormal;

    /**
     * Creates a new SpriteEnemy instance with the specified position and factory.
     *
     * @param x The x-coordinate of the enemy's position.
     * @param y The y-coordinate of the enemy's position.
     * @param f The factory used to create the enemy sprite.
     */
    public SpriteEnemy(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;
        try {
            imageNormal = ImageIO.read(new File("src/res/sprites/Enemy/Enemy" + this.getWidth() * this.f.getScale() + "x.png"));
        } catch (IOException exception) {
            System.err.println("Image not found!");
            System.out.println("Make sure there are sprites in the map \"src/res/sprites/Enemy/\" called Enemy" + this.getWidth() * this.f.getScale() + "x.png");
            throw new RuntimeException(exception);
        }
    }

    /**
     * Visualizes the enemy sprite using the provided graphics context.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(imageNormal, pos.x * scale, pos.y * scale, null);
    }
}
