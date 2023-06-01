package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayerBullet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpritePlayerBullet extends AbsPlayerBullet {
    SpriteFactory f;
    BufferedImage image;

    public SpritePlayerBullet(AbsPlayer p, AbsFactory f) {
        super(p);
        this.f = (SpriteFactory) f;
        try {
            if (p.getOwnColor() == Color.RED) {
                image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Red" + 2 * this.f.getScale() + "x" + 6 * this.f.getScale() + ".png"));
            } else {
                image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Blue" + 2 * this.f.getScale() + "x" + 6 * this.f.getScale() + ".png"));
            }
        } catch (IOException ignored) {
        }

    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
    }
}
