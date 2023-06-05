package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

import java.awt.*;

/**
 * The <code>AbsBullet</code> class is an abstract representation of a bullet entity in a game.
 * It extends the {@link AbsEntity} class and contains fields and methods specific to bullets.
 */
public abstract class AbsBullet extends AbsEntity {

    /**
     * The amount of damage that this bullet deals.
     */
    private int damage;

    /**
     * The SoundComponent that represents the sound that this bullet makes when fired.
     */
    private SoundComponent sound;

    /**
     * Returns the amount of damage that this bullet deals.
     *
     * @return an integer representing the amount of damage this bullet deals.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the amount of damage that this bullet deals.
     *
     * @param damage an integer representing the amount of damage this bullet should deal.
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Determines whether or not this bullet has hit a particular creature.
     *
     * @param creature the creature to check if this bullet has hit.
     * @return true if this bullet has hit the creature, false otherwise.
     * @see AbsCreature
     */
    public boolean hasHit(AbsCreature creature) {
        Rectangle pos = new Rectangle(this.getMovementComponent().getPosition(), new Dimension(this.getWidth(), this.getHeight()));
        Rectangle pPos = new Rectangle(creature.getMovementComponent().getPosition(), new Dimension(creature.getWidth(), creature.getHeight()));
        return pos.intersects(pPos);
    }


    /**
     * Returns the SoundComponent that represents the sound that this bullet makes when fired.
     *
     * @return the SoundComponent that represents the sound that this bullet makes when fired.
     */
    public SoundComponent getSound() {
        return this.sound;
    }

    /**
     * Sets the SoundComponent that represents the sound that this bullet makes when fired.
     *
     * @param sound the SoundComponent that represents the sound that this bullet should make when fired.
     */
    public void setSound(SoundComponent sound) {
        this.sound = sound;
    }
}




