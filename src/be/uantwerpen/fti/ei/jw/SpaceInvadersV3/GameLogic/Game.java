package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.KeyboardInput1;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D.J2DFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {

    // Variables concerning the game logic
    Random random;
    AbsScoreBoard scoreBoard;
    boolean victory, gameOver, playing;
    int currentRound, totalRounds;

    // Game timings
    int FPS = 60;
    long frameInterval = 1000 / FPS;
    Timer gameTimer, powerupTimer;

    // Variables concerning the movement
    AbsInput input;
    MovementSystem movementUpdater = new MovementSystem();


    // Variables concerning the game environment
    AbsFactory factory;
    AbsVisualManager visualManager;
    int fieldWidth, fieldHeight;


    // Game elements
    LinkedList<AbsPlayer> players = new LinkedList<>();
    LinkedList<AbsEnemy> enemiesList = new LinkedList<>();
    LinkedList<AbsBullet> playerBullets = new LinkedList<>();
    LinkedList<AbsBullet> enemyBullets = new LinkedList<>();
    LinkedList<AbsPowerUp> powerups = new LinkedList<>();


    public Game() {
        // Read config files
        ArrayList<String> configSettings;
        try {
            configSettings = readConfigFiles();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        // Load variables
        loadVariables(configSettings);

        // Create the player(s)
        createPlayers(2);

        // Create game environment
        createGameEnvironment();

        // Load first level
        loadLevel(currentRound++);

        // Start Game
        startGame();
    }


    // Read config files
    public ArrayList<String> readConfigFiles() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File("src/Config.xml"));
        doc.getDocumentElement().normalize();
        NodeList frameSize = doc.getElementsByTagName("frameSize");
        Node first = frameSize.item(0);
        NodeList childs = first.getChildNodes();
        int n = childs.getLength();
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node child = childs.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                //System.out.println(child.getNodeName() + ": " + child.getTextContent());
                values.add(child.getTextContent());
            }
        }
        return values;
    }

    public void loadVariables(ArrayList<String> settings) {
        // Get the amount of cells in the gameField
        fieldWidth = Integer.parseInt(settings.get(0));
        fieldHeight = Integer.parseInt(settings.get(1));

        // Choose the type of visualisation
        factory = new J2DFactory(fieldWidth, fieldHeight);

        // Create the different Timers
        gameTimer = new Timer();
        powerupTimer = new Timer();

        // Create random generator
        random = new Random();


        // Setup gameLogic booleans
        victory = false;    // This will change if the players have defeated all the levels
        gameOver = false;   // This will change if 1 player reaches 0 lives
        playing = false;    // This will chane when the game is paused

        // Setup amount of rounds
        currentRound = 0;   // This is the current round that will be loaded
        totalRounds = 2;    // This is the total amount of rounds the player must survive to become victorious

    }

    private void createPlayers(int amountOfPlayers) {
        // Choose the type of input
        input = new KeyboardInput1(amountOfPlayers);
        if (amountOfPlayers > 0) {
            players.add(factory.createPlayer(100, fieldHeight - 15, "Player1", input));
        }
        if (amountOfPlayers > 1) {
            players.add(factory.createPlayer(50, fieldHeight - 15, "Player2", input));
        }

    }

    private void createGameEnvironment() {
        // Create scoreBoard
        scoreBoard = factory.createScoreBoard(players);

        visualManager = factory.getVisualManager();
        visualManager.setupGameEnv(input, scoreBoard);                   // This will set up the game environment

    }

    public void loadLevel(int round) {
        BufferedImage lvl;
        try {
            lvl = ImageIO.read(new File("D:\\_Opslag\\Programmeren\\Java\\SpaceInvadersV3\\src\\res\\levels\\Level" + round + ".png")); // Reads file and converts it to a BufferedImage
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Get the dimensions of the image
        int w = lvl.getWidth();
        int h = lvl.getHeight();

        // Determine for every pixel in the image the color
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = lvl.getRGB(x, y);
                // This will shift the value from every pixel to a range between 0 and 255 using the arithmetic shift and bit-and functions
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                // If the pixel is white, all the values will be 255
                if (red == 255 && green == 255 && blue == 255) {
                    enemiesList.add(factory.createEnemy(x * 15, y * 15)); // This will create an enemy on the location of the pixel but in the field
                }
            }
        }
    }

    public void nextRound() {
        if (currentRound == totalRounds) {
            playing = false;
            victory = true;
        } else {
            players.forEach(AbsPlayer::resetPowerup);
            playerBullets.clear();
            enemyBullets.clear();
            loadLevel(currentRound++);
        }
    }

    public void startGame() {
        // Start timer voor wanneer powerup mag komen
        powerupTimer = new Timer();
        playing = true;
        gameLoop();
        if (victory) {
            System.out.println("Victorious");
            while (true) {

            }
        }
        if (gameOver) {
            System.out.println("GameOver");
            while (true) {

            }
        }
    }


    private void updatePlayers() {
        /*
            Update all players in the list, this means:
            - Check if they have a powerup
            - Check if they want to shoot
            - Update their position
             */
        movementUpdater.updatePlayers(players, fieldWidth, input);
        for (AbsPlayer p : players) {
            // Give player powerUp
            p.checkPowerUp();

            // Create bullets if player shoots
            if (p.shoots() && p.getShootTimer().getTime() >= 500 / p.getShootingBonus()) {
                playerBullets.add(factory.createPlayerBullet(p));
                p.getShootTimer().reset();
            }
        }
    }

    private void updateEnemies() {
         /*
        Update all enemies in the list, this means:
        - Let them shoot
        - Update their position
         */
        if (AbsEnemy.getBulletTimer().getTime() > 1000) {
            for (AbsEnemy enemy : enemiesList) {
                int rand = random.nextInt(100); // gives a number between 0 and 99

                // 10% chance that an enemy shoots
                if (rand < 10) {
                    enemyBullets.add(factory.createEnemyBullet(enemy));
                }
            }
            AbsEnemy.getBulletTimer().reset();
        }

        // Make sure that the enemies move slower than the rest of the game
        if (AbsEnemy.getEnemyMoveTimer().getTime() >= 300) {
            List<MovementComponent> components = enemiesList.stream().map(AbsEnemy::getMovementComponent).collect(Collectors.toList());
            movementUpdater.updateEnemies(components, enemiesList.get(0), fieldWidth);
            AbsEnemy.getEnemyMoveTimer().reset();
        }
    }

    public void gameLoop() {
        // Start gameLoop
        System.out.println("Game loop starting");
        while (playing) {
            if (!input.pausePressed) {
                powerupTimer.start();
                for (AbsPlayer player : players){
                    player.startTimers();
                }


// ******************************************** CHECK FOR NEXT LEVEL ***************************************************
                // Check if there are no enemies left, if so the next round should be loaded
                if (enemiesList.size() == 0) {
                    System.out.println("No enemies");
                    nextRound();
                    continue;
                }

// ******************************************* CREATE POWERUP **********************************************************

                // Create a powerUp object every 20 seconds
                if (powerupTimer.getTime() > 20000) {
                    powerups.add(factory.createPowerUp(0, 0));
                    powerupTimer.reset();
                }

// ************************************** MOVE OBJECTS + CREATE BULLETS ************************************************

                updatePlayers();
                updateEnemies();


                // Move all the powerUps
                try {
                    List<MovementComponent> powerUpMovementComponents = powerups.stream().map(AbsEntity::getMovementComponent).collect(Collectors.toList());
                    movementUpdater.updatePowerup(powerUpMovementComponents, powerups.get(0), fieldWidth);
                } catch (IndexOutOfBoundsException ignored) {
                }
                // Move all the components
                List<MovementComponent> bulletMovementComponents = Stream.concat(playerBullets.stream().map(AbsEntity::getMovementComponent), enemyBullets.stream().map(AbsEntity::getMovementComponent)).collect(Collectors.toList());
                movementUpdater.updateBullets(bulletMovementComponents);

// ************************************************ COLLISION DETECTION ************************************************

                // Iterators for the all the lists containing gameElements
                Iterator<AbsBullet> it_playerBullets = playerBullets.iterator();
                Iterator<AbsBullet> it_enemyBullets = enemyBullets.iterator();
                Iterator<AbsEnemy> it_enemies = enemiesList.iterator();
                Iterator<AbsPowerUp> it_powerups = powerups.iterator();

                // Check for collisions between playerBullets and Enemies or Powerups
                while (it_playerBullets.hasNext()) {
                    // Get the playerBullet object
                    AbsPlayerBullet playerBullet = (AbsPlayerBullet) it_playerBullets.next();

                    // If playerBullet exits the field, let it disappear
                    if (playerBullet.getMovementComponent().getPosY() + playerBullet.getHeight() <= 0) {
                        it_playerBullets.remove();
                    }

                    // Look if playerBullet has hit the powerUp
                    while (it_powerups.hasNext()) {
                        AbsPowerUp powerUp = it_powerups.next();
                        if (playerBullet.hasHit(powerUp)) {
                            powerUp.damagedBy(playerBullet);
                            if (powerUp.isDead()) {
                                playerBullet.getShooter().getPowerUp(powerUp.getPowerUp()); // Give the power from the powerUp to the shooter of the playerBullet that hit it
                                it_powerups.remove();
                                powerupTimer.reset();
                            }
                            try {
                                it_playerBullets.remove();
                            } catch (IllegalStateException ignored) {

                            }
                        }
                    }

                    // Look if playerBullet has hit an enemy
                    while (it_enemies.hasNext()) {
                        AbsEnemy enemy = it_enemies.next();
                        // If enemy is hit, damage it
                        if (playerBullet.hasHit(enemy)) {
                            // Damage the enemy hit
                            enemy.damagedBy(playerBullet);

                            // If enemy has no health anymore, delete it
                            if (enemy.isDead()) {
                                it_enemies.remove();
                                playerBullet.getShooter().addPoints(1);
                            }

                            // Remove the bullet since it has hit something
                            try {
                                it_playerBullets.remove();
                            } catch (IllegalStateException ignored) {

                            }
                        }
                    }
                }

                // Look if enemyBullet has hit a player
                while (it_enemyBullets.hasNext()) {
                    // Get the enemybullet object
                    AbsEnemyBullet enemyBullet = (AbsEnemyBullet) it_enemyBullets.next();

                    // If bullet exits the field, let it disappear
                    if (enemyBullet.getMovementComponent().getPosY() > fieldHeight) {
                        it_enemyBullets.remove();
                    }

                    // Check if enemyBullet has hit a player
                    for (AbsPlayer player : players) {
                        if (player != null && enemyBullet.hasHit(player)) {
                            player.damagedBy(enemyBullet);
                            try {
                                it_enemyBullets.remove();
                            } catch (IllegalStateException ignored) {

                            }

                            // Check if player is dead or not
                            if (player.isDead()) {
                                gameOver = true;
                                playing = false;
                                break;
                            }
                        }
                    }
                }

                // Check if enemy has hit the ground level
                try {
                    int maxY = enemiesList.stream().map(AbsEntity::getMovementComponent).mapToInt(MovementComponent::getPosY).min().getAsInt() + enemiesList.get(0).getHeight();
                    if (maxY >= fieldHeight - enemiesList.get(0).getHeight()) {
                        gameOver = true;
                        playing = false;
                        break;
                    }
                } catch (IndexOutOfBoundsException | NoSuchElementException ignored) {
                }


// ****************************************** VISUALIZE GAME OBJECTS ***************************************************
                enemiesList.forEach(AbsEnemy::visualize);
                players.forEach(AbsPlayer::visualize);
                playerBullets.forEach(AbsBullet::visualize);
                enemyBullets.forEach(AbsBullet::visualize);
                powerups.forEach(AbsPowerUp::visualize);

                // Update the points and lives of the players
                scoreBoard.update();
                scoreBoard.visualize();

                visualManager.render();   // Show the bufferedImage on the gamePanel

                //SoundSystem.update(playerBullets.stream().map(AbsBullet::getSoundComponent).collect(Collectors.toList()));

// ***************************************** SLEEP UNTIL NEXT FRAME ****************************************************
            } else {
                powerupTimer.pause();
                for (AbsPlayer player : players) {
                    player.pauseTimers();
                }
                visualManager.clearGamePanel();
                visualManager.showPaused();
                visualManager.render();
            }
            // Sleep until next frame
            long sleepTime = frameInterval - gameTimer.getTime();
            try {
                if (sleepTime >= 0) {
                    Thread.sleep(sleepTime);
                }
                gameTimer.reset();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

//TODO: Add Sprites
