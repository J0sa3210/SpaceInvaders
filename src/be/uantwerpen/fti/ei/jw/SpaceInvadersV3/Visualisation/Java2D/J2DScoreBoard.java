package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.ScoreBoardStruct;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The {@code J2DScoreBoard} class represents a score board in the Java 2D visualization of the Space Invaders game.
 * It extends the {@link AbsScoreBoard} class and is responsible for creating and visualizing the score board on the screen.
 */
public class J2DScoreBoard extends AbsScoreBoard {
    LinkedList<JPanel> scoreBoardPanels; // List of score board panels

    /**
     * Constructs a {@code J2DScoreBoard} object with the specified players.
     *
     * @param players The list of players to be displayed on the score board.
     */
    public J2DScoreBoard(LinkedList<AbsPlayer> players) {
        super(players);
        scoreBoardPanels = new LinkedList<>();
    }

    /**
     * Creates the score board with the specified cell size and frame width.
     *
     * @param cellSize    The size of each cell in the score board.
     * @param frameWidth  The width of the game frame.
     */
    @Override
    public void createScoreBoard(int cellSize, int frameWidth) {
        int scoreBoardHeight = 30 * cellSize;
        int scoreBoardWidth = frameWidth / 3;

        int i = 0;
        for (ScoreBoardStruct struct : getScoreBoardStructs()) {
            JPanel tempScoreBoardPanel = new JPanel(null);
            tempScoreBoardPanel.setBounds(0, 0, scoreBoardWidth, scoreBoardHeight);
            tempScoreBoardPanel.setBackground(new Color(0, 0, 0, 0));
            tempScoreBoardPanel.setOpaque(false);

            J2DLabel l1 = new J2DLabel(struct.name(), 30); // Create a label for the player's name
            l1.setBounds(0, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
            l1.setHorizontalAlignment(SwingConstants.CENTER);
            l1.setOpaque(false);
            l1.setBackground(new Color(0, 0, 0, 0));
            J2DLabel l2 = new J2DLabel("Health: " + struct.health(), 25); // Create a label for the player's health
            l2.setBounds(tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
            l2.setOpaque(false);
            l2.setBackground(new Color(0, 0, 0, 0));
            J2DLabel l3 = new J2DLabel("Points: " + struct.points(), 25); // Create a label for the player's points
            l3.setBounds(2 * tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
            l3.setOpaque(false);
            l3.setBackground(new Color(0, 0, 0, 0));

            tempScoreBoardPanel.add(l1);
            tempScoreBoardPanel.add(l2);
            tempScoreBoardPanel.add(l3);

            i++;
            scoreBoardPanels.add(tempScoreBoardPanel);
        }
    }

    /**
     * Visualizes the score board by updating the player information.
     */
    @Override
    public void visualize() {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoardPanels) {
            try {
                JLabel label2 = (JLabel) scoreBoardPanel.getComponent(1); // Get the label for health
                label2.setText("Health: " + getScoreBoardStructs().get(i).health()); // Update the health information
                JLabel label1 = (JLabel) scoreBoardPanel.getComponent(2); // Get the label for points
                label1.setText("Points: " + getScoreBoardStructs().get(i++).points()); // Update the points information
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Gets the list of score board panels.
     *
     * @return The list of score board panels.
     */
    public LinkedList<JPanel> getScoreBoardPanels() {
        return scoreBoardPanels;
    }
}
