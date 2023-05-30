package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteGameFrame extends JFrame {
    private final int frameWidth;
    private final int frameHeight;
    private final int headingHeight;

    public SpriteGameFrame(Dimension frameDimension, int cellSize, BufferedImage bgImage) {
        this.setFocusable(true);
        // Lay-out //
        // Visualization
        this.setPreferredSize(frameDimension);
        this.setMinimumSize(frameDimension);
        this.setMaximumSize(frameDimension);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        // Background image
        this.setContentPane(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, this);
            }
        });

        // Dimensions
        frameWidth = this.getWidth();
        frameHeight = this.getHeight();


        // Heading
        headingHeight = (30 * cellSize);
        JPanel heading = new JPanel();
        heading.setBackground(new Color(0,0,0,170));
        heading.add(new SpriteGameLabel("Space Invaders", 15 * cellSize));
        heading.setBounds(frameWidth / 3, 0, frameWidth / 3, headingHeight);
        this.add(heading);
    }

    public void add(SpriteScoreBoard scoreBoard) {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoard.scoreBoardPanels) {
            scoreBoardPanel.setLocation(i++ * frameWidth * 2 / 3, 0);
            scoreBoardPanel.setVisible(true);
            this.add(scoreBoardPanel);
        }
    }

    public void add(SpriteGamePanel gamePanel) {
        JPanel panel = gamePanel.getGamePanel();
        Dimension gamePanelDimension = panel.getSize();
        panel.setLocation((frameWidth - gamePanelDimension.width) / 2, headingHeight + ((frameHeight - headingHeight) - gamePanelDimension.height) / 2);
        this.add(panel);
    }
}
