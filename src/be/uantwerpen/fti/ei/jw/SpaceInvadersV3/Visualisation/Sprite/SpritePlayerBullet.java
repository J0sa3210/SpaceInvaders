package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayerBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The <code>SpritePlayerBullet</code> class extends the AbsPlayerBullet class and represents a sprite-based player bullet in the Space Invaders game.
 */
public class SpritePlayerBullet extends AbsPlayerBullet {
    private final SpriteFactory f;
    private final BufferedImage image;

    /**
     * Creates a new SpritePlayerBullet instance associated with the specified player and factory.
     *
     * @param p The player that fired the bullet.
     * @param f The factory that creates the bullet.
     */
    public SpritePlayerBullet(AbsPlayer p, AbsFactory f) {
        super(p);
        this.f = (SpriteFactory) f;
        try {
            if (p.getOwnColor() == Color.RED) {
                image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Red" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png"));
            } else {
                image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Blue" + this.getWidth() * this.f.getScale() + "x" + this.getHeight() * this.f.getScale() + ".png"));
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Visualizes the player bullet by drawing it on the graphics context.
     */
    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
    }
}
