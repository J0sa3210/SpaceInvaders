package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

public abstract class AbsPlayer extends AbsCreature {
    public static int height;
    private final String playerName;
    private final Timer shootTimer;
    private boolean hasDoubleMovementSpeed;
    private boolean hasDoubleShootingSpeed;
    private boolean hasMitraillette;
    private boolean hasHalveMovementSpeed;
    private int shootingBonus;
    private double movementBonus;
    private int points;
    private Timer powerUpTimer;

    public AbsPlayer(int x, int y, String playerName) {
        this.setX(x);
        this.setY(y);
        height = 15;
        this.setWidth(15);
        this.setHeight(height);
        this.setSpeed(2);
        this.setHealth(3);
        this.setDead(false);
        this.hasDoubleMovementSpeed = false;
        this.hasDoubleShootingSpeed = false;
        this.hasMitraillette = false;
        this.hasHalveMovementSpeed = false;
        this.shootingBonus = 1;
        this.movementBonus = 1;
        this.points = 0;
        this.playerName = playerName;
        this.shootTimer = new Timer();
    }

    public abstract boolean shoots();

    public void getPowerUp(String powerup) {
        switch (powerup) {
            case "extraLife":
                if (this.getHealth() < 5) {
                    this.addExtraLife();
                }
                break;
            case "doubleMovementSpeed":
                powerUpTimer = new Timer();
                this.hasDoubleMovementSpeed = true;
                break;
            case "doubleShootingSpeed":
                powerUpTimer = new Timer();
                this.hasDoubleShootingSpeed = true;
                break;
            case "Mitraillette":
                powerUpTimer = new Timer();
                this.hasMitraillette = true;
                break;
            case "halveMovementSpeed":
                powerUpTimer = new Timer();
                this.hasHalveMovementSpeed = true;
                break;
        }
    }

    public void addExtraLife() {
        this.setHealth(this.getHealth() + 1);
    }

    public void checkPowerUp() {

        if (powerUpTimer == null) {
            this.hasDoubleShootingSpeed = false;
            this.hasMitraillette = false;
            this.hasDoubleMovementSpeed = false;
            this.hasHalveMovementSpeed = false;
            this.shootingBonus = 1;
            this.movementBonus = 1;

        } else if (hasDoubleShootingSpeed) {
            if (powerUpTimer.getTime() <= 10000) {
                this.shootingBonus = 2;
            } else {
                this.hasDoubleShootingSpeed = false;
                this.powerUpTimer = null;
                this.shootingBonus = 1;
            }
        } else if (hasMitraillette) {
            if (powerUpTimer.getTime() <= 10000) {
                this.shootingBonus = 5;
            } else {
                this.hasMitraillette = false;
                this.powerUpTimer = null;
                this.shootingBonus = 1;
            }
        } else if (hasDoubleMovementSpeed) {
            if (powerUpTimer.getTime() <= 10000) {
                this.movementBonus = 2;
            } else {
                this.hasDoubleMovementSpeed = false;
                this.powerUpTimer = null;
                this.movementBonus = 1;
            }
        } else if (hasHalveMovementSpeed) {
            if (powerUpTimer.getTime() <= 10000) {
                this.movementBonus = 0.5;
            } else {
                this.hasHalveMovementSpeed = false;
                this.powerUpTimer = null;
                this.movementBonus = 1;
            }
        }
    }

    public int getShootingBonus() {
        return shootingBonus;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void resetPowerup() {
        this.powerUpTimer = null;
        //this.hasDoubleMovementSpeed = false;
        //this.hasMitraillette = false;
        //this.hasDoubleShootingSpeed = false;
    }

    public Timer getShootTimer() {
        return this.shootTimer;
    }

    public double getMovementBooster() {
        return this.movementBonus;
    }

}
