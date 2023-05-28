package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPowerUp;

import java.awt.*;

public class J2DPowerUp extends AbsPowerUp {
    J2DFactory f;
    public J2DPowerUp(int x, int y, AbsFactory f) {
        super(x, y);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.BLUE);
        if (this.getHealth() == 1){
            g2d.setColor(Color.CYAN);
        }
        g2d.fillRect(this.getX() * scale, this.getY() * scale, (int) (this.getWidth() * scale), (int) (this.getHeight() * scale));
    }
}
