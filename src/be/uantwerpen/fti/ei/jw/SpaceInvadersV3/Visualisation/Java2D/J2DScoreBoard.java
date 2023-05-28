package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;


import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.ScoreBoardStruct;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class J2DScoreBoard extends AbsScoreBoard {
    LinkedList<JPanel> scoreBoardPanels;

    public J2DScoreBoard(LinkedList<AbsPlayer> players) {
        super(players);
        scoreBoardPanels = new LinkedList<>();
    }

    @Override
    public void createScoreBoard(int cellSize, int frameWidth) {
        int scoreBoardHeight = 30 * cellSize;
        int scoreBoardWidth = frameWidth / 3;

        int labelHeight = 100;
        int labelWidth = scoreBoardWidth;

        for (int i = 0; i < 2; i++) {
            JPanel tempScoreBoardPanel = new JPanel(null);
            tempScoreBoardPanel.setBounds(0, 0, scoreBoardWidth, scoreBoardHeight);
            tempScoreBoardPanel.setBackground(Color.BLACK);

            try {
                ScoreBoardStruct struct = getScoreBoardStructs().get(i);
                J2DGameLabel l1 = new J2DGameLabel(struct.name(), 30);
                l1.setBounds(0, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
                l1.setHorizontalAlignment(SwingConstants.CENTER);
                J2DGameLabel l2 = new J2DGameLabel("Health: " + struct.health(), 25);
                l2.setBounds(tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());
                J2DGameLabel l3 = new J2DGameLabel("Points: " + struct.points(), 25);
                l3.setBounds(2 * tempScoreBoardPanel.getWidth() / 3, 0, tempScoreBoardPanel.getWidth() / 3, tempScoreBoardPanel.getHeight());

                tempScoreBoardPanel.add(l1);
                tempScoreBoardPanel.add(l2);
                tempScoreBoardPanel.add(l3);
            } catch (IndexOutOfBoundsException e) {

            }


            scoreBoardPanels.add(tempScoreBoardPanel);
        }


        // Create scoreBoardPanel

    }

    public LinkedList<JPanel> getScoreBoardPanel() {
        return scoreBoardPanels;
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
            } catch (ArrayIndexOutOfBoundsException ignored) {

            }
        }
    }
}

