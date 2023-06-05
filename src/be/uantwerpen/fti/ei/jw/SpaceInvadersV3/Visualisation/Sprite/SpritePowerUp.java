package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The <code>SpritePowerUp</code> class extends the AbsPowerUp class and represents a sprite-based power-up in the Space Invaders game.
 */
public class SpritePowerUp extends AbsPowerUp {
    private final SpriteFactory f;
    private final BufferedImage imageLeft;
    private final BufferedImage imageRight;

    /**
     * Creates a new SpritePowerUp instance with the specified coordinates and factory.
     *
     * @param x The x-coordinate of the power-up.
     * @param y The y-coordinate of the power-up.
     * @param f The factory that creates the power-up.
     */
    public SpritePowerUp(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;

        // Load the left and right images for the power-up
        try {
            // Construct the file path using the scale value obtained from the factory
            imageLeft = ImageIO.read(new File("src/res/sprites/Enemy/Powerup_left" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png"));
            imageRight = ImageIO.read(new File("src/res/sprites/Enemy/Powerup_right" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png"));
        } catch (IOException exception) {
System.err.println("Make sure there are sprites in the map \"src/res/sprites/Enemy/\" called Powerup_left" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png and Powerup_right" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png ");
            throw new RuntimeException(exception);
        }
    }

    /**
     * Visualizes the power-up by drawing it on the graphics context.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();

        // Determine which image to draw based on the power-up's movement direction
        if (this.getMovementComponent().getSpeedX() < 0) {
            // Draw the left image at the scaled coordinates
            g2d.drawImage(imageLeft, pos.x * scale, pos.y * scale, null);
        } else {
            // Draw the right image at the scaled coordinates
            g2d.drawImage(imageRight, pos.x * scale, pos.y * scale, null);
        }
    }
}
