package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpritePlayer extends AbsPlayer {
    SpriteFactory f;
    BufferedImage image;
    String colorString;

    public SpritePlayer(int x, int y, String playerName, AbsFactory f, AbsInput input) {
        super(x, y, playerName, input);
        this.f = (SpriteFactory) f;
        colorString = null;

        if (this.getOwnColor().equals(Color.BLUE)) {
            colorString = "Blue";
        } else if (this.getOwnColor().equals(Color.RED)) {
            colorString = "Red";
        }
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        try {
            if (this.hasMitraillette()) {
                image = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + "_Mitraillette.png"));
            } else {
                image = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + ".png"));
            }
            image = SpriteVisualManager.resize(image, this.getWidth() * this.f.getScale(), this.getHeight() * this.f.getScale());
        } catch (IOException ignored) {
            System.out.println(this.f.getScale());
            System.out.println("src/res/sprites/Player/Player_" + colorString + "_" + (16 * this.f.getScale()) + "x.png");
        }
        g2d.drawImage(image, pos.x * scale, pos.y * scale, null);
    }
}
