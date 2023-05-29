package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import javax.swing.*;
import java.awt.*;

public class J2DGameFrame extends JFrame {
    private final int frameWidth;
    private final int headingHeight;

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
            scoreBoardPanel.setLocation(i++*frameWidth * 2 / 3, 0);
            scoreBoardPanel.setVisible(true);
            this.add(scoreBoardPanel);
        }
    }

    public void add(J2DGamePanel gamePanel) {
        JPanel panel = gamePanel.getGamePanel();
        Dimension gamePanelDimension = panel.getSize();
        panel.setLocation((frameWidth - gamePanelDimension.width) / 2, headingHeight);
        this.add(panel);
    }
}
