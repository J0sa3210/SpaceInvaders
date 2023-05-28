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
    private final SoundComponent soundComponent;

    public AbsPlayerBullet(AbsPlayer p) {
        this.setWidth(2);
        this.setHeight(5);
        this.setX((p.getX() + p.getWidth() / 2 - this.getWidth() / 2));
        this.setY(p.getY());
        this.setSpeed(5);
        this.setDamage(1);
        this.shooter = p;
        this.soundComponent = new SoundComponent("8-bit-explosion.wav");
    }

    /**
     * A function that checks if the bullet has hit a {@link AbsCreature}.
     *
     * @param creature The {@link AbsCreature} to check for, this can be either a {@link AbsEnemy} or an {@link AbsPowerUp}.
     * @return A boolean that will say if the bullet "has hit" the {@link AbsCreature}.
     */
    @Override
    public boolean hasHit(AbsCreature creature) {
        return this.getX() <= creature.getX() + creature.getWidth() && this.getX() >= creature.getX() && this.getY()+this.getHeight() <= creature.getY() + creature.getHeight() && (this.getY() + this.getHeight()) > creature.getY();
    }

    @Override
    public void move(int fieldWidth) {
        this.moveY(-this.getSpeed());
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

    public SoundComponent getSoundComponent(){
        return this.soundComponent;
    }
}
