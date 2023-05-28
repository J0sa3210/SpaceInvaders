package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;

public abstract class AbsPlayer1 extends AbsPlayer {
    private final AbsInput input;


    public AbsPlayer1(int x, int y, String playerName, AbsInput input) {
        super(x, y, playerName);
        this.input = input;
    }

    @Override
    public void move(int fieldWidth) {
        if (input.rightPressed1 && this.getX() + this.getWidth() < fieldWidth) {
            this.moveX((int) (this.getSpeed() * this.getMovementBooster()));
        }
        if (input.leftPressed1 && this.getX() > 0) {
            this.moveX((int) (-this.getSpeed() * this.getMovementBooster()));
        }
    }

    @Override
    public boolean shoots(){
        return input.shootPressed1;
    }
}
