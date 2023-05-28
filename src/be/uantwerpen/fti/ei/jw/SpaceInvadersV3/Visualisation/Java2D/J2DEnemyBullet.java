package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemyBullet;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

public class J2DEnemyBullet extends AbsEnemyBullet {
    J2DFactory f;

    public J2DEnemyBullet(AbsEnemy e, AbsFactory f) {
        super(e);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        double scale = f.getScale();
        g2d.setColor(Color.WHITE);
        g2d.fillRect((int) ( this.getX() * scale), (int)(this.getY() * scale),(int) (this.getWidth() * scale),(int) (this.getHeight() * scale));
    }
}
