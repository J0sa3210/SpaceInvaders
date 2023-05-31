package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import javax.sound.sampled.*;
import java.io.File;

public class SoundComponent {
    private Clip clip;
    private Boolean mustPlay, mustLoop, mustStop, isLooped;

    public SoundComponent(String url) {
        mustPlay = false;
        mustLoop = false;
        isLooped = false;
        mustStop = false;
        try {
            File soundFile = new File(url);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }

    public Clip getClip() {
        return clip;
    }

    public Boolean getMustPlay() {
        return mustPlay;
    }

    public void setMustPlay(Boolean mustPlay) {
        this.mustPlay = mustPlay;
    }

    public Boolean getMustLoop() {
        return mustLoop;
    }

    public Boolean getLooped() {
        return isLooped;
    }

    public void setLooped(Boolean looped) {
        isLooped = looped;
    }

    public Boolean getMustStop() {
        return mustStop;
    }

    public void setMustStop(Boolean mustStop) {
        this.mustStop = mustStop;
    }

    public void adjustVolume(float volume){
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume); // Reduce volume by 10 decibels.
    }

    @Override
    public String toString() {
        return "SoundComponent{" +
                "clip=" + clip +
                ", mustPlay=" + mustPlay +
                ", mustLoop=" + mustLoop +
                ", mustStop=" + mustStop +
                ", isLooped=" + isLooped +
                '}';
    }
}
