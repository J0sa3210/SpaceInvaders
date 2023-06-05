package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import java.util.LinkedList;


/**
 * An <code>AbsScoreBoard</code> is an abstract class that represents a scoreboard in the game.
 *
 * <p>
 * It contains methods and fields related to managing and visualizing the scores of players.
 * </p>
 */
public abstract class AbsScoreBoard {
    private final LinkedList<AbsPlayer> players;
    private final LinkedList<ScoreBoardStruct> scoreBoardStructs;

    /**
     * Constructs a new {@code AbsScoreBoard} object with the specified list of players.
     *
     * @param players The list of players
     */
    public AbsScoreBoard(LinkedList<AbsPlayer> players) {
        this.players = players;


        //List of ScoreBoardStructs containing the values of the player to be visualized and updated
        scoreBoardStructs = new LinkedList<>();
        for (AbsPlayer player : players) {
            scoreBoardStructs.add(new ScoreBoardStruct(player.getPlayerName(), player.getPoints(), player.getHealth()));
        }
    }

    /**
     * Returns the list of score board structures.
     *
     * @return The list of score board structures
     */
    public LinkedList<ScoreBoardStruct> getScoreBoardStructs() {
        return scoreBoardStructs;
    }

    /**
     * Updates the score board with the current scores and health of the players.
     */
    public void update() {
        int i = 0;
        for (AbsPlayer player : players) {
            scoreBoardStructs.set(i++, new ScoreBoardStruct(player.getPlayerName(), player.getPoints(), player.getHealth()));
        }
    }

    /**
     * Visualizes the score board.
     * This method must be implemented by the subclasses.
     */
    public abstract void visualize();

    /**
     * Creates a score board with the specified cell size and width.
     *
     * @param cellSize The size of each cell in the score board
     * @param width    The width of the score board
     */
    public abstract void createScoreBoard(int cellSize, int width);
}
