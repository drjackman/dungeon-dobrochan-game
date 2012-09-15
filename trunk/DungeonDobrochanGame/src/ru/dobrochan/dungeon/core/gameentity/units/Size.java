
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 *
 * @author SkinnyMan
 */
public enum Size
{
	SMALL(1),
	NORMAL(2),
	BIG(3),
	GIANT(4);

	private int size;

	public int value() { return size; }

	private Size(int size)
	{
		this.size = size;
	}
}
