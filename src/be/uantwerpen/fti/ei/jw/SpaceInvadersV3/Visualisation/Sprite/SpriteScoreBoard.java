package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.ScoreBoardStruct;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * The <code>SpriteScoreBoard</code> class extends the AbsScoreBoard class and represents a sprite-based scoreboard in the Space Invaders game.
 */
public class SpriteScoreBoard extends AbsScoreBoard {
    private final LinkedList<JPanel> scoreBoardPanels;
    /**
     * Creates a new SpriteScoreBoard instance with the specified list of players.
     *
     * @param players The list of players.
     */
    public SpriteScoreBoard(LinkedList<AbsPlayer> players) {
        super(players);
        scoreBoardPanels = new LinkedList<>();
    }

    /**
     * Creates the scoreboard panels based on the cell size and frame width.
     *
     * @param cellSize    The size of each cell.
     * @param frameWidth  The width of the frame.
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

            // Create the labels for name, health, and points
            SpriteLabel l1 = new SpriteLabel(struct.name(), 30);
            l1.setBounds(0, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
            l1.setHorizontalAlignment(SwingConstants.CENTER);
            l1.setOpaque(false);
            l1.setBackground(new Color(0, 0, 0, 0));
            SpriteLabel l2 = new SpriteLabel("Health: " + struct.health(), 25);
            l2.setBounds(tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
            l2.setOpaque(false);
            l2.setBackground(new Color(0, 0, 0, 0));
            SpriteLabel l3 = new SpriteLabel("Points: " + struct.points(), 25);
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
     * Updates the scoreboard by modifying the health and points labels.
     */
    @Override
    public void visualize() {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoardPanels) {
            try {
                // Update the health label
                JLabel label2 = (JLabel) scoreBoardPanel.getComponent(1);
                label2.setText("Health: " + getScoreBoardStructs().get(i).health());

                // Update the points label
                JLabel label1 = (JLabel) scoreBoardPanel.getComponent(2);
                label1.setText("Points: " + getScoreBoardStructs().get(i++).points());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    /**
     * Returns the list of scoreboard panels.
     *
     * @return The list of scoreboard panels.
     */
    public LinkedList<JPanel> getScoreBoardPanels() {
        return scoreBoardPanels;
    }
}
