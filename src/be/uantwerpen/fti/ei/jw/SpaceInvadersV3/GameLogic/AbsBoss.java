package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;

public abstract class AbsBoss extends AbsEnemy {

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
    public AbsBoss(int x, int y) {
        super(x, y);
        this.setHeight(64);
        this.setWidth(64);
        this.setHealth(20);
        this.setMovementComponent(new MovementComponent(x,y,1,this.getHeight()/4));
    }

    @Override
    public abstract void visualize();
}