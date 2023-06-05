package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsBoss;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

public class J2DBoss extends AbsBoss {
    J2DFactory f;

    /**
     * Constructs a new AbsBoss object with given x and y position.
     * This enemy is 64 cells wide and high and also has 20 health points
     * The boss will also shoot 3 bullets like a shotgun and has a 50% chance of firing
     *
     * @param x The x position of the AbsEnemy.
     * @param y The y position of the AbsEnemy.
     * @param f The {@link J2DFactory} that will draw the object
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
