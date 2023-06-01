package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsVisualManager;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.min;

public class SpriteVisualManager extends AbsVisualManager {
    SpriteGameFrame gameFrame;
    SpriteGamePanel gamePanel;
    SpriteGameLabel paused;
    Dimension fieldDimension;
    private int cellSize;

    public SpriteVisualManager(int fieldWidth, int fieldHeight) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        paused = new SpriteGameLabel("Paused", 50);
    }

    public BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return resizedImage;
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
        BufferedImage frameBgImage = null;
        try {
            frameBgImage = ImageIO.read(new File("src/res/sprites/background.png")); // replace with your image file name
            frameBgImage = resize(frameBgImage, gameFrameDimension.width, gameFrameDimension.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameFrame = new SpriteGameFrame(gameFrameDimension, cellSize, frameBgImage);
        gameFrame.setUndecorated(true);

        // Voeg scoreBoard toe
        scoreBoard.createScoreBoard(cellSize, gameFrameDimension.width);
        gameFrame.add((SpriteScoreBoard) scoreBoard);

        // voeg Input toe
        gameFrame.addKeyListener((KeyListener) input);

        // Voeg GamePanel toe
        gamePanel = new SpriteGamePanel(gamePanelDimension);
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

    @Override
    public void showPaused() {
        gamePanel.getG2d().setColor(Color.WHITE);
        gamePanel.getG2d().setFont(new Font("MV Boli", Font.BOLD, 200));
        gamePanel.getG2d().drawString("PAUSED", gameFrame.getWidth() / 6, gameFrame.getHeight() / 2);
    }

    @Override
    public void clearGamePanel() {

    }

    public Graphics2D getG2d() {
        return gamePanel.getG2d();
    }

    public int getCellSize() {
        cellSize = calculateCellSize(fieldDimension);
        return cellSize;
    }

    private int calculateCellSize(Dimension fieldDimension) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) min(screenSize.width / fieldDimension.getWidth(), (screenSize.height) / (fieldDimension.getHeight() + 30));
    }
}