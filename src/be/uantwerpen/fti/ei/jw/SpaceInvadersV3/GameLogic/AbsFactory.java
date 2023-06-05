package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.util.LinkedList;

/**
 * The <code>AbsFactory</code> class is an abstract class that serves as a factory for creating various game objects.
 *
 * <p>
 * This class provides abstract methods for creating different game objects such as enemies, players, bullets, power-ups, scoreboards, and visual managers. Each subclass of `AbsFactory` should implement these methods to create specific types of objects.
 * </p>
 */
public abstract class AbsFactory {

    /**
     * Creates an enemy object at the specified coordinates.
     *
     * @param x The x-coordinate of the enemy
     * @param y The y-coordinate of the enemy
     * @return The created enemy object
     */
    public abstract AbsEnemy createEnemy(int x, int y);

    /**
     * Creates a player object at the specified coordinates with the given player name and input.
     *
     * @param x          The x-coordinate of the player
     * @param y          The y-coordinate of the player
     * @param playername The name of the player
     * @param input      The input object for the player
     * @return The created player object
     */
    public abstract AbsPlayer createPlayer(int x, int y, String playername, AbsInput input);

    /**
     * Creates a player bullet object for the given player.
     *
     * @param p The player object for which to create the bullet
     * @return The created player bullet object
     */
    public abstract AbsPlayerBullet createPlayerBullet(AbsPlayer p);

    /**
     * Creates an enemy bullet object for the given enemy.
     *
     * @param e The enemy object for which to create the bullet
     * @return The created enemy bullet object
     */
    public abstract AbsEnemyBullet createEnemyBullet(AbsEnemy e);

    /**
     * Creates a power-up object at the specified coordinates.
     *
     * @param x The x-coordinate of the power-up
     * @param y The y-coordinate of the power-up
     * @return The created power-up object
     */
    public abstract AbsPowerUp createPowerUp(int x, int y);

    /**
     * Creates a score board object for the given list of players.
     *
     * @param players The list of players for the score board
     * @return The created score board object
     */
    public abstract AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players);

    /**
     * Creates a visual manager object with the specified field dimensions and game logic.
     *
     * @param fieldWidth  The width of the playing field
     * @param fieldHeight The height of the playing field
     * @param game        The game logic object
     * @return The created visual manager object
     */
    public abstract AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight, Game game);

    /**
     * Retrieves the visual manager object associated with this factory.
     *
     * @return The visual manager object
     */
    public abstract AbsVisualManager getVisualManager();

    /**
     * Creates a boss enemy object at the specified coordinates
     *
     * @param x The x-coordinate of the enemy
     * @param y The y-coordinate of the enemy
     * @return The created boss enemy object
     */
    public abstract AbsEnemy createBoss(int x, int y);
}
