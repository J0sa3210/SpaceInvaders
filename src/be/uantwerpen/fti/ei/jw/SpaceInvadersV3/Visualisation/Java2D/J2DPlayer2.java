package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer2;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

public class J2DPlayer2 extends AbsPlayer2 {
    J2DFactory f;

    public J2DPlayer2(int x, int y, String playerName, AbsFactory f, AbsInput input) {
        super(x,y,playerName,input);
        this.f = (J2DFactory) f;
    }

    @Override
    public void visualize() {
        Graphics2D g2d = f.getG2d();
        int scale = f.getScale();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(this.getX() * scale, this.getY() * scale, this.getWidth() * scale, this.getHeight() * scale);
    }
}
