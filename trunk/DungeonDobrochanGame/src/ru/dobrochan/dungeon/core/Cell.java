
package ru.dobrochan.dungeon.core;

/**
 * Представляет координаты ячейки поля.
 *
 * @author SkinnyMan
 */
public class Cell
{
	/** Координата X. */
	public int X;

	/** Координата Y. */
	public int Y;

	/**
	 * Инициализирует новый экземпляр класса Cell с координатами по умолчанию.
	 */
	public Cell()
	{
		X = 0;
		Y = 0;
	}

	/**
	 * Инициализирует новый экземпляр класса Cell с заданными координатами.
	 * @param x координата X
	 * @param y координата Y
	 */
	public Cell(int x, int y)
	{
		X = x;
		Y = y;
	}
}
