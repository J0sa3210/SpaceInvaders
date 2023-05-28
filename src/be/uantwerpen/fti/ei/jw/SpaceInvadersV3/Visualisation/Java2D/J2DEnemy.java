package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;



import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

public class J2DEnemy extends AbsEnemy {
    J2DFactory f;

    public J2DEnemy(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.GREEN);
        g2d.fillRect(this.getX() * scale, this.getY() * scale, (this.getWidth() * scale), (this.getHeight() * scale));

    }
}
