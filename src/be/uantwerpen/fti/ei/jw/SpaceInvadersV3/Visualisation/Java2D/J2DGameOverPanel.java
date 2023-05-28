package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class J2DGameOverPanel extends JPanel implements ActionListener {
    JButton button;
    Game game;
    Dimension frameDimension;
    LinkedList<AbsPlayer> players;
    public J2DGameOverPanel(Dimension frameDimension, LinkedList<AbsPlayer> players, Game game) {
        this.game = game;
        this.frameDimension = frameDimension;
        this.players = players;
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        JLabel gameOverLabel = new J2DGameLabel("Game Over",100);
        gameOverLabel.setBounds(0,300, frameDimension.width, 150);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);

        //addScoreBoard();

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
        button.setBounds(frameDimension.width/2-200,500,400,75);


        this.add(gameOverLabel);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button){
            button.setEnabled(false);
        }
    }

    /*private void addScoreBoard() {
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
    }*/
}
