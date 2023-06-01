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
    BufferedImage imageLeft, imageRight;

    public SpritePowerUp(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;
        try {
            imageLeft = ImageIO.read(new File("src/res/sprites/Enemy/Powerup_left" + 32 * this.f.getScale() + "x" + 16 * this.f.getScale() + ".png"));
            imageRight = ImageIO.read(new File("src/res/sprites/Enemy/Powerup_right" + 32 * this.f.getScale() + "x" + 16 * this.f.getScale() + ".png"));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        if (this.getMovementComponent().getSpeedX() < 0) {
            g2d.drawImage(imageLeft, pos.x * scale, pos.y * scale, null);
        } else {
            g2d.drawImage(imageRight, pos.x * scale, pos.y * scale, null);
        }
    }
}
