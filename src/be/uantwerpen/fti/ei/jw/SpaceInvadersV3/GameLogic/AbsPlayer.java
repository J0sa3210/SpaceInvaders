package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

/**
 * The <code>AbsPlayer</code> class is an abstract class representing a player in the game.
 *
 * <p>
 * This class extends the `AbsCreature` class and provides additional functionality specific to players.
 * </p>
 */
public abstract class AbsPlayer extends AbsCreature {

    // Possible colors
    private static final Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.ORANGE};
    private static int playerId;
    private final AbsInput input;
    // Values concerning unique values
    private final String playerName;
    private final int ownId;
    private final Color ownColor;
    // Timers
    private final Timer shootTimer;
    private int points;
    // Booleans concerning powers
    private boolean hasDoubleMovementSpeed;
    private boolean hasDoubleShootingSpeed;
    private boolean hasShotgun;
    private boolean hasHalveMovementSpeed;
    private int shootingBonus;
    private double movementBonus;
    private Timer powerUpTimer;

    /**
     * Creates a new player object with the specified coordinates, player name, and input.
     *
     * @param x          The x-coordinate of the player
     * @param y          The y-coordinate of the player
     * @param playerName The name of the player
     * @param input      The input object for the player
     */
    public AbsPlayer(int x, int y, String playerName, AbsInput input) {
        this.setMovementComponent(new MovementComponent(x, y, 2, 2));
        this.input = input;

        // Set AbsEntity settings
        this.setWidth(16);
        this.setHeight(16);
        this.setHealth(3);
        this.setDead(false);

        // Initialise powers
        this.hasDoubleMovementSpeed = false;
        this.hasDoubleShootingSpeed = false;
        this.hasShotgun = false;
        this.hasHalveMovementSpeed = false;
        this.shootingBonus = 1;
        this.movementBonus = 1;

        // Initialise unique values
        this.points = 0;
        ownId = playerId;
        playerId++;
        ownColor = colors[ownId];
        this.playerName = playerName;

        // Initialise timer
        this.shootTimer = new Timer();  // Timer will make sure each player can shoot separately
    }

    /**
     * Sets the player ID.
     *
     * @param playerId The player ID to set
     */
    public static void setPlayerId(int playerId) {
        AbsPlayer.playerId = playerId;
    }

    /**
     * Retrieves the shooting bonus for the player.
     *
     * @return The shooting bonus
     */
    public int getShootingBonus() {
        return shootingBonus;
    }

    /**
     * Adds the specified number of points to the player's total points.
     *
     * @param points The number of points to add
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Retrieves the total points earned by the player.
     *
     * @return The total points earned
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Resets the power-up status of the player.
     */
    public void resetPowerup() {
        this.powerUpTimer = null;
        this.hasDoubleMovementSpeed = false;
        this.hasShotgun = false;
        this.hasDoubleShootingSpeed = false;
    }

    /**
     * Retrieves the shoot timer object.
     * <p>
     * This Timer makes sure every player cannot shoot too much.
     * Every AbsPlayer has it's own shootTimer in order to shoot independently from other players
     * </p>
     *
     * @return The shoot timer object
     */
    public Timer getShootTimer() {
        return this.shootTimer;
    }

    /**
     * Retrieves the movement booster value for the player.
     *
     * @return The movement booster value
     */
    public double getMovementBooster() {
        return this.movementBonus;
    }

    /**
     * Checks if the player is currently shooting.
     *
     * @return `true` if the player is shooting, `false` otherwise
     */
    public boolean shoots() {
        return input.getShootPressed().get(ownId);
    }

    /**
     * Check if player currently has the shotgun power-up active.
     *
     * <p>
     *     This is used to change the picture of the player
     * </p>
     *
     * @return 'true' if the player has the shotgun power-up active, 'false' otherwise
     */
    public boolean hasShotgun() {
        return this.hasShotgun;
    }

    /**
     * Retrieves the unique ID of the player.
     *
     * @return The player ID
     */
    public int getOwnId() {
        return ownId;
    }

    /**
     * Retrieves the unique color associated with the player.
     *
     * @return The player color
     */
    public Color getOwnColor() {
        return ownColor;
    }

    /**
     * Pauses the timers associated with the player.
     */
    public void pauseTimers() {
        if (powerUpTimer != null) {
            powerUpTimer.pause();
        }
        shootTimer.pause();
    }

    /**
     * Starts the timers associated with the player.
     */
    public void startTimers() {
        if (powerUpTimer != null) {
            powerUpTimer.start();
        }
        shootTimer.start();
    }

    /**
     * Checks the power-up status of the player and updates the bonuses accordingly.
     */
    public void checkPowerUp() {
        if (powerUpTimer == null) { // Resets all the values
            this.hasDoubleShootingSpeed = false;
            this.hasShotgun = false;
            this.hasDoubleMovementSpeed = false;
            this.hasHalveMovementSpeed = false;
            this.shootingBonus = 1;
            this.movementBonus = 1;
        } else if (hasDoubleShootingSpeed) {    // For 5 seconds, the player will be able to shoot double as often
            if (powerUpTimer.getTime() <= 5000) {
                this.shootingBonus = 2;
            } else {    // Reset the power-up values
                this.hasDoubleShootingSpeed = false;
                this.powerUpTimer = null;
                this.shootingBonus = 1;
            }
        } else if (hasShotgun) {        // For 4 seconds, the player will shoot 3 bullets in a cone
            if (powerUpTimer.getTime() <= 4000) {
                this.hasShotgun = true;
            } else {
                this.hasShotgun = false;
                this.powerUpTimer = null;
                this.shootingBonus = 1;
            }
        } else if (hasDoubleMovementSpeed) {    // For 5 seconds, the player can move at double speed
            if (powerUpTimer.getTime() <= 5000) {
                this.movementBonus = 2;
            } else {
                this.hasDoubleMovementSpeed = false;
                this.powerUpTimer = null;
                this.movementBonus = 1;
            }
        } else if (hasHalveMovementSpeed) {     // For 10 seconds, the player can only move at half speed
            if (powerUpTimer.getTime() <= 10000) {
                this.movementBonus = 0.5;
            } else {
                this.hasHalveMovementSpeed = false;
                this.powerUpTimer = null;
                this.movementBonus = 1;
            }
        }
    }

    /**
     * Gets a power-up and applies its effects to the player.
     *
     * @param powerup The name of the power-up to get
     */
    public void getPowerUp(String powerup) {
        switch (powerup) {
            case "extraLife" -> {
                if (this.getHealth() < 5) {
                    this.addExtraLife();
                }
            }
            case "doubleMovementSpeed" -> {
                powerUpTimer = new Timer();
                this.hasDoubleMovementSpeed = true;
            }
            case "doubleShootingSpeed" -> {
                powerUpTimer = new Timer();
                this.hasDoubleShootingSpeed = true;
            }
            case "Shotgun" -> {
                powerUpTimer = new Timer();
                this.hasShotgun = true;
            }
            case "halveMovementSpeed" -> {
                powerUpTimer = new Timer();
                this.hasHalveMovementSpeed = true;
            }
        }
    }

    /**
     * Adds an extra life to the player.
     */
    public void addExtraLife() {
        this.setHealth(this.getHealth() + 1);
    }

    /**
     * Returns a string representation of the player object.
     *
     * @return A string representation of the player object
     */
    @Override
    public String toString() {
        return "AbsPlayer{" +
                "playerName='" + playerName +
                ", ownColor=" + ownColor +
                ", location=" + getMovementComponent().toString() +
                ", hasDoubleMovementSpeed = " + hasDoubleMovementSpeed +
                ", hasDoubleShootingSpeed=" + hasDoubleShootingSpeed +
                ", hasShotgun=" + hasShotgun +
                ", hasHalveMovementSpeed=" + hasHalveMovementSpeed +
                ", shootingBonus=" + shootingBonus +
                ", movementBonus=" + movementBonus +
                ", points=" + points +
                "}";
    }
}