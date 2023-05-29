package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

import be.uantwerpen.fti.ei.jw.SpaceInvadersV3.Input.AbsInput;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MovementSystem {
    void updateEnemies(List<MovementComponent> components, AbsEnemy enemy, int fieldWidth) {
        int min = components.stream().mapToInt(MovementComponent::getPosX).min().getAsInt();
        int max = components.stream().mapToInt(MovementComponent::getPosX).max().getAsInt();
        for (MovementComponent component : components) {
            component.setPosX(component.getPosX() + component.getSpeedX());
            if (min == 0 || max + enemy.getWidth() - 1 >= fieldWidth) {
                component.setSpeedX(component.getPosX() * -1);
                component.setPosY(component.getSpeedY());
            }
        }
    }

    void updateBullets(List<MovementComponent> components) {
        for (MovementComponent component : components) {
            component.setPosX(component.getPosX() + component.getSpeedX());
            component.setPosY(component.getPosY() + component.getSpeedY());
        }
    }

    void updatePowerup(List<MovementComponent> components, AbsPowerUp powerUp, int fieldWidth) {
        for (MovementComponent component : components) {
            component.setPosX(component.getPosX() + component.getSpeedX());
            if (component.getPosX() == 0 || component.getPosX() + powerUp.getWidth() - 1 >= fieldWidth) {
                component.setSpeedX(component.getSpeedX() * -1);
            }
        }
    }

    void updatePlayers(LinkedList<AbsPlayer> players, int fieldWidth, AbsInput input) {
        for (AbsPlayer player : players){
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