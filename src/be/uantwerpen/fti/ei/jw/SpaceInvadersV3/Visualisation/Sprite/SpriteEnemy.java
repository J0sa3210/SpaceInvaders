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
    BufferedImage image;

    public SpriteEnemy(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;
        try {
            image = ImageIO.read(new File("src/res/sprites/Enemy/alien4.png"));
            image = SpriteVisualManager.resize(image,this.getWidth()*this.f.getScale(), this.getHeight()*this.f.getScale());
        } catch (IOException ignored) {}

    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.GREEN);
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
        //g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), this.getWidth() * scale, this.getHeight() * scale);
    }
}
