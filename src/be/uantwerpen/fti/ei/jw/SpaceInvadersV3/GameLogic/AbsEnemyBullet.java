package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

import java.awt.*;

/**
 * An <code>AbsEnemyBullet</code> is an abstract class that represents an enemy bullet.
 * It extends the {@link AbsBullet} class.
 */
public abstract class AbsEnemyBullet extends AbsBullet {

    /**
     * Constructs a new {@link AbsEnemyBullet} object with the specified enemy.
     *
     * @param e the enemy object from which the bullet is fired
     *
     * @see AbsEnemy
     */
    public AbsEnemyBullet(AbsEnemy e) {
        this.setWidth(2);
        this.setHeight(6);
        Point ePos = e.getMovementComponent().getPosition();
        this.setMovementComponent(new MovementComponent(ePos.x + e.getWidth() / 2 - this.getWidth() / 2, ePos.y + e.getHeight(), 0, 5));
        this.setDamage(1);
        setSound(new SoundComponent("src/res/sounds/EnemyBullet_shoot.wav"));
        this.getSound().adjustVolume(-20f);
    }
}





