
package ru.dobrochan.dungeon.core.gameview;

import ru.dobrochan.dungeon.core.Cell;

/**
 * Provides data for event which occur when player click on a game field.
 *
 * @author SkinnyMan
 */
public class CellClickEvent
{
	private Cell cell;

	/**
	 * Initialize a new instance of CellClickEvent class.
	 *
	 * @param the cell that was clicked
	 */
	public CellClickEvent(Cell cell)
	{
		this.cell = cell;
	}

	/**
	 * Returns clicked cell.
	 *
	 * @return
	 */
	public Cell getCell() { return cell; }
}
