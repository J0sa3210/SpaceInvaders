package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * An <code>AbsCreature</code> is an {@link AbsEntity} with health and the ability to die.
 *
 * @see AbsEntity
 */
public abstract class AbsCreature extends AbsEntity {
    /**
     * The amount of health points. If this amounts reaches 0, the <code>AbsCreature</code> is dead and removed from the game.
     */
    private int health;
    /**
     * This will indicate if the <code>AbsCreature</code> is dead or not.
     */
    private boolean isDead;

    /**
     * Return the amount of remaining health points.
     * @return The amount of remaining health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the amount of health points of this creature.
     * @param health The amount of health points until the creature is dead.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * This function will reduce the amount of health points of this creature with the amount of damage the {@link AbsBullet} does.
     * @param bullet The {@link AbsBullet} that hit the <code>AbsCreature</code>.
     *
     * @see AbsBullet
     */
    public void damagedBy(AbsBullet bullet) {
        this.health -= bullet.getDamage();
        if (this.health <= 0) {
            this.setDead(true);
        }
    }

    /**
     * Returns true if the creature is dead (if his health is smaller or equal to 0) and false if this is not the case.
     * @return The boolean that answers on the statement: "This creature is dead"
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Change te <code>isDead</code> boolean to the given parameter.
     * @param dead The new state of isDead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }
}
