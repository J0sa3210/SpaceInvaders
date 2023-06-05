package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Visualisation.Sprite;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.*;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;

/**
 * The <code>SpriteFactory</code> class extends the AbsFactory class and represents a factory for creating sprite-based game objects in the Space Invaders game.
 * It provides methods for creating different types of sprites, such as enemies, bullets, power-ups, and the visual manager.
 */
public class SpriteFactory extends AbsFactory {
    private final SpriteVisualManager visualManager;

    /**
     * Creates a new SpriteFactory instance with the specified field dimensions and game.
     *
     * @param fieldWidth  The width of the game field.
     * @param fieldHeight The height of the game field.
     * @param game        The game instance.
     */
    public SpriteFactory(int fieldWidth, int fieldHeight, Game game) {
        visualManager = (SpriteVisualManager) createVisualManager(fieldWidth, fieldHeight, game);
    }

    /**
     * Creates an enemy sprite at the specified position.
     *
     * @param x The x-coordinate of the enemy.
     * @param y The y-coordinate of the enemy.
     * @return The created enemy sprite.
     */
    @Override
    public AbsEnemy createEnemy(int x, int y) {
        return new SpriteEnemy(x, y, this);
    }

    /**
     * Creates an enemy bullet sprite associated with the specified enemy.
     *
     * @param enemy The enemy that fired the bullet.
     * @return The created enemy bullet sprite.
     */
    @Override
    public AbsEnemyBullet createEnemyBullet(AbsEnemy enemy) {
        return new SpriteEnemyBullet(enemy, this);
    }

    /**
     * Creates a power-up sprite at the specified position.
     *
     * @param x The x-coordinate of the power-up.
     * @param y The y-coordinate of the power-up.
     * @return The created power-up sprite.
     */
    @Override
    public AbsPowerUp createPowerUp(int x, int y) {
        return new SpritePowerUp(x, y, this);
    }

    /**
     * Creates a score board sprite associated with the specified players.
     *
     * @param players The list of players.
     * @return The created score board sprite.
     */
    @Override
    public AbsScoreBoard createScoreBoard(LinkedList<AbsPlayer> players) {
        return new SpriteScoreBoard(players);
    }

    /**
     * Creates a player sprite at the specified position with the given name and input.
     *
     * @param x          The x-coordinate of the player.
     * @param y          The y-coordinate of the player.
     * @param playername The name of the player.
     * @param input      The input handler for the player.
     * @return The created player sprite.
     */
    @Override
    public AbsPlayer createPlayer(int x, int y, String playername, AbsInput input) {
        return new SpritePlayer(x, y, playername, this, input);
    }

    /**
     * Creates a player bullet sprite associated with the specified player.
     *
     * @param p The player that fired the bullet.
     * @return The created player bullet sprite.
     */
    @Override
    public AbsPlayerBullet createPlayerBullet(AbsPlayer p) {
        return new SpritePlayerBullet(p, this);
    }

    /**
     * Creates a visual manager for the game with the specified field dimensions and game instance.
     *
     * @param fieldWidth  The width of the game field.
     * @param fieldHeight The height of the game field.
     * @param game        The game instance.
     * @return The created visual manager.
     */
    @Override
    public AbsVisualManager createVisualManager(int fieldWidth, int fieldHeight, Game game) {
        return new SpriteVisualManager(fieldWidth, fieldHeight, game);
    }

    /**
     * Returns the visual manager associated with this factory.
     *
     * @return The visual manager.
     */
    public SpriteVisualManager getVisualManager() {
        return visualManager;
    }

    @Override
    public AbsEnemy createBoss(int x, int y) {
        return new SpriteBoss(x,y,this);
    }

    /**
     * Returns the scale used by the visual manager.
     *
     * @return The scale value.
     */
    public int getScale() {
        return visualManager.getCellSize();
    }

    /**
     * Returns the Graphics2D object from the visual manager.
     *
     * @return The Graphics2D object.
     */
    public Graphics2D getG2d() {
        return visualManager.getG2d();
    }
}
