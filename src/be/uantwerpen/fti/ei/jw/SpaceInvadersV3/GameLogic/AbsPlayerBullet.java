package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

import java.awt.*;

/**
 * An <code>AbsPlayerBullet</code> is an {@link AbsBullet} shot by an {@link AbsPlayer}.
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

        // Set AbsEntity values
        this.setWidth(2);
        this.setHeight(6);

        // Set AbsBullet values
        this.setDamage(1);
        setSound(new SoundComponent("src/res/sounds/PlayerBullet_shoot.wav"));
        this.getSound().adjustVolume(-15f);

        // Calculate position according to the shooting player
        this.shooter = p;
        Point pPos = p.getMovementComponent().getPosition();
        this.setMovementComponent(new MovementComponent((int) (pPos.getX() + p.getWidth() / 2 - this.getWidth() / 2), (int) pPos.getY(), 0, -5));

    }

    /**
     * Returns the {@link AbsPlayer} that shot the bullet.
     * <p>
     * This is used in other methods, for example to give the shooter of this bullet a power up after destroying an {@link AbsPowerUp} object.
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
