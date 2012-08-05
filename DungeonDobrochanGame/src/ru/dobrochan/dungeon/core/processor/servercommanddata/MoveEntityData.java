
package ru.dobrochan.dungeon.core.processor.servercommanddata;

import ru.dobrochan.dungeon.core.Cell;

/**
 * Provides data for MOVE_ENTITY command.
 *
 * @author SkinnyMan
 */
public class MoveEntityData
{
	private int id;
	private Cell[] path;

	/**
	 * Returns the ID of moving entity.
	 *
	 * @return ID
	 */
	public int getEntityID() { return id; }

	/**
	 * Returns the array of cells that make the path of moving entity.
	 *
	 * @return array of cells
	 */
	public Cell[] getPath() { return path; }

	/**
	 * Initialize a new instance of MoveEntityData class with specified entity's ID and path.
	 *
	 * @param id ID of moving entity
	 * @param path array of cells that make the path
	 */
	public MoveEntityData(int id, Cell[] path)
	{
		this.id = id;
		this.path = path;
	}

}
