package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.awt.*;

public class MovementComponent {
    private int posX, posY;
    private int speedX, speedY;

    public MovementComponent(int posX, int posY, int speedX, int speedY) {
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Point getPosition() {
        return new Point(posX, posY);
    }
}
