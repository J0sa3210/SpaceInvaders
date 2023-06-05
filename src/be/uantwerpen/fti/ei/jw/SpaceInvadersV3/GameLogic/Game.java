package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.SoundComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Systems.MovementSystem;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Systems.SoundSystem;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.Keyboard1PlayerInput;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.Keyboard2PlayerInput;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D.J2DFactory;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite.SpriteFactory;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The main <code>game</code> class controls the game logic and elements.
 */
public class Game {

    // Variables concerning the game logic
    Random random;
    AbsScoreBoard scoreBoard;
    boolean victory;
    boolean gameOver;
    boolean playing;
    int currentLevel;
    int totalLevels;

    // Game timings
    int FPS = 40;
    long frameInterval = 1000 / FPS;
    Timer gameTimer;
    Timer powerupTimer;

    // Variables concerning the movement
    AbsInput input;
    MovementSystem movementUpdater = new MovementSystem();

    // Variables concerning the game environment
    String typeOfFactory;
    AbsFactory factory;
    AbsVisualManager visualManager;

    int fieldWidth;
    int fieldHeight;

    // Variables concerning the sound
    SoundSystem soundSystem = new SoundSystem();
    List<SoundComponent> soundComponents = new LinkedList<>();

    // Game elements
    int amountOfPlayers;
    String[] playerNames = new String[2];
    LinkedList<AbsPlayer> playersList = new LinkedList<>();
    LinkedList<AbsEnemy> enemiesList = new LinkedList<>();
    LinkedList<AbsBullet> playerBulletsList = new LinkedList<>();
    LinkedList<AbsBullet> enemyBulletsList = new LinkedList<>();
    LinkedList<AbsPowerUp> powerupsList = new LinkedList<>();


    public Game() {
        // Ask user input
        askUserInput();

        // Read config files
        readConfigFiles();

        // Load variables
        loadVariables();

        // Create the player(s)
        createPlayers(amountOfPlayers);

        // Wait for 2 seconds until further loading the game
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Create game environment
        createGameEnvironment();

        // Load first level
        loadLevel(currentLevel++);

        // Start Game
        startGameLoop();
    }

    /**
     * This method asks input about:
     * - How many players will play (max 2)
     * - What kind of visualization the player wants (Sprite or J2D)
     */
    private void askUserInput() {
        // Get scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Space Invaders!!");

        // Get the values from user
        getFactoryType(scanner);
        getAmountOfPlayers(scanner);
        getPlayerNames(scanner);

        // Start game
        System.out.println("Please minimize the IntelliJ IDE. The game will start in 2 seconds.");
        System.out.println("Thanks and enjoy the game!");
    }

    private void getPlayerNames(Scanner scanner) {
        for (int i = 0; i < amountOfPlayers; i++) {
            System.out.println("What is the name of player " + (i + 1) + "?");
            String playername = scanner.nextLine();
            playerNames[i] = playername;
        }
    }

    /**
     * This method will ask for the amount of players the user wants. This method will keep repeating until a valid answer (1 or 2) has been given
     *
     * @param scanner The scanner to read the users input
     * @see AbsPlayer
     */
    private void getAmountOfPlayers(Scanner scanner) {
        System.out.println("Please choose how many players you would like (1 or 2): ");
        amountOfPlayers = Integer.parseInt(scanner.nextLine());
        if (amountOfPlayers != 1 && amountOfPlayers != 2) {
            getAmountOfPlayers(scanner);
        }
    }

    /**
     * This method will ask for the type of visualisation the user wants.
     * <p>
     * Based on that type, an instance of an {@link AbsFactory} will be made.
     * This method will keep repeating until a valid answer (1 or 2) has been given
     * </p>
     *
     * @param scanner The scanner to read the users input
     * @see J2DFactory
     * @see SpriteFactory
     */
    private void getFactoryType(Scanner scanner) {
        System.out.println("Next, choose what type of visualisation you want Sprite (1) or J2D (2): ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            typeOfFactory = "Sprite";
        } else if (choice == 2) {
            typeOfFactory = "J2D";
        } else {
            getFactoryType(scanner);
        }
    }

