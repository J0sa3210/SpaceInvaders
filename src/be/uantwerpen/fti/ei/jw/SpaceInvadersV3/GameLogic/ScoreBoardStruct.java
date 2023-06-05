package be.uantwerpen.fti.ei.jw.SpaceInvadersV3.GameLogic;

/**
 * The <code>ScoreBoardStruct</code> class represents a data structure that holds information about a player's score, name, and health.
 * It is implemented as a record, providing automatic implementations of equals, hashCode, and toString methods.
 *
 * @param name   The name of the player.
 * @param points The score points of the player.
 * @param health The health of the player.
 */
public record ScoreBoardStruct(String name, int points, int health) {
}
