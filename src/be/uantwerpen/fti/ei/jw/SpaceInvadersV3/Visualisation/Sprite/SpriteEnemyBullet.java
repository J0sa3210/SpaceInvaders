package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemyBullet;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;

import java.awt.*;

public class SpriteEnemyBullet extends AbsEnemyBullet {
    SpriteFactory f;

    public SpriteEnemyBullet(AbsEnemy e, AbsFactory f) {
        super(e);
        this.f = (SpriteFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        double scale = f.getScale();
        g2d.setColor(Color.WHITE);
        Point pos = getMovementComponent().getPosition();
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), (int) (this.getWidth() * scale), (int) (this.getHeight() * scale));
    }
}
