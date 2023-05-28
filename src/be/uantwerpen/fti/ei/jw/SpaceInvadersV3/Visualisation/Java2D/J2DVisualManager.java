package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsVisualManager;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import static java.lang.Math.min;

public class J2DVisualManager extends AbsVisualManager {
    J2DGameFrame gameFrame;
    J2DGamePanel gamePanel;
    int cellSize;
    Dimension fieldDimension;

    public J2DVisualManager(int fieldWidth, int fieldHeight) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
    }

    // Setup een gameframe dat een scoreboard heeft
    @Override
    public void setupGameEnv(AbsInput input, AbsScoreBoard scoreBoard) {
        // Bereken cellSize
        cellSize = calculateCellSize(fieldDimension);

        /*
        Bereken grootte  van JFrame "gameFrame"
        Zorg voor een beetje ruimte links en rechts (offset van 10%)
        Zorg voor plaats voor de heading (ongeveer 30 cellen)
         */
        Dimension gameFrameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension gamePanelDimension = new Dimension(fieldDimension.width * cellSize, fieldDimension.height * cellSize);

        // Maak een JFrame "gameFrame"
        gameFrame = new J2DGameFrame(gameFrameDimension, cellSize);
        gameFrame.setUndecorated(true);

        // Voeg scoreBoard toe
        scoreBoard.createScoreBoard(cellSize, gameFrameDimension.width);
        gameFrame.add((J2DScoreBoard) scoreBoard);

        // voeg Input toe
        gameFrame.addKeyListener((KeyListener) input);

        // Voeg GamePanel toe
        gamePanel = new J2DGamePanel(gamePanelDimension);
        gameFrame.add(gamePanel);


        // Maak G2d
        gamePanel.createG2D();

        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    @Override
    public void render() {
        gamePanel.getGamePanel().repaint();
    }

    public Graphics2D getG2d() {
        return gamePanel.getG2d();
    }

    public J2DGameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(J2DGameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public J2DGamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(J2DGamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    private int calculateCellSize(Dimension fieldDimension) {
        // System.out.println("GamePanel dimensions: " + gamePanel.getGamePanel().getSize());
        // System.out.println("fielddimensions: " + gamePanel.getGamePanel().getSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) min(screenSize.width / fieldDimension.getWidth(), (screenSize.height) / (fieldDimension.getHeight() + 30));
    }
}