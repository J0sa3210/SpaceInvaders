package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.util.LinkedList;

public abstract class AbsFactory {

    public abstract AbsEnemy createEnemy(int x, int y);

    public abstract AbsPlayer createPlayer(int x, int y, String playername, AbsInput input);

    public abstract AbsPlayerBullet createPlayerBullet(AbsPlayer p);

    public abstract AbsEnemyBullet createEnemyBullet(AbsEnemy e);

    public abstract AbsPowerUp createPowerUp(int x, int y);

    public abstract AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players);

    public abstract AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight);

    public abstract AbsVisualManager getVisualManager();

}
