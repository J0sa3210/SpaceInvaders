package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * An <code>AbsEntity</code> is an abstract class that can be visualized and move on the playing field.
 *
 * <p>
 * This object contains the methods and variables for an object to be visualized.
 * The real visualization is determined by the instance of the {@link AbsFactory}.
 * </p>
 */
public abstract class AbsEntity {
    private MovementComponent movementComponent;
    private int width;
    private int height;

    public abstract void visualize();

    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    /**
     * Returns the current width coordinate of this object.
     *
     * @return The width coordinate
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the current width coordinate to a new value.
     *
     * @param width The new value for the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the current height of this object.
     *
     * @return The height coordinate
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the current height to a new value.
     *
     * @param height The new value for the width
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
