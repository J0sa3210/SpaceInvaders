package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.awt.*;

/**
 * <code>AbsPlayerBullet</code> is an {@link AbsBullet} shot by an {@link AbsPlayer}.
 * <p>
 * This type of {@link AbsBullet} has it's own dimension and speed.
 * This bullet will only check if it has hit an {@link AbsEnemy} or an {@link AbsPowerUp}.
 * </p>
 *
 * @see AbsBullet
 * @see AbsPlayer
 * @see AbsEnemy
 * @see AbsPowerUp
 */
public abstract class AbsPlayerBullet extends AbsBullet {
    private final AbsPlayer shooter;

    public AbsPlayerBullet(AbsPlayer p) {
        this.setWidth(2);
        this.setHeight(5);
        Point pPos = p.getMovementComponent().getPosition();
        this.setMovementComponent(new MovementComponent((int) (pPos.getX() + p.getWidth() / 2 - this.getWidth() / 2), (int) pPos.getY(), 0, -5));
        this.setDamage(1);
        this.shooter = p;
    }

    /**
     * A function that checks if the bullet has hit a {@link AbsCreature}.
     *
     * @param creature The {@link AbsCreature} to check for, this can be either a {@link AbsEnemy} or an {@link AbsPowerUp}.
     * @return A boolean that will say if the bullet "has hit" the {@link AbsCreature}.
     */
    @Override
    public boolean hasHit(AbsCreature creature) {
        Point pos = getMovementComponent().getPosition();
        Point cPos = creature.getMovementComponent().getPosition();
        return pos.getX() <= cPos.getX() + creature.getWidth() && pos.getX() >= cPos.getX() && pos.getY() + this.getHeight() <= cPos.getY() + creature.getHeight() && (pos.getY() + this.getHeight()) > cPos.getY();
    }

    /**
     * Returns the {@link AbsPlayer} that shot the bullet.
     * <p>
     * This is used in ohter methods, for example to give the shooter of this bullet a power up after destroying an {@link AbsPowerUp} object.
     * </p>
     *
     * @return The {@link AbsPlayer} that shot the bullet.
     * @see AbsPlayer
     * @see AbsPowerUp
     */
    public AbsPlayer getShooter() {
        return this.shooter;
    }
}
