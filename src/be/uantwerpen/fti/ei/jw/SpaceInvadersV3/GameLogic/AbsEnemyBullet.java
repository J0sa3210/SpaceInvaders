package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.awt.*;

public abstract class AbsEnemyBullet extends AbsBullet {

    public AbsEnemyBullet(AbsEnemy e) {
        this.setWidth(2);
        this.setHeight(5);
        Point ePos = e.getMovementComponent().getPosition();
        this.setMovementComponent(new MovementComponent(ePos.x + e.getWidth() / 2 - this.getWidth()/2,ePos.y + e.getHeight(),0,5));
        this.setDamage(1);
    }

    @Override
    public boolean hasHit(AbsCreature player) {
        Point pos = this.getMovementComponent().getPosition();
        Point pPos = player.getMovementComponent().getPosition();
        return pos.getX() >= pPos.getX() && pos.getX() - pPos.getX() <= player.getWidth() && pos.getY() >= pPos.getY() && pos.getY() <= pPos.getY() + player.getHeight();
    }
}
