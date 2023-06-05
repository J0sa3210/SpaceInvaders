package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsVisualManager;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.min;

/**
 * The <code>SpriteVisualManager</code> class is responsible for managing the visual aspects of the Space Invaders game using sprites.
 * It extends the {@link AbsVisualManager} class.
 */
public class SpriteVisualManager extends AbsVisualManager {
    JFrame gameFrame;
    Game game;
    JPanel container, gamePanel, gameOverPanel, victoryPanel;
    SpriteLabel paused;
    CardLayout containerLayout;
    SpriteButton playAgainButton1, playAgainButton2, quitButton1, quitButton2;
    Dimension fieldDimension, panelDimension, frameDimension;
    private int cellSize;
    private Graphics2D g2d;
    private BufferedImage g2dimage;

    /**
     * Constructor for the SpriteVisualManager class.
     *
     * @param fieldWidth  the width of the game field
     * @param fieldHeight the height of the game field
     * @param game        the Game object
     */
    public SpriteVisualManager(int fieldWidth, int fieldHeight, Game game) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        paused = new SpriteLabel("Paused", 50);
        this.game = game;
    }

    /**
     * Resizes the given image to the specified width and height.
     *
     * @param image     the image to resize
     * @param newWidth  the new width of the image
     * @param newHeight the new height of the image
     * @return the resized image
     */
    public BufferedImage resize(BufferedImage image, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return resizedImage;
    }

    /**
     * This method sets up the game environment with a game frame and a scoreboard.
     *
     * @param input      The input object for handling user input.
     * @param scoreBoard The scoreboard object for displaying the game score.
     */
    @Override
    public void setupGameEnv(AbsInput input, AbsScoreBoard scoreBoard) {
        // Bereken cellSize
        cellSize = calculateCellSize(fieldDimension);
        int headingHeight = 30 * cellSize;

        //Bereken dimensies
        frameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        panelDimension = new Dimension(fieldDimension.width * cellSize, fieldDimension.height * cellSize);

        // SpriteButtonFActory
        playAgainButton1 = new SpriteButton("Play again", 50);
        playAgainButton1.setSize(300, 80);
        playAgainButton1.setLocation((panelDimension.width / 2) - (playAgainButton1.getWidth() / 2), (panelDimension.height / 2) - playAgainButton1.getHeight());
        playAgainButton1.addActionListener(new SpriteActionListener(playAgainButton1, game, this));
        playAgainButton1.setVisible(true);

        quitButton1 = new SpriteButton("Quit", 50);
        quitButton1.setSize(300, 80);
        quitButton1.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton1.setLocation((panelDimension.width / 2) - (quitButton1.getWidth() / 2), (panelDimension.height / 2 + 120) - quitButton1.getHeight());
        quitButton1.addActionListener(new SpriteActionListener(quitButton1, game, this));
        quitButton1.setVisible(true);

        playAgainButton2 = new SpriteButton("Play again", 50);
        playAgainButton2.setSize(300, 80);
        playAgainButton2.setLocation((panelDimension.width / 2) - (playAgainButton2.getWidth() / 2), (panelDimension.height / 2) - playAgainButton2.getHeight());
        playAgainButton2.addActionListener(new SpriteActionListener(playAgainButton2, game, this));
        playAgainButton2.setVisible(true);

        quitButton2 = new SpriteButton("Quit", 50);
        quitButton2.setSize(300, 80);
        quitButton2.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton2.setLocation((panelDimension.width / 2) - (quitButton2.getWidth() / 2), (panelDimension.height / 2 + 120) - quitButton2.getHeight());
        quitButton2.addActionListener(new SpriteActionListener(quitButton2, game, this));
        quitButton2.setVisible(true);

        // Maak een JFrame "gameFrame"
        BufferedImage frameBgImage = null;
        try {
            frameBgImage = ImageIO.read(new File("src/res/sprites/background.png")); // replace with your image file name
            frameBgImage = resize(frameBgImage, frameDimension.width, frameDimension.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameFrame = createGameFrame(frameDimension, frameBgImage, (KeyListener) input);

        // Maak container om tussen verschillende JPanels te wisselen
        containerLayout = new CardLayout();
        container = createContainer(panelDimension, containerLayout);

        // Voeg scoreBoard toe
        scoreBoard.createScoreBoard(cellSize, frameDimension.width);
        addScoreBoard(gameFrame, (SpriteScoreBoard) scoreBoard);

        // Voeg GamePanel toe
        gamePanel = createGamePanel(panelDimension);
        container.add(gamePanel, "gamePanel");

        victoryPanel = createVictoryPanel(panelDimension, playAgainButton1, quitButton1);
        container.add(victoryPanel, "victoryPanel");

        gameOverPanel = createGameOverPanel(panelDimension, playAgainButton2, quitButton2);
        container.add(gameOverPanel, "gameOverPanel");

        containerLayout.show(container, "gamePanel");

        addContainer(gameFrame, container, headingHeight);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    /**
     * This method creates a {@link JPanel} container with the specified dimension and {@link CardLayout}.
     *
     * @param panelDimension The dimension of the container panel.
     * @param layout         The CardLayout for the container.
     * @return The created container panel.
     */
    private JPanel createContainer(Dimension panelDimension, CardLayout layout) {
        JPanel container = new JPanel();
        container.setLayout(layout);
        container.setBounds(0, 0, panelDimension.width, panelDimension.height);
        container.setBackground(new Color(0, 0, 0, 0));
        container.setOpaque(false);

        return container;
    }

    /**
     * This method renders the game by repainting the game panel.
     */
    @Override
    public void render() {
        gamePanel.repaint();
    }

    /**
     * This method displays a "PAUSED" message on the game panel.
     */
    @Override
    public void showPaused() {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("MV Boli", Font.BOLD, 200));
        g2d.drawString("PAUSED", gameFrame.getWidth() / 6, gameFrame.getHeight() / 2);
    }

    /**
     * This method returns the {@link Graphics2D} object used for drawing on the game panel.
     *
     * @return The Graphics2D object.
     */
    public Graphics2D getG2d() {
        return g2d;
    }

    /**
     * This method returns the cell size used in the game.
     *
     * @return The cell size.
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * This method calculates the cell size based on the field dimensions and the screen size.
     *
     * @param fieldDimension The dimension of the game field.
     * @return The calculated cell size.
     */
    private int calculateCellSize(Dimension fieldDimension) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) min(screenSize.width / fieldDimension.getWidth(), (screenSize.height) / (fieldDimension.getHeight() + 30));
    }

    /**
     * This method switches the current panel to the game over panel.
     */
    @Override
    public void getGameOverScreen() {
        containerLayout.show(container, "gameOverPanel");
    }

    /**
     * This method switches the current panel to the victory panel.
     */
    @Override
    public void getVictoryScreen() {
        containerLayout.show(container, "victoryPanel");
    }

    /**
     * This method switches the current panel to the game panel.
     */
    @Override
    public void getGameScreen() {
        containerLayout.show(container, "gamePanel");
    }

    /**
     * This method creates the game over panel with the specified dimensions and buttons.
     *
     * @param gameOverPanelDimension The dimension of the game over panel.
     * @param playAgainButton        The play again button.
     * @param quitButton             The quit button.
     * @return The created game over panel.
     */
    private JPanel createGameOverPanel(Dimension gameOverPanelDimension, SpriteButton playAgainButton, SpriteButton quitButton) {
        JPanel gameOverPanel = new JPanel(null);
        gameOverPanel.setBackground(new Color(255, 0, 0));
        gameOverPanel.setBounds(0, 0, gameOverPanelDimension.width, gameOverPanelDimension.height);
        gameOverPanel.setOpaque(false);

        SpriteLabel gameOverLabel = new SpriteLabel("Game over!", 80);
        gameOverLabel.setSize(gameOverPanelDimension.width / 3, 150);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setLocation((gameOverPanelDimension.width / 2) - (gameOverLabel.getWidth() / 2), (gameOverPanelDimension.height / 4) - gameOverLabel.getHeight());
        gameOverPanel.add(gameOverLabel);

        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(quitButton);

        gameOverPanel.setVisible(true);
        return gameOverPanel;
    }

    /**
     * This method creates the victory panel with the specified dimensions and buttons.
     *
     * @param victoryPanelDimension The dimension of the victory panel.
     * @param playAgainButton       The play again button.
     * @param quitButton            The quit button.
     * @return The created victory panel.
     */
    private JPanel createVictoryPanel(Dimension victoryPanelDimension, SpriteButton playAgainButton, SpriteButton quitButton) {
        JPanel victoryPanel = new JPanel(null);
        victoryPanel.setBackground(new Color(255, 0, 0));
        victoryPanel.setBounds(0, 0, victoryPanelDimension.width, victoryPanelDimension.height);
        victoryPanel.setOpaque(false);

        SpriteLabel victoryLabel = new SpriteLabel("Victory!", 80);
        victoryLabel.setSize(victoryPanelDimension.width / 3, 150);
        victoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        victoryLabel.setLocation((victoryPanelDimension.width / 2) - (victoryLabel.getWidth() / 2), (victoryPanelDimension.height / 4) - victoryLabel.getHeight());
        victoryPanel.add(victoryLabel);

        victoryPanel.add(playAgainButton);
        victoryPanel.add(quitButton);

        victoryPanel.setVisible(true);
        return victoryPanel;
    }

    /**
     * This method creates the game panel with the specified dimension.
     *
     * @param gamePanelDimension The dimension of the game panel.
     * @return The created game panel.
     */
    private JPanel createGamePanel(Dimension gamePanelDimension) {
        // Create Image and Graphics
        g2dimage = new BufferedImage(gamePanelDimension.width, gamePanelDimension.height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        g2d = g2dimage.createGraphics();

        // Create JPanel
        JPanel gamePanel = new JPanel() {
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
                } catch (NullPointerException ignored) {
                }
            }
        };

        // Customize JPanel
        gamePanel.setBackground(new Color(0, 0, 0, 0));
        gamePanel.setBounds(0, 0, gamePanelDimension.width, gamePanelDimension.height);
        gamePanel.setPreferredSize(gamePanelDimension);
        gamePanel.setDoubleBuffered(true);
        gamePanel.setOpaque(false);
        gamePanel.setVisible(true);

        return gamePanel;
    }

    /**
     * This method creates the game frame with the specified dimension, background image, and input listener.
     *
     * @param frameDimension The dimension of the game frame.
     * @param bgImage        The background image of the game frame.
     * @param input          The input listener for handling user input.
     * @return The created game frame.
     */
    private JFrame createGameFrame(Dimension frameDimension, BufferedImage bgImage, KeyListener input) {
        JFrame frame = new JFrame();
        frame.setFocusable(true);

        // Lay-out //
        // Visualization
        frame.setPreferredSize(frameDimension);
        frame.setMinimumSize(frameDimension);
        frame.setMaximumSize(frameDimension);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setUndecorated(true);

        // voeg Input toe
        frame.addKeyListener(input);

        // Background image
        frame.setContentPane(new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, frame);
            }
        });

        // Heading
        int headingHeight = (30 * cellSize);

        JPanel heading = new JPanel();
        heading.setBackground(new Color(0, 0, 0, 0));
        heading.add(new SpriteLabel("Space Invaders", 15 * cellSize));
        heading.setBounds(frameDimension.width / 3, 0, frameDimension.width / 3, headingHeight);
        frame.add(heading);
        return frame;
    }

    /**
     * This method adds the scoreboard panels to the game frame.
     *
     * @param gameFrame  The game frame to which the scoreboard panels will be added.
     * @param scoreBoard The scoreboard containing the panels.
     */
    void addScoreBoard(JFrame gameFrame, SpriteScoreBoard scoreBoard) {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoard.getScoreBoardPanels()) {
            scoreBoardPanel.setLocation(i++ * gameFrame.getWidth() * 2 / 3, 0);
            scoreBoardPanel.setVisible(true);
            gameFrame.add(scoreBoardPanel);
        }
    }

    /**
     * This method adds the container to the game frame.
     *
     * @param gameFrame      The game frame to which the container will be added.
     * @param container      The container panel.
     * @param headingHeight  The height of the heading panel.
     */
    private void addContainer(JFrame gameFrame, JPanel container, int headingHeight) {
        Dimension containerDim = container.getSize();
        Dimension frameDim = gameFrame.getSize();
        container.setLocation((frameDim.width - containerDim.width) / 2, headingHeight + ((frameDim.height - headingHeight) - containerDim.height) / 2);
        container.setVisible(true);
        gameFrame.add(container);
    }
}