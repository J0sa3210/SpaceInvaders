package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

public abstract class AbsPlayer2 extends AbsPlayer {
    private final AbsInput input;

    public AbsPlayer2(int x, int y, String playerName, AbsInput input) {
        super(x, y, playerName);
        this.input = input;
    }

    @Override
    public void move(int fieldWidth) {
        if (input.rightPressed2 && this.getX() + this.getWidth() < fieldWidth) {
            this.moveX((int) (this.getSpeed() * this.getMovementBooster()));
        }
        if (input.leftPressed2 && this.getX() > 0) {
            this.moveX((int) (-this.getSpeed() * this.getMovementBooster()));
        }
    }

    @Override
    public boolean shoots(){
        return input.shootPressed2;
    }
}
