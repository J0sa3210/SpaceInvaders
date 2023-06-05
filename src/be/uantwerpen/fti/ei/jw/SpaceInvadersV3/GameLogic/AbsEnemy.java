package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

/**
 * An <code>AbsEnemy</code> is an abstract class that contains all the methods and variables for an {@link AbsCreature} to be an enemy.
 * An AbsEnemy will move from side to side while slowly descending.
 * Meanwhile it will shoot {@link AbsEnemyBullet} to the {@link AbsPlayer}s.
 *
 * @see AbsCreature
 * @see AbsEnemyBullet
 * @see AbsPlayer
 */
public abstract class AbsEnemy extends AbsCreature {
    private static Timer bulletTimer;
    private static Timer enemyMoveTimer;
    private final SoundComponent deadSound;

    /**
     * Constructs a new AbsEnemy object with given x and y position.
     * The object will have a width and height of 16 pixels, and a health of 1.
     * A new movement component will be created with a speed of 1 and a height equal to the object's height.
     * The dead sound will be set and initialized with a volume of -30 dB.
     * If there is no bulletTimer and enemyMoveTimer, a new instance of each will be created.
     *
     * @param x The x position of the AbsEnemy.
     * @param y The y position of the AbsEnemy.
     */
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
     * Returns the bulletTimer that determines when the enemy can shoot.
     *
     * @return A Timer object.
     */
    public static Timer getBulletTimer() {
        return bulletTimer;
    }

    /**
     * Returns the enemyMoveTimer that determines when the enemy can move horizontally.
     *
     * @return A Timer object.
     */
    public static Timer getEnemyMoveTimer() {
        return enemyMoveTimer;
    }

    /**
     * Returns the dead sound of the enemy.
     *
     * @return A SoundComponent object containing the sound of the enemy dying.
     */
    public SoundComponent getDeadSound() {
        return deadSound;
    }

    /**
     * Returns a String representation of the AbsEnemy object, including its location.
     *
     * @return A String representation of the AbsEnemy object.
     */
    @Override
    public String toString() {
        return "AbsEnemy{location: " + this.getMovementComponent().toString() + "}";
    }
}