    /**
     * Reads all values in the Config.properties file.
     */
    public void readConfigFiles() {
        try (InputStream input = new FileInputStream("src/Config.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            fieldWidth = Integer.parseInt(properties.getProperty("fieldWidth"));
            fieldHeight = Integer.parseInt(properties.getProperty("fieldHeight"));
            currentLevel = Integer.parseInt(properties.getProperty("startAtLevel"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the game variables based on the provided settings.
     */
    public void loadVariables() {
        // Choose the type of visualization
        if (typeOfFactory.equals("Sprite")) {
            factory = new SpriteFactory(fieldWidth, fieldHeight, this);
        } else if (typeOfFactory.equals("J2D")) {
            factory = new J2DFactory(fieldWidth, fieldHeight, this);
        }

        // Create the different Timers
        gameTimer = new Timer();
        powerupTimer = new Timer();

        // Create random generator
        random = new Random();

        // Setup gameLogic booleans
        victory = false;    // This will change if the players have defeated all the levels
        gameOver = false;   // This will change if 1 player reaches 0 lives
        playing = false;    // This will change when the game is paused

        // Setup amount of rounds
        totalLevels = 6;    // This is the total amount of rounds the player must survive to become victorious

        // Setup sounds
        soundSystem.startBackgroundMusic();
    }

    /**
     * Creates the players based on the specified amount.
     *
     * @param amountOfPlayers The number of players to create.
     */
    private void createPlayers(int amountOfPlayers) {
        // Choose the type of input
        if (amountOfPlayers == 1) {
            input = new Keyboard1PlayerInput();
            playersList.add(factory.createPlayer(100, fieldHeight - 15, playerNames[0], input));
        } else if (amountOfPlayers == 2) {
            input = new Keyboard2PlayerInput();
            playersList.add(factory.createPlayer(100, fieldHeight - 15, playerNames[0], input));
            playersList.add(factory.createPlayer(50, fieldHeight - 15, playerNames[1], input));
        }
    }

    /**
     * Creates the game environment and sets up the visual manager.
     */
    private void createGameEnvironment() {
        // Create scoreBoard
        scoreBoard = factory.createScoreBoard(playersList);

        visualManager = factory.getVisualManager();
        visualManager.setupGameEnv(input, scoreBoard);                   // This will set up the game environment
    }

    /**
     * Loads the specified level.
     *
     * @param round The level/round to load.
     */
    public void loadLevel(int round) {
        BufferedImage lvl;
        try {
            lvl = ImageIO.read(new File("src/res/levels/Level" + round + ".png")); // Reads file and converts it to a BufferedImage
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
                    enemiesList.add(factory.createEnemy(x * 16, y * 16)); // This will create an enemy on the location of the pixel but in the field
                } else if (red == 255 && green == 0 && blue == 0) {
                    enemiesList.add(factory.createBoss(x * 16, y * 16));
                }
            }
        }
    }

    /**
     * Proceeds to the next round of the game.
     */
    public void nextRound() {
        if (currentLevel == totalLevels) {
            playing = false;
            victory = true;
        } else {
            soundSystem.playLevelPassedSound();
            playersList.forEach(AbsPlayer::resetPowerup);
            playerBulletsList.clear();
            enemyBulletsList.clear();
            loadLevel(currentLevel++);
        }
    }


    /**
     * Starts the game loop, updating players {@link AbsPlayer}, enemies {@link AbsEnemy}, power ups {@link AbsPowerUp}, and checking for victory or game over.
     * If victorious, displays victory screen and plays victory sound.
     * If game over, displays game over screen and plays game over sound.
     *
     * @see AbsVisualManager
     * @see SoundSystem
     */
    public void startGameLoop() {
        powerupTimer = new Timer();
        playing = true;
        gameLoop();
        if (victory) {
            //System.out.println("Victorious");
            soundSystem.playVictorySound();
            visualManager.getVictoryScreen();
        }
        if (gameOver) {
            //System.out.println("GameOver");
            soundSystem.playGameOverSound();
            visualManager.getGameOverScreen();
        }
    }

    /**
     * Updates the movement of the players {@link AbsPlayer},
     * checks for power ups {@link AbsPowerUp},
     * and creates player bullets if the player shoots{@link AbsPlayerBullet}.
     */
    private void updatePlayers() {
        // Update position of players
        movementUpdater.updatePlayers(playersList, fieldWidth, input);

        for (AbsPlayer p : playersList) {
            // Give player powerUp
            p.checkPowerUp();

            // Create bullets if player shoots
            if (p.shoots() && p.getShootTimer().getTime() >= 500 / p.getShootingBonus()) {
                if (p.hasShotgun()) {
                    AbsPlayerBullet bullet1 = factory.createPlayerBullet(p);
                    soundComponents.add(bullet1.getSound());
                    playerBulletsList.add(bullet1);
                    AbsPlayerBullet bullet2 = factory.createPlayerBullet(p);
                    bullet2.getMovementComponent().setSpeedX(1);
                    soundComponents.add(bullet2.getSound());
                    playerBulletsList.add(bullet2);
                    AbsPlayerBullet bullet3 = factory.createPlayerBullet(p);
                    bullet3.getMovementComponent().setSpeedX(-1);
                    soundComponents.add(bullet3.getSound());
                    playerBulletsList.add(bullet3);
                } else {
                    AbsPlayerBullet bullet = factory.createPlayerBullet(p);
                    soundComponents.add(bullet.getSound());
                    playerBulletsList.add(bullet);
                }
                p.getShootTimer().reset();
            }
        }
    }

    /**
     * Updates the movement of the enemies every 0.3seconds {@link AbsEnemy},
     * checks for enemy bullet {@link AbsEnemyBullet} creation every 1 second,
     * and resets movement and shooting timers.
     */
    private void updateEnemies() {

        // Make sure that the enemies move slower than the rest of the game
        if (AbsEnemy.getEnemyMoveTimer().getTime() >= 300) {
            List<MovementComponent> components = enemiesList.stream().map(AbsEnemy::getMovementComponent).collect(Collectors.toList());
            movementUpdater.updateEnemies(components, enemiesList.get(0), fieldWidth);
            AbsEnemy.getEnemyMoveTimer().reset();
        }

        // Every second, randomly choose enemies to shoot (10% chance)
        if (AbsEnemy.getBulletTimer().getTime() > 1000) {

            for (AbsEnemy enemy : enemiesList) {
                int rand = random.nextInt(100); // gives a number between 0 and 99
                if (enemy.getClass().getSuperclass().getSimpleName().equals("AbsBoss")) {
                    if (rand < 50) {
                        AbsEnemyBullet enemyBullet0 = factory.createEnemyBullet(enemy);
                        soundComponents.add(enemyBullet0.getSound());
                        enemyBulletsList.add(enemyBullet0);
                        AbsEnemyBullet enemyBullet1 = factory.createEnemyBullet(enemy);
                        enemyBullet1.getMovementComponent().setSpeedX(1);
                        soundComponents.add(enemyBullet1.getSound());
                        enemyBulletsList.add(enemyBullet1);
                        AbsEnemyBullet enemyBullet2 = factory.createEnemyBullet(enemy);
                        enemyBullet2.getMovementComponent().setSpeedX(-1);
                        soundComponents.add(enemyBullet2.getSound());
                        enemyBulletsList.add(enemyBullet2);
                    }
                } else {
                    // 10% chance that an enemy shoots
                    if (rand < 10) {
                        AbsEnemyBullet enemyBullet = factory.createEnemyBullet(enemy);
                        soundComponents.add(enemyBullet.getSound());
                        enemyBulletsList.add(enemyBullet);

                    }
                }
            }
            AbsEnemy.getBulletTimer().reset();
        }
    }

    /**
     * Resumes the game by restarting power up timer and player timers.
     *
     * @see Timer
     */
    private void resumeGame() {
        powerupTimer.start();
        for (AbsPlayer player : playersList) {
            player.startTimers();
        }
    }

    /**
     * Checks if all enemies have been defeated, and if so, progresses to the next round.
     *
     * @return a boolean indicating if the player has progressed to the next level.
     */
    private boolean checkNextLevel() {
        if (enemiesList.size() == 0) {
            nextRound();
            return true;
        }
        return false;
    }

    /**
     * Creates a power up object {@link AbsPowerUp} every 20 seconds if there are no power ups present.
     */
    private void createPowerUp() {
        // Create a powerUp object every 20 seconds
        if (powerupTimer.getTime() > 10000 && powerupsList.isEmpty()) {
            powerupsList.add(factory.createPowerUp(0, 0));
            powerupTimer.reset();
        }

    }

    /**
     * Moves all powerups {@link AbsPowerUp} on the game board by calling the updatePowerup method from movementUpdater {@link MovementSystem}.
     */
    private void movePowerups() {
        if (!powerupsList.isEmpty()) {
            // Get all the MovementComponents
            List<MovementComponent> powerUpMovementComponents = powerupsList.stream().map(AbsEntity::getMovementComponent).collect(Collectors.toList());
            movementUpdater.updatePowerup(powerUpMovementComponents, powerupsList.get(0), fieldWidth);
        }
    }

    /**
     * Moves all bullets {@link AbsPlayer} on the game board by calling the updateBullets method from movementUpdater {@link MovementSystem}.
     * Bullets can belong to the player {@link AbsPlayerBullet} or the enemy {@link AbsEnemyBullet}.
     *
     * @see AbsPlayer
     * @see AbsEnemy
     */
    private void moveBullets() {
        List<MovementComponent> bulletMovementComponents = Stream.concat(playerBulletsList.stream().map(AbsEntity::getMovementComponent), enemyBulletsList.stream().map(AbsEntity::getMovementComponent)).collect(Collectors.toList());
        movementUpdater.updateBullets(bulletMovementComponents);
    }

    /**
     * Checks for collisions between playerBullets {@link AbsPlayerBullet} and enemies {@link AbsEnemy} or powerups {@link AbsPowerUp}.
     * Any detected collisions will damage the appropriate entity or remove it completely.
     * If a powerup {@link AbsPowerUp}is destroyed, the player who shot it gains the power from the powerup.
     */
    private void checkCollisionPlayerBullets() {
        // Check for collisions between playerBullets and Enemies or Powerups
        Iterator<AbsBullet> it_playerBullets = playerBulletsList.iterator();
        while (it_playerBullets.hasNext()) {
            // Get the playerBullet object
            AbsPlayerBullet playerBullet = (AbsPlayerBullet) it_playerBullets.next();

            // If playerBullet exits the field, let it disappear
            if (playerBullet.getMovementComponent().getPosY() + playerBullet.getHeight() <= 0) {
                it_playerBullets.remove();
            }

            // Look if playerBullet has hit the powerUp
            Iterator<AbsPowerUp> it_powerups = powerupsList.iterator();
            while (it_powerups.hasNext()) {
                AbsPowerUp powerUp = it_powerups.next();
                if (playerBullet.hasHit(powerUp)) {
                    powerUp.damagedBy(playerBullet);
                    if (powerUp.isDead()) {
                        playerBullet.getShooter().getPowerUp(powerUp.getPowerUp()); // Give the power from the powerUp to the shooter of the playerBullet that hit it
                        soundComponents.add(powerUp.getDeadSound());
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
            Iterator<AbsEnemy> it_enemies = enemiesList.iterator();
            while (it_enemies.hasNext()) {
                AbsEnemy enemy = it_enemies.next();
                // If enemy is hit, damage it
                if (playerBullet.hasHit(enemy)) {
                    // Damage the enemy hit
                    enemy.damagedBy(playerBullet);
                    // If enemy has no health anymore, delete it
                    if (enemy.isDead()) {
                        soundComponents.add(enemy.getDeadSound());
                        it_enemies.remove();
                        if (enemy.getClass().getSuperclass().getSimpleName().equals("AbsBoss")) {
                            playerBullet.getShooter().addPoints(10);
                        } else {
                            playerBullet.getShooter().addPoints(1);
                        }
                    }

                    // Remove the bullet since it has hit something
                    try {
                        it_playerBullets.remove();
                    } catch (IllegalStateException ignored) {
                    }
                }
            }
        }
    }

    /**
     * This method checks if any enemy bullets {@link AbsEnemyBullet} have hit a player {@link AbsPlayer} or the bottom of the field, and damages the player
     * accordingly. If a player is killed by a bullet, the game is over.
     */
    private void checkCollisionEnemyBullets() {
        // Look if enemyBullet has hit a player
        Iterator<AbsBullet> it_enemyBullets = enemyBulletsList.iterator();
        while (it_enemyBullets.hasNext()) {
            // Get the enemybullet object
            AbsEnemyBullet enemyBullet = (AbsEnemyBullet) it_enemyBullets.next();

            // If bullet exits the field, let it disappear
            if (enemyBullet.getMovementComponent().getPosY() > fieldHeight) {
                it_enemyBullets.remove();
            }

            // Check if enemyBullet has hit a player
            for (AbsPlayer player : playersList) {
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
            }
        } catch (IndexOutOfBoundsException | NoSuchElementException ignored) {
        }
    }

    /**
     * This method visualizes all objects on the field, including enemies, players, bullets, powerups, and thescoreboard.
     * It also renders game screen. How depends on the type of visualisation {@link AbsVisualManager}.
     *
     * @see AbsFactory
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D.J2DVisualManager
     * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite.SpriteVisualManager
     */
    private void visualizeObjects() {
        enemiesList.forEach(AbsEnemy::visualize);
        playersList.forEach(AbsPlayer::visualize);
        playerBulletsList.forEach(AbsBullet::visualize);
        enemyBulletsList.forEach(AbsBullet::visualize);
        powerupsList.forEach(AbsPowerUp::visualize);

        // Update the points and lives of the players
        scoreBoard.update();
        scoreBoard.visualize();

        visualManager.render();   // Show the bufferedImage on the gamePanel
    }

    /**
     * This method is the main game loop that runs while the game is being played.
     * It handles updating movement, checking for collisions, and rendering the game.
     */
    public void gameLoop() {

        // Start gameLoop
        while (playing) {

            // When not paused, we update the movement, check for collisions and draw it
            if (!input.isPausePressed()) {
                resumeGame();

                if (checkNextLevel()) {
                    continue;
                }

                createPowerUp();
                updatePlayers();
                updateEnemies();
                movePowerups();
                moveBullets();

                checkCollisionPlayerBullets();
                checkCollisionEnemyBullets();

                visualizeObjects();
                soundSystem.update(soundComponents);


            } else { // If the game is paused

                // Pause all timers in the game
                powerupTimer.pause();
                for (AbsPlayer player : playersList) {
                    player.pauseTimers();
                }

                // Show the paused screen
                visualManager.showPaused();
                visualManager.render();
            }

            // Calculate the time for each frame and sleep for the remaining time.
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

    /**
     * Resets the game by clearing all players and objects,
     * setting game logic booleans, and creating new players.
     * Also loads the first level and starts the game loop.
     */
    public void resetGame() {
        // clear players and all objects
        playersList.clear();
        AbsPlayer.setPlayerId(0);
        enemiesList.clear();
        enemyBulletsList.clear();
        playerBulletsList.clear();
        powerupsList.clear();

        // Setup gameLogic booleans
        victory = false;    // This will change if the players have defeated all the levels
        gameOver = false;   // This will change if 1 player reaches 0 lives
        playing = false;    // This will chane when the game is paused

        // Setup amount of rounds
        currentLevel = 0;   // This is the current round that will be loaded
        totalLevels = 1;    // This is the total amount of rounds the player must survive to become victorious


        // Create the player(s)
        createPlayers(2);

        visualManager.getGameScreen();

        loadLevel(currentLevel++);

        // Start Game
        startGameLoop();
    }
}
