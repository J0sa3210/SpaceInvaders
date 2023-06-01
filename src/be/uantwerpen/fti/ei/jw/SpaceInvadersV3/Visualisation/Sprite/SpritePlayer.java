package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.SoundComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpritePlayer extends AbsPlayer {
    private final SpriteFactory f;
    private BufferedImage imageNormal, imageMitraillette;
    private String colorString;
    private SoundComponent powerUpSound;

    public SpritePlayer(int x, int y, String playerName, AbsFactory f, AbsInput input) {
        super(x, y, playerName, input);
        this.f = (SpriteFactory) f;
        colorString = null;

        if (this.getOwnColor().equals(Color.BLUE)) {
            colorString = "Blue";
        } else if (this.getOwnColor().equals(Color.RED)) {
            colorString = "Red";
        }
        try {
            imageMitraillette = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + "_Mitraillette" + 16 * this.f.getScale() + "x.png"));
            imageNormal = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + 16 * this.f.getScale() + "x.png"));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        if (this.hasMitraillette()) {
            g2d.drawImage(imageMitraillette, pos.x * scale, pos.y * scale, null);
        } else {
            g2d.drawImage(imageNormal, pos.x * scale, pos.y * scale, null);
        }

    }
}
