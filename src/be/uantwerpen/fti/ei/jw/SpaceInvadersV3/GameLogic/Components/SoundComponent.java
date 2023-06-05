package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components;

import javax.sound.sampled.*;
import java.io.File;

/**
 * The <code>SoundComponent</code> class represents a component that handles sound effects in the game.
 * It encapsulates a {@link Clip} object and provides methods to control the playback and settings of the sound.
 */
public class SoundComponent {
    private final Boolean mustLoop;
    private Clip clip;
    private Boolean mustPlay;
    private Boolean mustStop;
    private Boolean isLooped;

    /**
     * Constructs a SoundComponent object with the specified sound file URL.
     *
     * @param url The URL of the sound file.
     */
    public SoundComponent(String url) {
        mustPlay = true;
        mustLoop = false;
        isLooped = false;
        mustStop = false;
        try {
            File soundFile = new File(url);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {
// Handle exception
        }
    }

    /**
     * Retrieves the Clip object associated with this SoundComponent.
     *
     * @return The Clip object.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Checks if the sound must be played.
     *
     * @return true if the sound must be played, false otherwise.
     */
    public Boolean getMustPlay() {
        return mustPlay;
    }

    /**
     * Sets whether the sound must be played.
     *
     * @param mustPlay true if the sound must be played, false otherwise.
     */
    public void setMustPlay(Boolean mustPlay) {
        this.mustPlay = mustPlay;
    }

    /**
     * Checks if the sound must be looped.
     *
     * @return true if the sound must be looped, false otherwise.
     */
    public Boolean getMustLoop() {
        return mustLoop;
    }

    /**
     * Checks if the sound is currently looped.
     *
     * @return true if the sound is looped, false otherwise.
     */
    public Boolean getLooped() {
        return isLooped;
    }

    /**
     * Sets whether the sound is looped.
     *
     * @param looped true if the sound should be looped, false otherwise.
     */
    public void setLooped(Boolean looped) {
        isLooped = looped;
    }

    /**
     * Checks if the sound must be stopped.
     *
     * @return true if the sound must be stopped, false otherwise.
     */
    public Boolean getMustStop() {
        return mustStop;
    }

    /**
     * Sets whether the sound must be stopped.
     *
     * @param mustStop true if the sound must be stopped, false otherwise.
     */
    public void setMustStop(Boolean mustStop) {
        this.mustStop = mustStop;
    }

    /**
     * Adjusts the volume of the sound.
     *
     * @param volume The volume level to set. Positive values increase the volume, negative values decrease it.
     */
    public void adjustVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }

    @Override
    public String toString() {
        return "SoundComponent{" + "clip=" + clip + ", mustPlay=" + mustPlay + ", mustLoop=" + mustLoop + ", mustStop=" + mustStop + ", isLooped=" + isLooped + '}';
    }
}

/*The `SoundComponent` class represents a component that handles sound effects in the game. It encapsulates a `Clip` object from the Java Sound API and provides methods to control the playback and settings of the sound.

The class has the following fields:
- `clip`: Represents the audio clip associated with the sound.
- `mustPlay`: Indicates whether the sound must be played.
- `mustLoop`: Indicates whether the sound must be looped.
- `mustStop`: Indicates whether the sound must be stopped.
- `isLooped`: Indicates whether the sound is currently looped.

The class provides the following methods:
- `SoundComponent(String url)`: Constructs a `SoundComponent` object with the specified sound file URL.
- `getClip()`: Retrieves the `Clip` object associated with this `SoundComponent`.
- `getMustPlay()`: Checks if the sound must be played.
- `setMustPlay(Boolean mustPlay)`: Sets whether the sound must be played.
- `getMustLoop()`: Checks if the sound must be looped.
- `getLooped()`: Checks if the sound is currently looped.
- `setLooped(Boolean looped)`: Sets whether the sound is looped.
- `getMustStop()`: Checks if the sound must be stopped.
- `setMustStop(Boolean mustStop)`: Sets whether the sound must be stopped.
- `adjustVolume(float volume)`: Adjusts the volume of the sound.
- `toString()`: Returns a string representation of the `SoundComponent` object.
*/
