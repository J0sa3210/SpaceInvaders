package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsBoss;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

public class J2DBoss extends AbsBoss {
    J2DFactory f;

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
    public J2DBoss(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();

        // THe color will become more and more white when the object is hit
        g2d.setColor(new Color(255, (20 - this.getHealth()) * 255 / 20, (20 - this.getHealth()) * 255 / 20));
        Point pos = this.getMovementComponent().getPosition();
        g2d.fillRect(pos.x * scale, pos.y * scale, this.getWidth() * scale, this.getHeight() * scale);
    }
}
