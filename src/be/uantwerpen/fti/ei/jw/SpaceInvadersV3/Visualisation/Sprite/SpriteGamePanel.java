package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteGamePanel {
    private final JPanel gamePanel;
    private Graphics2D g2d;
    private BufferedImage g2dimage;

    public SpriteGamePanel(Dimension gamePanelDimension) {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D graph2d = (Graphics2D) g;
                Toolkit.getDefaultToolkit().sync(); // Synced buffers in order that all inconsistencies are eliminated
                graph2d.drawImage(g2dimage, 0, 0, null);   // copy buffered image
                graph2d.dispose();
                try {
                    if (g2d != null) {
                        g2d.setBackground(new Color(0, 0, 0, 0));
                        g2d.clearRect(0, 0, g2dimage.getWidth(), g2dimage.getHeight());
                    }
                } catch (NullPointerException ignored) {}
            }
        };
        gamePanel.setBackground(new Color(0, 0, 0, 0));
        gamePanel.setBounds(0, 0, gamePanelDimension.width, gamePanelDimension.height);
        gamePanel.setPreferredSize(gamePanelDimension);
        gamePanel.setDoubleBuffered(true);
        gamePanel.setOpaque(false);
        gamePanel.setVisible(true);
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void createG2D() {
        g2dimage = new BufferedImage(gamePanel.getSize().width, gamePanel.getSize().height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();
    }

    public Graphics2D getG2d() {
        return g2d;
    }
}
