package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbsPowerUp extends AbsCreature {
    ArrayList<String> powerUps = new ArrayList<>(List.of("doubleMovementSpeed", "doubleShootingSpeed", "extraLife", "Mitraillette", "halveMovementSpeed"));
    String powerUp;
    boolean moveRight = false;

    public AbsPowerUp(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(30);
        this.setHeight(20);
        this.setSpeed(5);
        this.setHealth(2);
        // Choose a random powerUp
        Random random = new Random();
        this.powerUp = powerUps.get(random.nextInt(powerUps.size()));
    }

    @Override
    public void move(int fieldWidth) {
        if (this.getX() == 0 || this.getX() + this.getWidth() - 1 >= fieldWidth) {
            this.moveRight = !this.moveRight;
        }
        if (moveRight) {
            this.moveX(this.getSpeed());
        } else {
            this.moveX(-this.getSpeed());
        }
    }

    @Override
    public abstract void visualize();

    public String getPowerUp() {
        return powerUp;
    }
}
