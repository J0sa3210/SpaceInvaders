package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;

/**
 * An <code>AbsEntity</code> is an abstract class that represents an object that can be visualized and moved on the playing field.
 *
 * <p>
 * This class provides methods and variables for managing the visualization of an object. The actual visualization implementation is determined by the instance of the {@link AbsFactory} class.
 * </p>
 */
public abstract class AbsEntity {
    private MovementComponent movementComponent;
    private int width;
    private int height;

    /**
     * Abstract method to visualize the entity.
     * Each subclass must implement this method.
     */
    public abstract void visualize();

    /**
     * Retrieves the movement component of this entity.
     *
     * @return The movement component
     */
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    /**
     * Sets the movement component of this entity.
     *
     * @param movementComponent The new movement component
     */
    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    /**
     * Returns the current width of this entity.
     *
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of this entity.
     *
     * @param width The new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the current height of this entity.
     *
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of this entity.
     *
     * @param height The new height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}