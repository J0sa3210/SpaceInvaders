package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import javax.sound.sampled.Clip;
import java.util.List;

public class SoundSystem {

    private static void loop(SoundComponent soundComponent) {
        Clip clip = soundComponent.getClip();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        soundComponent.setLooped(true);
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
}
