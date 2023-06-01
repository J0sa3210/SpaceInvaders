package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemyBullet;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteEnemyBullet extends AbsEnemyBullet {
    SpriteFactory f;
    BufferedImage image;

    public SpriteEnemyBullet(AbsEnemy e, AbsFactory f) {
        super(e);
        this.f = (SpriteFactory) f;
        try {
            image = ImageIO.read(new File("src/res/sprites/Bullets/Bullet_Green" + 2 * this.f.getScale() + "x" + 6 * this.f.getScale() +".png"));
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
