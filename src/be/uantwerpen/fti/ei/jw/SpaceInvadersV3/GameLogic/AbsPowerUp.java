package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An <code>AbsPowerUp</code> is an abstract class that represents a power-up object in the game.
 *
 * <p>
 * It extends the {@link AbsCreature} class and inherits its properties.
 * This class contains methods and fields related to power-ups in the game.
 * </p>
 */
public abstract class AbsPowerUp extends AbsCreature {
    private final SoundComponent deadSound;

    /**
     * List of strings containing the possible power-ups the player can receive for killing this object
     */
    ArrayList<String> powerUps = new ArrayList<>(List.of("doubleMovementSpeed", "doubleShootingSpeed", "extraLife", "Shotgun", "halveMovementSpeed"));
    String powerUp;

    /**
     * Constructs a new {@code AbsPowerUp} object with the specified coordinates.
     *
     * @param x The x-coordinate of the power-up
     * @param y The y-coordinate of the power-up
     */
    public AbsPowerUp(int x, int y) {
        // Set AbsEntity values
        this.setWidth(32);
        this.setHeight(16);

        // Set AbsCreature
        this.setHealth(2);
        this.setMovementComponent(new MovementComponent(x, y, 5, 0));

        // Choose a random powerUp
        Random random = new Random();
        this.powerUp = powerUps.get(random.nextInt(powerUps.size()));

        // Set sound
        this.deadSound = new SoundComponent("src/res/sounds/Powerup_dead.wav");
    }

    /**
     * Visualizes the power-up object.
     * This method must be implemented by the subclasses.
     */
    @Override
    public abstract void visualize();

    /**
     * Returns the type of power-up.
     *
     * @return The type of power-up
     */
    public String getPowerUp() {
        return powerUp;
    }

    /**
     * Returns the sound component associated with the power-up's death.
     *
     * @return The sound component of the power-up's death
     */
    public SoundComponent getDeadSound() {
        return deadSound;
    }
}