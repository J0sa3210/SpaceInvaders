package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbsPowerUp extends AbsCreature {
    ArrayList<String> powerUps = new ArrayList<>(List.of("doubleMovementSpeed", "doubleShootingSpeed", "extraLife", "Mitraillette", "halveMovementSpeed"));
    String powerUp;

    public AbsPowerUp(int x, int y) {
        this.setMovementComponent(new MovementComponent(x, y, 5, 0));
        this.setWidth(30);
        this.setHeight(20);
        this.setHealth(2);
        // Choose a random powerUp
        Random random = new Random();
        this.powerUp = powerUps.get(random.nextInt(powerUps.size()));
    }

    @Override
    public abstract void visualize();

    public String getPowerUp() {
        return powerUp;
    }
}
