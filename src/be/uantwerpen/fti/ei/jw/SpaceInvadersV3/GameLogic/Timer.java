package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * An object that will keep the time.
 */
public class Timer {
    long startTime;
    long timePassed;
    long timeAtPause;
    boolean isPaused;

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
        timePassed = 0;
    }

    public void pause() {
        if (!isPaused) {
            timeAtPause = getTime();
            isPaused = true;
        }
    }

    public void start() {
        if (isPaused) {
            startTime = System.currentTimeMillis() - timeAtPause;
            isPaused = false;
        }
    }

    /**
     * Show current time passed since the last reset or initialisation
     *
     * @return long passed time
     */
    public long getTime() {
        timePassed = System.currentTimeMillis() - startTime;
        return timePassed;
    }
}
