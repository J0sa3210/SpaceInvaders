package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteEnemy extends AbsEnemy {
    SpriteFactory f;
    BufferedImage imageNormal;

    public SpriteEnemy(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;
        try {
            imageNormal = ImageIO.read(new File("src/res/sprites/Enemy/Enemy" + 16 * this.f.getScale() + "x.png"));
        } catch (IOException ignored) {}

    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(imageNormal, pos.x * scale, pos.y * scale, null);
    }
}
