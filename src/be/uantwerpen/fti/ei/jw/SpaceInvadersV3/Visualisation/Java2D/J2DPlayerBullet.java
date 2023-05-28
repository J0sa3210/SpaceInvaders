package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayerBullet;

import java.awt.*;

public class J2DPlayerBullet extends AbsPlayerBullet {
    J2DFactory f;

    public J2DPlayerBullet(AbsPlayer p, AbsFactory f) {
        super(p);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(this.getX() * scale, this.getY() * scale, (int) (this.getWidth() * scale), (int) (this.getHeight() * scale));

    }
}
