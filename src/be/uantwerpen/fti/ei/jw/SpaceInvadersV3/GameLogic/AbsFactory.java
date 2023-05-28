package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;
import java.util.LinkedList;

public abstract class AbsFactory {

    public AbsFactory() {}

    //public abstract AbsPlayerBullet createPlayerBullet(AbsPlayer p);


    public abstract AbsEnemy createEnemy(int x, int y);

    public abstract AbsPlayer createPlayer1(int x, int y, String playerName, AbsInput input);


    public abstract AbsPlayer createPlayer2(int x, int y, String playerName, AbsInput input);


    public abstract AbsPlayerBullet createPlayerBullet(AbsPlayer p);


    public abstract AbsEnemyBullet createEnemyBullet(AbsEnemy e);


    public abstract AbsPowerUp createPowerUp(int x, int y);


    public abstract AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players);


    public abstract void render();

    public abstract AbsInput getInput();

    public abstract AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight);

    public abstract AbsVisualManager getVisualManager();

}
