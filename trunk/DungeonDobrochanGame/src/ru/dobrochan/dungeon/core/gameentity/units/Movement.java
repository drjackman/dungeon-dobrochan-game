
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 * Defines passability of units.
 *
 * @author SkinnyMan
 */
public enum Movement
{
	/**
	 * The unit can't move.
	 */
	IMMOBILE(1),

	/**
	 * The unit can move only on the ground.
	 */
	GROUND(2),

	/**
	 * The unit can fly.
	 */
	FLYING(3),

	/**
	 * The unit can teleport.
	 */
	TELEPORT(4);

	private final int passability;

	/**
	 * Value of passability. Larger values correspond best passability.
	 *
	 * @return value of passability
	 */
	public int passability() { return passability; }

	private Movement(int passability)
	{
		this.passability = passability;
	}
}
