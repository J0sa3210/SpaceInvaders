package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class J2DVictoryPanel extends JPanel implements ActionListener {
    JButton button;
    Game game;
    Dimension frameDimension;
    LinkedList<AbsPlayer> players;

    J2DVictoryPanel(Dimension frameDimension, Game game, LinkedList<AbsPlayer> players) {
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.game = game;
        this.players = players;
        this.frameDimension = frameDimension;
        Border testBorder = BorderFactory.createLineBorder(Color.RED, 3);

        //addVictoryLabel();
        //addScoreBoard();
        //addPlayAgainButton();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setEnabled(false);
        }
    }

    /*private void addVictoryLabel() {
        JLabel victoryLabel = new SpriteVisualManager.SpriteGameLabel("Victory",120);
        victoryLabel.setBounds(0, 0, frameDimension.width, 150);
        victoryLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(victoryLabel);
    }

    private void addScoreBoard() {
        JLabel scoreBoardLabel = new SpriteVisualManager.SpriteGameLabel("Scoreboard:", 50);
        scoreBoardLabel.setBounds(frameDimension.width / 3, 150, 500, 100);
        this.add(scoreBoardLabel);

        int j = 0;
        for (AbsPlayer player : players) {
            JPanel tempPanel = new JPanel(new GridLayout(2, 1));
            tempPanel.setBackground(Color.BLACK);

            for (int i = 0; i < 2; i++) {
                JLabel temp;
                if (i == 0) {
                    temp = new SpriteVisualManager.SpriteGameLabel("Player: " + player.getPlayerName(),30);

                } else {
                    temp = new SpriteVisualManager.SpriteGameLabel("Points: " + player.getPoints(),30);
                }
                temp.setHorizontalAlignment(JLabel.LEFT);
                tempPanel.add(temp);
            }
            tempPanel.setBounds(frameDimension.width / 3, 250 + (130 * (j++)), frameDimension.width / 3, 120);
            this.add(tempPanel);
        }
    }

    private void addPlayAgainButton() {
        button = new JButton();
        button.setText("Play Again");
        button.setEnabled(true);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setBorder(null);
        button.addActionListener(this);
        button.setFont(new Font("MV Boli", Font.BOLD, 40));
        button.setVerticalAlignment(SwingConstants.NORTH);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBounds(frameDimension.width / 2 - 200, 800, 400, 75);
        this.add(button);
    }*/
}
