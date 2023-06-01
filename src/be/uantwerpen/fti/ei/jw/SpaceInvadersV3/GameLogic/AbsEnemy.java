package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * <code>AbsEnemy</code> is an abstract class that contains all the methods and variables for an {@link AbsCreature} to be an enemy.
 * <p>
 * An <code>AbsEnemy</code> will move from side to side while slowly descending.
 * Meanwhile it will shoot {@link AbsEnemyBullet} to the {@link AbsPlayer}s.
 * </p>
 *
 * @see AbsCreature
 * @see AbsEnemyBullet
 * @see AbsPlayer
 */
public abstract class AbsEnemy extends AbsCreature {
    private static Timer bulletTimer, enemyMoveTimer;
    private SoundComponent deadSound;

    public AbsEnemy(int x, int y) {
        this.setWidth(16);
        this.setHeight(16);
        this.setMovementComponent(new MovementComponent(x, y, 1, this.getHeight()));
        this.setHealth(1);
        this.deadSound = new SoundComponent("src/res/sounds/Enemy_dead.wav");
        this.deadSound.adjustVolume(-30f);
        if (bulletTimer == null) {
            bulletTimer = new Timer();
        }
        if (enemyMoveTimer == null) {
            enemyMoveTimer = new Timer();
        }
    }

    /**
     * Return the {@link Timer} that determines when the enemy can shoot.
     *
     * @return A {@link Timer} object.
     */
    public static Timer getBulletTimer() {
        return bulletTimer;
    }

    /**
     * Return the {@link Timer} that determines when the enemy can move horizontally.
     *
     * @return A {@link Timer} object.
     */
    public static Timer getEnemyMoveTimer() {
        return enemyMoveTimer;
    }

    public SoundComponent getDeadSound() {
        return deadSound;
    }
}
