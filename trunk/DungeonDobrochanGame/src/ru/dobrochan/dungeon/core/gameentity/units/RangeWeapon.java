
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 *
 * @author SkinnyMan
 */
public enum RangeWeapon
{
	NONE(0),
	THROW(1),
	BOW(2),
	CROSSBOW(3);

	private int value;

	public int value() { return value; }

	private RangeWeapon(int value)
	{
		this.value = value;
	}
}
