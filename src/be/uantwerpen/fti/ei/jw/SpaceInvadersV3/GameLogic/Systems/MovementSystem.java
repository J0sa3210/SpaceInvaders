package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Systems;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsEnemy;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPlayer;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsPowerUp;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.Components.MovementComponent;
import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


/**
 * The <code>MovementSystem</code> class handles the updating of {@link MovementComponent}s for various entities in the game.
 * It provides methods to update enemies, bullets, power-ups, and players' movement.
 *
 * @see AbsEnemy
 * @see be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic.AbsBullet
 * @see AbsPowerUp
 * @see AbsPlayer
 */
public class MovementSystem {

    /**
     * Updates the movement of enemies in the game.
     *
     * @param components The list of MovementComponent objects representing the movement components of enemies.
     * @param enemy      The enemy object being updated.
     * @param fieldWidth The width of the game field.
     */
    public void updateEnemies(List<MovementComponent> components, AbsEnemy enemy, int fieldWidth) {
        int min = components.stream().mapToInt(MovementComponent::getPosX).min().getAsInt();
        int max = components.stream().mapToInt(MovementComponent::getPosX).max().getAsInt();
        for (MovementComponent component : components) {
            if (min == 0 || max + enemy.getWidth() >= fieldWidth) {
                component.setSpeedX(component.getSpeedX() * -1);
                component.setPosY(component.getPosY() + component.getSpeedY());
            }
            component.setPosX(component.getPosX() + component.getSpeedX());
        }
    }

    /**
     * Updates the movement of bullets in the game.
     *
     * @param components The list of MovementComponent objects representing the movement components of bullets.
     */
    public void updateBullets(List<MovementComponent> components) {
        for (MovementComponent component : components) {
            component.setPosX(component.getPosX() + component.getSpeedX());
            component.setPosY(component.getPosY() + component.getSpeedY());
        }
    }

    /**
     * Updates the movement of power-ups in the game.
     *
     * @param components The list of MovementComponent objects representing the movement components of power-ups.
     * @param powerUp    The power-up object being updated.
     * @param fieldWidth The width of the game field.
     */
    public void updatePowerup(List<MovementComponent> components, AbsPowerUp powerUp, int fieldWidth) {
        for (MovementComponent component : components) {
            component.setPosX(component.getPosX() + component.getSpeedX());
            if (component.getPosX() == 0 || component.getPosX() + powerUp.getWidth() >= fieldWidth) {
                component.setSpeedX(component.getSpeedX() * -1);
            }
        }
    }

    /**
     * Updates the movement of players in the game.
     *
     * @param players    The list of AbsPlayer objects representing the players in the game.
     * @param fieldWidth The width of the game field.
     * @param input      The input object representing the player's input.
     */
    public void updatePlayers(LinkedList<AbsPlayer> players, int fieldWidth, AbsInput input) {
        for (AbsPlayer player : players) {
            Point pos = player.getMovementComponent().getPosition();
            MovementComponent component = player.getMovementComponent();
            if (input.getRightPressed().get(player.getOwnId()) && pos.getX() + player.getWidth() < fieldWidth) {
                component.setPosX((int) (pos.getX() + (component.getSpeedX() * player.getMovementBooster())));
            }
            if (input.getLeftPressed().get(player.getOwnId()) && pos.getX() > 0) {
                component.setPosX((int) (pos.getX() - (component.getSpeedX() * player.getMovementBooster())));
            }
        }
    }
}