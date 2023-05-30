package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.*;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;

public class SpriteFactory extends AbsFactory {
    SpriteVisualManager visualManager;
    Dimension fieldDimension;

    public SpriteFactory(int fieldWidth, int fieldHeight) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        visualManager = (SpriteVisualManager) createVisualManager(fieldWidth, fieldHeight);
    }

    @Override
    public AbsEnemyBullet createEnemyBullet(AbsEnemy enemy) {
        return new SpriteEnemyBullet(enemy, this);
    }

    @Override
    public AbsPowerUp createPowerUp(int x, int y) {
        return new SpritePowerUp(x, y, this);
    }

    @Override
    public AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players) {
        return new SpriteScoreBoard(players);
    }

    @Override
    public AbsEnemy createEnemy(int x, int y) {
        return new SpriteEnemy(x, y, this);
    }

    @Override
    public AbsPlayer createPlayer(int x, int y, String playername, AbsInput input) {
        return new SpritePlayer(x, y, playername, this, input);
    }

    @Override
    public AbsPlayerBullet createPlayerBullet(AbsPlayer p) {
        return new SpritePlayerBullet(p, this);
    }

    @Override
    public AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight) {
        return new SpriteVisualManager(fieldWidth, fieldHeight);
    }

    public SpriteVisualManager getVisualManager() {
        return visualManager;
    }

    public int getScale() {
        return visualManager.getCellSize();
    }

    public Graphics2D getG2d() {
        return visualManager.getG2d();
    }
}
