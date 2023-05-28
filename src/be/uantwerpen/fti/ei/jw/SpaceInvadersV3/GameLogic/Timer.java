package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * An object that will keep the time.
 */
public class Timer {
    long startTime;

    /**
     * Constructor that will automatically hold the time
     */
    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Method that will reset the timer
     */
    public void reset() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Show current time passed since the last reset or initialisation
     * @return long passed time
     */
    public long getTime() {
        return System.currentTimeMillis() - startTime;
    }
}
