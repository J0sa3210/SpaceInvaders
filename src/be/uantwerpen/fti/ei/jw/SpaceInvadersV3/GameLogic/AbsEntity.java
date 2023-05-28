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
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;

    /**
     * A function that will determine how and how fast the object will move vertically.
     *
     *
     */
    public abstract void move(int fieldWidth);

    public abstract void visualize();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the current Y coordinate of this object.
     *
     * @return The Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Set the current Y coordinate to a new value.
     *
     * @param y The new value for the X coordinate
     */
    public void setY(int y) {
        this.y = y;
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


    /**
     * Returns the current speed of this object
     *
     * @return The speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Set the current speed to a new value.
     *
     * @param speed The new value for the speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    /**
     * Function that moves the X coordinate by a certain amount.
     *
     * @param speedX The amount that the X coordinate will be moved.
     */
    public void moveX(int speedX) {
        this.setX(this.getX() + speedX);
    }

    /**
     * Function that moves the Y coordinate by a certain amount.
     *
     * @param speedY The amount that the Y coordinate will be moved.
     */
    public void moveY(int speedY) {
        this.setY(this.getY() + speedY);
    }
}
