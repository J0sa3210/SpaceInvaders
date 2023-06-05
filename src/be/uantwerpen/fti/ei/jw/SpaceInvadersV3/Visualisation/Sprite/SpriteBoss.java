package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsBoss;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class SpriteBoss extends AbsBoss {
    SpriteFactory f;
    private BufferedImage imageNormal;
    private final RescaleOp filter;
    int currentHealth = this.getHealth();
    /**
     * Constructs a new AbsEnemy object with given x and y position.
     * The object will have a width and height of 16 pixels, and a health of 1.
     * A new movement component will be created with a speed of 1 and a height equal to the object's height.
     * The dead sound will be set and initialized with a volume of -30 dB.
     * If there is no bulletTimer and enemyMoveTimer, a new instance of each will be created.
     *
     * @param x The x position of the AbsEnemy.
     * @param y The y position of the AbsEnemy.
     */
    public SpriteBoss(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (SpriteFactory) f;
        try {
            filter = new RescaleOp(0.9f,0,null);
            imageNormal = ImageIO.read(new File("src/res/sprites/Enemy/Enemy_Boss" + this.getWidth() * this.f.getScale() + "x.png"));
        } catch (IOException exception) {
            System.err.println("Image not found!");
            System.out.println("Make sure there are sprites in the map \"src/res/sprites/Enemy/\" called Enemy_Boss" + this.getWidth() * this.f.getScale() + "x.png");
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        Point pos = this.getMovementComponent().getPosition();
        if (this.getHealth() != currentHealth){
            imageNormal = filter.filter(imageNormal,null);
            currentHealth = this.getHealth();
        }
        g2d.drawImage(imageNormal, pos.x * scale, pos.y*scale, null);
    }
}
