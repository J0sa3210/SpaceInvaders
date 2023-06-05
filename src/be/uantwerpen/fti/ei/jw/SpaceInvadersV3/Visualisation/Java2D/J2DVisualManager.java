package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsScoreBoard;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsVisualManager;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Game;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static java.lang.Math.min;

/**
 * The <code>J2DVisualManager</code> class is responsible for managing the Java 2D visualization of the Space Invaders game.
 * It extends {@link AbsVisualManager} and implements the necessary methods.
 */
public class J2DVisualManager extends AbsVisualManager{
    JFrame gameFrame;
    Game game;
    JPanel container, gamePanel, gameOverPanel, victoryPanel;
    J2DLabel paused;
    CardLayout containerLayout;
    J2DButton playAgainButton1, playAgainButton2, quitButton1, quitButton2;
    Dimension fieldDimension, panelDimension, frameDimension;
    private int cellSize;
    private Graphics2D g2d;
    private BufferedImage g2dimage;


    /**
     * Creates a new <code>J2DVisualManager</code> instance with the specified field dimensions and game instance.
     *
     * @param fieldWidth  The width of the game field.
     * @param fieldHeight The height of the game field.
     * @param game        The Game instance.
     *
     * @see Game
     */
    public J2DVisualManager(int fieldWidth, int fieldHeight, Game game) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        paused = new J2DLabel("Paused", 50);
        this.game = game;
    }

    /**
     * Sets up the game environment by creating the necessary components and adding them to the game frame.
     *
     * @param input      The input handler for the game.
     * @param scoreBoard The score board for the game.
     */
    @Override
    public void setupGameEnv(AbsInput input, AbsScoreBoard scoreBoard) {
        // Calculate cell size
        cellSize = calculateCellSize(fieldDimension);
        int headingHeight = 30 * cellSize;

        // Calculate dimensions
        frameDimension = Toolkit.getDefaultToolkit().getScreenSize();
        panelDimension = new Dimension(fieldDimension.width * cellSize, fieldDimension.height * cellSize);

        // Create buttons
        playAgainButton1 = createPlayAgainButton();
        playAgainButton2 = createPlayAgainButton();
        quitButton1 = createQuitButton();
        quitButton2 = createQuitButton();

        // Create game frame
        gameFrame = createGameFrame(frameDimension, (KeyListener) input);

        // Create container to switch between panels
        containerLayout = new CardLayout();
        container = createContainer(panelDimension, containerLayout);

        // Add scoreBoard
        scoreBoard.createScoreBoard(cellSize, frameDimension.width);
        addScoreBoard(gameFrame, (J2DScoreBoard) scoreBoard);

        // Add GamePanel
        gamePanel = createGamePanel(panelDimension);
        container.add(gamePanel, "gamePanel");

        // Add victoryPanel
        victoryPanel = createVictoryPanel(panelDimension, playAgainButton1, quitButton1);
        container.add(victoryPanel, "victoryPanel");

        // Add gameOverPanel
        gameOverPanel = createGameOverPanel(panelDimension, playAgainButton2, quitButton2);
        container.add(gameOverPanel, "gameOverPanel");

        // Show gamePanel
        containerLayout.show(container, "gamePanel");

        // Add container to the gameFrame
        addContainer(gameFrame, container, headingHeight);

        // Make frame visible
        gameFrame.pack();
        gameFrame.setUndecorated(true);
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
     * Renders the game by repainting the game panel.
     */
    @Override
    public void render() {
        gamePanel.repaint();
    }

    /**
     * Displays the "Paused" message on the game panel.
     */
    @Override
    public void showPaused() {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("MV Boli", Font.BOLD, 200));
        g2d.drawString("PAUSED", gameFrame.getWidth() / 6, gameFrame.getHeight() / 2);
    }

    /**
     * Returns the Graphics2D object for drawing on the game panel.
     *
     * @return The Graphics2D object.
     */
    public Graphics2D getG2d() {
        return g2d;
    }

    /**
     * Returns the size of a cell in the game grid.
     *
     * @return The cell size.
     */
    public int getCellSize() {
        return cellSize;
    }

    private int calculateCellSize(Dimension fieldDimension) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return (int) min(screenSize.width / fieldDimension.getWidth(), (screenSize.height) / (fieldDimension.getHeight() + 30));
    }

    /**
     * Switches to the game over screen by showing the game over panel.
     */
    @Override
    public void getGameOverScreen() {
        containerLayout.show(container, "gameOverPanel");
    }

    /**
     * Switches to the victory screen by showing the victory panel.
     */
    @Override
    public void getVictoryScreen() {
        containerLayout.show(container, "victoryPanel");
    }

    /**
     * Switches to the game screen by showing the game panel.
     */
    @Override
    public void getGameScreen() {
        containerLayout.show(container,"gamePanel");
    }

    private JPanel createGameOverPanel(Dimension gameOverPanelDimension, J2DButton playAgainButton, J2DButton quitButton) {
        JPanel gameOverPanel = new JPanel(null);
        gameOverPanel.setBackground(new Color(255, 0, 0));
        gameOverPanel.setBounds(0, 0, gameOverPanelDimension.width, gameOverPanelDimension.height);
        gameOverPanel.setOpaque(false);

        J2DLabel gameOverLabel = new J2DLabel("Game over!", 80);
        gameOverLabel.setSize(gameOverPanelDimension.width / 3, 150);
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverLabel.setLocation((gameOverPanelDimension.width / 2) - (gameOverLabel.getWidth() / 2), (gameOverPanelDimension.height / 4) - gameOverLabel.getHeight());
        gameOverPanel.add(gameOverLabel);

        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(quitButton);

        gameOverPanel.setVisible(true);
        return gameOverPanel;
    }

    private JPanel createVictoryPanel(Dimension victoryPanelDimension, J2DButton playAgainButton, J2DButton quitButton) {
        JPanel victoryPanel = new JPanel(null);
        victoryPanel.setBackground(new Color(255, 0, 0));
        victoryPanel.setBounds(0, 0, victoryPanelDimension.width, victoryPanelDimension.height);
        victoryPanel.setOpaque(false);

        J2DLabel victoryLabel = new J2DLabel("Victory!", 80);
        victoryLabel.setSize(victoryPanelDimension.width / 3, 150);
        victoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        victoryLabel.setLocation((victoryPanelDimension.width / 2) - (victoryLabel.getWidth() / 2), (victoryPanelDimension.height / 4) - victoryLabel.getHeight());
        victoryPanel.add(victoryLabel);

        victoryPanel.add(playAgainButton);
        victoryPanel.add(quitButton);

        victoryPanel.setVisible(true);
        return victoryPanel;
    }

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

    private JFrame createGameFrame(Dimension frameDimension, KeyListener input) {
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
        frame.getContentPane().setBackground(Color.BLACK);

        // voeg Input toe
        frame.addKeyListener(input);

        // Heading
        int headingHeight = (30 * cellSize);

        JPanel heading = new JPanel();
        heading.setBackground(new Color(0, 0, 0, 0));
        heading.add(new J2DLabel("Space Invaders", 15 * cellSize));
        heading.setBounds(frameDimension.width / 3, 0, frameDimension.width / 3, headingHeight);
        frame.add(heading);
        return frame;
    }

    private J2DButton createPlayAgainButton(){
        J2DButton playAgainButton = new J2DButton("Play again", 50);
        playAgainButton.setSize(300, 80);
        playAgainButton.setLocation((panelDimension.width / 2) - (playAgainButton.getWidth() / 2), (panelDimension.height / 2) - playAgainButton.getHeight());
        playAgainButton.addActionListener(new J2DActionListener(playAgainButton, game, this));
        playAgainButton.setVisible(true);
        return playAgainButton;
    }

    private J2DButton createQuitButton(){
        J2DButton quitButton = new J2DButton("Quit", 50);
        quitButton.setSize(300, 80);
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.setLocation((panelDimension.width / 2) - (quitButton.getWidth() / 2), (panelDimension.height / 2 + 120) - quitButton.getHeight());
        quitButton.addActionListener(new J2DActionListener(quitButton, game, this) );
        quitButton.setVisible(true);
        return quitButton;
    }

    void addScoreBoard(JFrame gameFrame, J2DScoreBoard scoreBoard) {
        int i = 0;
        for (JPanel scoreBoardPanel : scoreBoard.getScoreBoardPanels()) {
            scoreBoardPanel.setLocation(i++ * gameFrame.getWidth() * 2 / 3, 0);
            scoreBoardPanel.setVisible(true);
            gameFrame.add(scoreBoardPanel);
        }
    }

    private void addContainer(JFrame gameFrame, JPanel container, int headingHeight) {
        Dimension containerDim = container.getSize();
        Dimension frameDim = gameFrame.getSize();
        container.setLocation((frameDim.width - containerDim.width) / 2, headingHeight + ((frameDim.height - headingHeight) - containerDim.height) / 2);
        container.setVisible(true);
        gameFrame.add(container);
    }
}