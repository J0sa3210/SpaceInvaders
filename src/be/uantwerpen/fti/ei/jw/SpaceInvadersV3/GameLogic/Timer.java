package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * The <code>Timer</code> class represents an object that keeps track of time.
 *
 * <p>
 *     This object is used in the game for different purposes:
 *     - Controlling the moving speed of the enemies
 *     - Controlling when a players and enemies can shoot
 *     - Controlling when a powerup will spawn
 *     - ...
 * </p>
 *
 * @see AbsPlayer
 * @see AbsEnemy
 * @see AbsPowerUp
 */
public class Timer {
    // Time form where te Timer will sart measuring
    long startTime;
    // Time passed since the last reset or initialisation
    long timePassed;
    // Time recorded when the Timer was paused
    long timeAtPause;
    // The state of the Timer
    boolean isPaused;

    /**
     * Constructs a `Timer` object and automatically sets the start time.
     */
    public Timer() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Resets the timer by setting the start time to the current time and resetting the time passed.
     */
    public void reset() {
        this.startTime = System.currentTimeMillis();
        timePassed = 0;
    }

    /**
     * Pauses the timer by storing the time at the pause moment and updating the paused state.
     */
    public void pause() {
        if (!isPaused) {
            timeAtPause = getTime();
            isPaused = true;
        }
    }

    /**
     * Resumes the timer by updating the start time based on the time at pause and updating the paused state.
     */
    public void start() {
        if (isPaused) {
            startTime = System.currentTimeMillis() - timeAtPause;
            isPaused = false;
        }
    }

    /**
     * Returns the time passed since the last reset or initialization.
     *
     * @return The time passed in milliseconds.
     */
    public long getTime() {
        timePassed = System.currentTimeMillis() - startTime;
        return timePassed;
    }
}
/*
The `Timer` class represents an object that keeps track of time. It has the following fields:
- `startTime`: Represents the start time of the timer.
- `timePassed`: Represents the time passed since the last reset or initialization.
- `timeAtPause`: Represents the time recorded when the timer was paused.
- `isPaused`: Represents the paused state of the timer.

The class provides the following methods:
- `Timer()`: Constructs a `Timer` object and automatically sets the start time.
- `reset()`: Resets the timer by setting the start time to the current time and resetting the time passed.
- `pause()`: Pauses the timer by storing the time at the pause moment and updating the paused state.
- `start()`: Resumes the timer by updating the start time based on the time at pause and updating the paused state.
- `getTime()`: Returns the time passed since the last reset or initialization in milliseconds.

Note: The timer uses the `System.currentTimeMillis()` method to measure time.
 */