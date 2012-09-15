
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 *
 * @author SkinnyMan
 */
public enum MeleeWeapon
{
	NONE(0),
	HANDS(1),
	CLAWS(2),
	ONE_HANDED(3),
	TWO_HANDED(4);

	private int value;

	public int value() { return value; }

	private MeleeWeapon(int value)
	{
		this.value = value;
	}
}
