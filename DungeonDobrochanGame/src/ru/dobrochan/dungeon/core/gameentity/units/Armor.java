
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 *
 * @author SkinnyMan
 */
public enum Armor
{
	NONE(1),
	LIGHT(2),
	HEAVY(3),
	JUGGERNAUT(4);

	private int value;

	public int value() { return value; }

	private Armor(int value)
	{
		this.value = value;
	}
}
