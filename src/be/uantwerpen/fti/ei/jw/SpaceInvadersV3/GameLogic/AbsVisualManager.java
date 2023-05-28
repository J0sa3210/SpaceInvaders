package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

public abstract class AbsVisualManager {
    public AbsVisualManager() {

    }

    public abstract void setupGameEnv(AbsInput input, AbsScoreBoard scoreBoard);

    public abstract void render();
}
