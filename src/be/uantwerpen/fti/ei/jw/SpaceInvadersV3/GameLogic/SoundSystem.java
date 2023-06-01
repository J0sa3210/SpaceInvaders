package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import javax.sound.sampled.Clip;
import java.util.List;

public class SoundSystem {
    SoundComponent levelPassedSound, gameOverSound, victorySound, backgroundMusic;

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

    private void loop(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        soundComponent.setLooped(true);
    }

    private void play(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.start();
        soundComponent.setMustPlay(false);
    }

    private void stop(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.stop();
        soundComponent.setMustStop(false);
        soundComponent.setLooped(false);
    }

    private void reset(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.stop();
        clip.setFramePosition(0);
    }

    public void playVictorySound() {
        victorySound.getClip().start();
        victorySound.getClip().drain();
        victorySound.getClip().setFramePosition(0);

    }

    public void playGameOverSound() {
        gameOverSound.getClip().start();
        gameOverSound.getClip().drain();
        gameOverSound.getClip().setFramePosition(0);
    }

    public void playLevelPassedSound() {
        levelPassedSound.getClip().start();
        levelPassedSound.getClip().drain();
        levelPassedSound.getClip().setFramePosition(0);
    }

    public void startBackgroundMusic() {
        backgroundMusic.getClip().start();
        loop(backgroundMusic);
    }
}
