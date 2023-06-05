package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components;

import java.awt.*;


/**
 * The <code>MovementComponent</code> class represents the movement component of an object in a 2D space.
 * It tracks the position and speed of the object along the X and Y axes.
 */
public class MovementComponent {
    private final int speedY;
    private int posX, posY;
    private int speedX;

    /**
     * Constructs a new MovementComponent with the specified initial position and speed.
     *
     * @param posX   The initial X-coordinate of the object's position.
     * @param posY   The initial Y-coordinate of the object's position.
     * @param speedX The speed of the object along the X-axis.
     * @param speedY The speed of the object along the Y-axis.
     */
    public MovementComponent(int posX, int posY, int speedX, int speedY) {
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * Returns the current X-coordinate of the object's position.
     *
     * @return The X-coordinate of the object's position.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the X-coordinate of the object's position.
     *
     * @param posX The new X-coordinate of the object's position.
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns the current Y-coordinate of the object's position.
     *
     * @return The Y-coordinate of the object's position.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the Y-coordinate of the object's position.
     *
     * @param posY The new Y-coordinate of the object's position.
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Returns the speed of the object along the X-axis.
     *
     * @return The speed of the object along the X-axis.
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Sets the speed of the object along the X-axis.
     *
     * @param speedX The new speed of the object along the X-axis.
     */
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    /**
     * Returns the speed of the object along the Y-axis.
     *
     * @return The speed of the object along the Y-axis.
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Returns the current position of the object as a Point object.
     *
     * @return The current position of the object as a Point object.
     */
    public Point getPosition() {
        return new Point(posX, posY);
    }

    /**
     * Returns a string representation of the MovementComponent object.
     *
     * @return A string representation of the MovementComponent object.
     */
    @Override
    public String toString() {
        return "MovementComponent{" + "posX=" + posX + ", posY=" + posY + '}';
    }
}