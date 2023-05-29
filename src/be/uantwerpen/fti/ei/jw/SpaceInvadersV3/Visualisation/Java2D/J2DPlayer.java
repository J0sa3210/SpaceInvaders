package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

public class J2DPlayer extends AbsPlayer {
    J2DFactory f;

    public J2DPlayer(int x, int y, String playerName, AbsFactory f, AbsInput input) {
        super(x, y, playerName, input);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(getOwnColor());
        Point pos = getMovementComponent().getPosition();
        g2d.fillRect((int) (pos.getX() * scale), (int) (pos.getY() * scale), this.getWidth() * scale, this.getHeight() * scale);
    }
}
