package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class J2DGameFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int headingHeight;
    private int scoreBoardPanelWidth;

    public J2DGameFrame(Dimension frameDimension, int cellSize) {
        this.setFocusable(true);

        // Lay-out //
        // Visualization
        this.setPreferredSize(frameDimension);
        this.setMinimumSize(frameDimension);
        this.setMaximumSize(frameDimension);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        // Background color
        this.getContentPane().setBackground(Color.BLACK);

        // Dimensions
        frameWidth = this.getWidth();
        frameHeight = this.getHeight();


        // Heading
        headingHeight = (30 * cellSize);
        JPanel heading = new JPanel();
        heading.setBackground(Color.BLACK);
        heading.add(new J2DGameLabel("Space Invaders", 15*cellSize));
        heading.setBounds(frameWidth / 3, 0, frameWidth / 3, headingHeight);
        this.add(heading);
    }

    public void add(J2DScoreBoard scoreBoard) {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoard.scoreBoardPanels){
            JPanel scoreB = scoreBoardPanel;
            scoreB.setLocation(i++*frameWidth * 2 / 3, 0);
            scoreB.setVisible(true);
            this.add(scoreB);
        }
    }

    public void add(J2DGamePanel gamePanel) {
        JPanel panel = gamePanel.getGamePanel();
        Dimension gamePanelDimension = panel.getSize();
        panel.setLocation((frameWidth - gamePanelDimension.width) / 2, headingHeight);
        this.add(panel);
    }
}
