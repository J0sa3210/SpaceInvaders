package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.ScoreBoardStruct;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SpriteScoreBoard extends AbsScoreBoard {
    LinkedList<JPanel> scoreBoardPanels;
    Image hearthRed, hearthBlue;

    public SpriteScoreBoard(LinkedList<AbsPlayer> players) {
        super(players);
        scoreBoardPanels = new LinkedList<>();
        try {
            hearthRed = ImageIO.read(new File("src/res/sprites/Hearth_red.png"));
            hearthBlue = ImageIO.read(new File("src/res/sprites/Hearth_blue.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createScoreBoard(int cellSize, int frameWidth) {
        int scoreBoardHeight = 30 * cellSize;
        int scoreBoardWidth = frameWidth / 3;

        for (int i = 0; i < 2; i++) {
            JPanel tempScoreBoardPanel = new JPanel(null);
            tempScoreBoardPanel.setBounds(0, 0, scoreBoardWidth, scoreBoardHeight);
            tempScoreBoardPanel.setBackground(new Color(0,0,0,170));

            try {
                ScoreBoardStruct struct = getScoreBoardStructs().get(i);
                SpriteGameLabel l1 = new SpriteGameLabel(struct.name(), 30);
                l1.setBounds(0, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
                l1.setHorizontalAlignment(SwingConstants.CENTER);
                l1.setOpaque(true);
                SpriteGameLabel l2 = new SpriteGameLabel("Health: " + struct.health(), 25);
                l2.setBounds(tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
                l2.setOpaque(true);
                SpriteGameLabel l3 = new SpriteGameLabel("Points: " + struct.points(), 25);
                l3.setBounds(2 * tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
                l3.setOpaque(true);

                tempScoreBoardPanel.add(l1);
                tempScoreBoardPanel.add(l2);
                tempScoreBoardPanel.add(l3);
            } catch (IndexOutOfBoundsException ignored) {

            }
            scoreBoardPanels.add(tempScoreBoardPanel);
        }
    }

    @Override
    public void visualize() {
        // Ga over alle panels
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoardPanels) {
            try {
                JLabel label1 = (JLabel) scoreBoardPanel.getComponent(1);
                label1.setText("Points: " + getScoreBoardStructs().get(i).points());
                JLabel label2 = (JLabel) scoreBoardPanel.getComponent(2);
                label2.setText("Health: " + getScoreBoardStructs().get(i++).health());
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
    }
}

