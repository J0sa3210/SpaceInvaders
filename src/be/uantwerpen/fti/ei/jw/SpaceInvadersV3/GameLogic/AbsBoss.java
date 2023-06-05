package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;

public abstract class AbsBoss extends AbsEnemy {

    /**
     * Constructs a new AbsBoss object with given x and y position.
     * This enemy is 64 cells wide and high and also has 20 health points
     * The boss will also shoot 3 bullets like a shotgun and has a 50% chance of firing
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