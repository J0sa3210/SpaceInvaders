package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPowerUp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpritePowerUp extends AbsPowerUp {
    SpriteFactory f;
    BufferedImage image;

    public SpritePowerUp(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;

    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        try {
            if (this.getMovementComponent().getSpeedX() < 0) {
                image = ImageIO.read(new File("src/res/sprites/Enemy/alien2_left.png"));
            } else {
                image = ImageIO.read(new File("src/res/sprites/Enemy/alien2_right.png"));
            }
            image = SpriteVisualManager.resize(image, this.getWidth() * scale, this.getHeight() * scale);
        } catch (IOException ignored) {
        }
        Point pos = getMovementComponent().getPosition();
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
    }
}
