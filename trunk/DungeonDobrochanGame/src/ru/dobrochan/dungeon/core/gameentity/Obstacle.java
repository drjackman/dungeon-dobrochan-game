
package ru.dobrochan.dungeon.core.gameentity;

/**
 * Defines obstacles and their passability.
 *
 * @author SkinnyMan
 */
public enum Obstacle
{
	/**
	 * There is no obstacle. Passable for all.
	 */
	NONE(1),

	/**
	 * Small obstacle. Impassable for ground units, passable for other.
	 */
	SMALL(2),

	/**
	 * Big obstacle. Impassable for ground and flying units, passable for teleporting unit.
	 */
	BIG(3),

	/**
	 * Impassable obstacle.
	 */
	PATHLESS(4);

	private final int passability;

	/**
	 * Value of passability. Larger values correspond worst passability.
	 *
	 * @return value of passability
	 */
	public int passability() { return passability; }

	private Obstacle(int passability)
	{
		this.passability = passability;
	}
}
