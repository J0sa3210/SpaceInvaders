package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.util.LinkedList;


public abstract class AbsScoreBoard {
    private final LinkedList<AbsPlayer> players;
    private final LinkedList<ScoreBoardStruct> scoreBoardStructs;

    public AbsScoreBoard(LinkedList<AbsPlayer> players) {
        this.players = players;
        scoreBoardStructs = new LinkedList<>();
        for (AbsPlayer player : players) {
            scoreBoardStructs.add(new ScoreBoardStruct(player.getPlayerName(), player.getPoints(), player.getHealth()));
        }
    }

    // Getters & setters
    public LinkedList<ScoreBoardStruct> getScoreBoardStructs() {
        return scoreBoardStructs;
    }

    public void update() {
        int i = 0;
        for (AbsPlayer player : players) {
            scoreBoardStructs.set(i++, new ScoreBoardStruct(player.getPlayerName(), player.getPoints(), player.getHealth()));
        }
    }

    public abstract void visualize();

    public abstract void createScoreBoard(int cellSize, int width);
}
