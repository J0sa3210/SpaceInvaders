package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.awt.*;

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
    static boolean moveRight = false; // The direction in which all the enemies will travel
    static boolean moveDown = false; // Let all AbsEnemy objects now to move down next turn
    private static int height;
    private static Timer bulletTimer;
    private static Timer enemyMoveTimer;

    public AbsEnemy(int x, int y) {
        height = 15;
        this.setX(x);
        this.setY(y);
        this.setWidth(15);
        this.setHeight(height);
        this.setSpeed(1);
        this.setHealth(1);
        bulletTimer = new Timer();
        enemyMoveTimer = new Timer();
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

    /**
     * Return the height of this object.
     *
     * @return the height of this object.
     */
    public int getHeight() {
        return height;
    }
    
    public void move(int fieldWidth) {
        this.moveY(this.getHeight());
    }

    /**
     * A function that will move the object horizontally.
     * <p>
     * The direction is determined by the static boolean <code>moveRight</code>.
     * This will change once one of the outer <code>AbsEnemy</code> reaches the border of the field.
     * </p>
     */
    public void moveHor() {
        if (moveRight) {
            this.moveX(getSpeed());
        } else {
            this.moveX(-this.getSpeed());
        }
    }
}
