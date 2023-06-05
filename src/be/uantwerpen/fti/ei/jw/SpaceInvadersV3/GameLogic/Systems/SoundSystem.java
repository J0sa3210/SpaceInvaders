package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Systems;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;

import javax.sound.sampled.Clip;
import java.util.List;

/**
 * The SoundSystem class manages the sound effects and background music in the game.
 * It contains instances of SoundComponent representing different sounds and provides methods to control and play them.
 */
public class SoundSystem {
    SoundComponent levelPassedSound, gameOverSound, victorySound, backgroundMusic;

    /**
     * Constructs a SoundSystem object and initializes the different sound components that will be used in the {@link be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game} itself.
     *
     * <p>
     * These sounds contain:
     * - A victory sound for when the player(s) complete all the levels
     * - A game over sound for when one of the players die or an enemy has touched the lower bound of the field
     * - A level passed sound for every time all enemies are eliminated and the players move up a new round
     * - Background music that will be looped throughout the game
     * </p>
     *
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game
     */
    public SoundSystem() {
        victorySound = new SoundComponent("src/res/sounds/Victory.wav");
        victorySound.adjustVolume(-15f);

        gameOverSound = new SoundComponent("src/res/sounds/Game_over.wav");
        gameOverSound.adjustVolume(-15f);

        levelPassedSound = new SoundComponent("src/res/sounds/Level_passed.wav");
        levelPassedSound.adjustVolume(-15f);

        backgroundMusic = new SoundComponent("src/res/sounds/BackgroundMusic.wav");
        backgroundMusic.adjustVolume(-23f);
    }

    /**
     * Updates the state of the sound components based on their settings.
     *
     * @param soundComponents The list of sound components to update.
     */
    public void update(List<SoundComponent> soundComponents) {
        for (SoundComponent soundComponent : soundComponents) {
            if (soundComponent.getMustPlay()) {
                play(soundComponent);
            }
            if (soundComponent.getMustLoop() && !soundComponent.getLooped()) {
                loop(soundComponent);
            }

            if (soundComponent.getMustStop()) {
                stop(soundComponent);
            }
        }
    }

    /**
     * This method makes sure a {@link Clip} is being looped
     *
     * @param soundComponent that contains the clip to be looped
     */
    private void loop(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        soundComponent.setLooped(true);
    }

    /**
     * This method starts a {@link Clip}
     *
     * @param soundComponent that contains the clip to be played
     */
    private void play(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.start();
        soundComponent.setMustPlay(false);
    }

    /**
     * This method stops a {@link Clip} from playing
     *
     * @param soundComponent that contains the clip to be stopped
     */
    private void stop(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.stop();
        soundComponent.setMustStop(false);
        soundComponent.setLooped(false);
    }

    /**
     * Plays the victory sound effect.
     */
    public void playVictorySound() {
        victorySound.getClip().start();
        victorySound.getClip().drain();
        victorySound.getClip().setFramePosition(0);
    }

    /**
     * Plays the game over sound effect.
     */
    public void playGameOverSound() {
        gameOverSound.getClip().start();
        gameOverSound.getClip().drain();
        gameOverSound.getClip().setFramePosition(0);
    }

    /**
     * Plays the level passed sound effect.
     */
    public void playLevelPassedSound() {
        levelPassedSound.getClip().start();
        levelPassedSound.getClip().drain();
        levelPassedSound.getClip().setFramePosition(0);
    }

    /**
     * Starts playing the background music in a loop.
     */
    public void startBackgroundMusic() {
        backgroundMusic.getClip().start();
        loop(backgroundMusic);
    }
}

/*

The `SoundSystem` class manages the sound effects and background music in the game. It has the following fields:
- `levelPassedSound`: Represents the sound component for the level passed sound effect.
- `gameOverSound`: Represents the sound component for the game over sound effect.
- `victorySound`: Represents the sound component for the victory sound effect.
- `backgroundMusic`: Represents the sound component for the background music.

The class provides the following methods:
- `SoundSystem()`: Constructs a `SoundSystem` object and initializes the sound components with their respective sound files.
- `update(List<SoundComponent> soundComponents)`: Updates the state of the sound components based on their settings.
- `loop(SoundComponent soundComponent)`: Starts looping the specified sound component.
- `play(SoundComponent soundComponent)`: Plays the specified sound component.
- `stop(SoundComponent soundComponent)`: Stops the specified sound component.
- `reset(SoundComponent soundComponent)`: Resets the playback of the specified sound component to the beginning.
- `playVictorySound()`: Plays the victory sound effect.
- `playGameOverSound()`: Plays the game over sound effect.
- `playLevelPassedSound()`: Plays the level passed sound effect.
- `startBackgroundMusic()`: Starts playing the background music in a loop.

Note: The `SoundComponent` class represents a component that handles individual sound effects and is used within the `SoundSystem` class.
 */
