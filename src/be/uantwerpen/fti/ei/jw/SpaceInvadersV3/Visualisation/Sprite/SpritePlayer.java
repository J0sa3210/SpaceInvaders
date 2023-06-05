package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The <code>SpritePlayer</code> class represents a player in the game with a sprite-based visual representation.
 * It extends the {@link AbsPlayer} class and provides methods for visualizing the player.
 */
public class SpritePlayer extends AbsPlayer {
    private final SpriteFactory f;
    private BufferedImage imageNormal, imageShotgun;
    private String colorString;

    /**
     * Constructs a `SpritePlayer` object with the specified position, player name, factory, and input.
     *
     * @param x          The x-coordinate of the player's position.
     * @param y          The y-coordinate of the player's position.
     * @param playerName The name of the player.
     * @param f          The factory used for creating game objects.
     * @param input      The input component for controlling the player.
     *
     * @see AbsFactory
     * @see SpriteFactory
     * @see AbsInput
     */
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
        if (imageNormal == null && imageShotgun == null) {
            try {
                imageShotgun = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + "_Shotgun" + this.getWidth() * this.f.getScale() + "x.png"));
                imageNormal = ImageIO.read(new File("src/res/sprites/Player/Player_" + colorString + this.getWidth() * this.f.getScale() + "x.png"));
            } catch (IOException exception) {
                System.err.println("Image not found!");
                System.out.println("Make sure there are sprites in the map \"src/res/sprites/Player/\" called Player_" + colorString + "_Shotgun" + this.getWidth() * this.f.getScale() + "x.png and Player_" + colorString + this.getWidth() * this.f.getScale() + "x.png");
                throw new RuntimeException(exception);
            }
        }
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = getMovementComponent().getPosition();
        if (this.hasShotgun()) {
            g2d.drawImage(imageShotgun, pos.x * scale, pos.y * scale, null);
        } else {
            g2d.drawImage(imageNormal, pos.x * scale, pos.y * scale, null);
        }

    }
}