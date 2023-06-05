package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemyBullet;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The <code>SpriteEnemyBullet</code> class extends the AbsEnemyBullet class and represents an enemy bullet sprite in the Space Invaders game.
 * It handles the visualization of the enemy bullet sprite.
 */
public class SpriteEnemyBullet extends AbsEnemyBullet {
    private final SpriteFactory f;
    private final BufferedImage image;

    /**
     * Creates a new SpriteEnemyBullet instance associated with the specified enemy and factory.
     *
     * @param e The enemy that fired the bullet.
     * @param f The factory used to create the enemy bullet sprite.
     */
    public SpriteEnemyBullet(AbsEnemy e, AbsFactory f) {
        super(e);
        this.f = (SpriteFactory) f;
        try {
            image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Green" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() +".png"));
        } catch (IOException exception) {
            System.err.println("Image not found!");
            System.out.println("Make sure there are sprites in the map \"src/res/sprites/Bullets/\" called Bullet_Green"  + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() +".png");
            throw new RuntimeException(exception);
        }
    }

    /**
     * Visualizes the enemy bullet sprite using the provided graphics context.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
    }
}
