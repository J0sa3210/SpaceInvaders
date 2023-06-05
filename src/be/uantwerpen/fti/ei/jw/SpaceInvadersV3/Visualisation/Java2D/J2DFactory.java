package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Java2D;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.*;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;

/**
 * The <code>J2DFactory</code> class represents a factory for creating Java 2D visual elements in the Space Invaders game.
 * It extends the {@link AbsFactory} class and provides concrete implementations for creating specific visual elements.
 */
public class J2DFactory extends AbsFactory {
    J2DVisualManager visualManager;
    Dimension fieldDimension;

    /**
     * Constructs a <code>J2DFactory</code> object with the specified field dimensions and <code>Game</code> instance.
     *
     * @param fieldWidth  The width of the game field.
     * @param fieldHeight The height of the game field.
     * @param game        The game instance.
     * @see Game
     */
    public J2DFactory(int fieldWidth, int fieldHeight, Game game) {
        fieldDimension = new Dimension(fieldWidth, fieldHeight);
        visualManager = (J2DVisualManager) createVisualManager(fieldWidth, fieldHeight, game);
    }

    /**
     * Creates an {@link J2DEnemyBullet} object associated with the specified enemy.
     *
     * @param enemy The enemy that fired the bullet.
     * @return The created enemy bullet.
     * @see J2DEnemy
     * @see AbsBullet
     */
    @Override
    public AbsEnemyBullet createEnemyBullet(AbsEnemy enemy) {
        return new J2DEnemyBullet(enemy, this);
    }

    /**
     * Creates a {@link J2DPowerUp} object with the specified coordinates.
     *
     * @param x The x-coordinate of the power-up.
     * @param y The y-coordinate of the power-up.
     * @return The created power-up.
     * @see AbsPowerUp
     */
    @Override
    public AbsPowerUp createPowerUp(int x, int y) {
        return new J2DPowerUp(x, y, this);
    }

    /**
     * Creates a {@link J2DScoreBoard} object with the specified player list.
     *
     * @param players The list of players.
     * @return The created score board.
     * @see AbsScoreBoard
     */
    @Override
    public AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players) {
        return new J2DScoreBoard(players);
    }

    /**
     * Creates an {@link J2DEnemy} object with the specified coordinates.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @return The created enemy.
     * @see AbsEnemy
     */
    @Override
    public AbsEnemy createEnemy(int x, int y) {
        return new J2DEnemy(x, y, this);
    }

    /**
     * Creates an {@link J2DBoss} object with the specified coordinates.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @return The created enemy.
     * @see AbsEnemy
     * @see AbsBoss
     */
    @Override
    public AbsEnemy createBoss(int x, int y) {
        return new J2DBoss(x, y, this);
    }

    /**
     * Creates a {@link J2DPlayer} object with the specified coordinates, player name, and input.
     *
     * @param x          The x-coordinate of the player.
     * @param y          The y-coordinate of the player.
     * @param playername The name of the player.
     * @param input      The input component for the player.
     * @return The created player.
     * @see AbsInput
     * @see AbsPlayer
     */
    @Override
    public AbsPlayer createPlayer(int x, int y, String playername, AbsInput input) {
        return new J2DPlayer(x, y, playername, this, input);
    }

    /**
     * Creates a {@link J2DPlayerBullet} object associated with the specified player.
     *
     * @param p The player that fired the bullet.
     * @return The created player bullet.
     * @see AbsPlayerBullet
     * @see AbsPlayer
     */
    @Override
    public AbsPlayerBullet createPlayerBullet(AbsPlayer p) {
        return new J2DPlayerBullet(p, this);
    }

    /**
     * Creates a {@link J2DVisualManager} object with the specified field dimensions and game instance.
     *
     * @param fieldWidth  The width of the game field.
     * @param fieldHeight The height of the game field.
     * @param game        The game instance.
     * @return The created Java 2D visual manager.
     * @see AbsVisualManager
     * @see Game
     */
    @Override
    public AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight, Game game) {
        return new J2DVisualManager(fieldWidth, fieldHeight, game);
    }

    /**
     * Retrieves the visual manager associated with the factory.
     *
     * @return The visual manager.
     */
    public J2DVisualManager getVisualManager() {
        return visualManager;
    }

    /**
     * Retrieves the scale factor used for visualization.
     *
     * @return The scale factor.
     */
    public int getScale() {
        return visualManager.getCellSize();
    }

    /**
     * Retrieves the graphics context from the visual manager.
     *
     * @return The graphics context.
     */
    public Graphics2D getG2d() {
        return visualManager.getG2d();
    }
}