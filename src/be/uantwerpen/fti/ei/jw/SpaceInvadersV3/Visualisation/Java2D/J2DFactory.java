package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.*;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;

public class J2DFactory extends AbsFactory {
    AbsInput input;
    J2DVisualManager visualManager;
    Dimension fieldDimension;

    public J2DFactory(int fieldWidth, int fieldHeight) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        visualManager = (J2DVisualManager) createVisualManager(fieldWidth, fieldHeight);
    }


    @Override
    public AbsEnemyBullet createEnemyBullet(AbsEnemy enemy) {
        return new J2DEnemyBullet(enemy, this);
    }

    @Override
    public AbsPowerUp createPowerUp(int x, int y) {
        return new J2DPowerUp(x, y, this);
    }

    @Override
    public AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players) {
        return new J2DScoreBoard(players);
    }


    @Override
    public AbsEnemy createEnemy(int x, int y) {
        return new J2DEnemy(x, y, this);
    }

    @Override
    public AbsPlayer createPlayer1(int x, int y, String playerName, AbsInput input) {
        return new J2DPlayer1(x, y, playerName, this, input);
    }

    @Override
    public AbsPlayer createPlayer2(int x, int y, String playerName, AbsInput input) {
        return new J2DPlayer2(x, y, playerName, this, input);
    }

    @Override
    public AbsPlayerBullet createPlayerBullet(AbsPlayer p) {
        return new J2DPlayerBullet(p, this);
    }

    @Override
    public void render() {
        visualManager.render();
    }

    @Override
    public AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight) {
        return new J2DVisualManager(fieldWidth, fieldHeight);
    }

    public J2DVisualManager getVisualManager() {
        return visualManager;
    }

    @Override
    public AbsInput getInput() {
        return input;
    }

    public int getScale() {
        return visualManager.cellSize;
    }

    public Graphics2D getG2d() {
        return visualManager.getG2d();
    }
}
