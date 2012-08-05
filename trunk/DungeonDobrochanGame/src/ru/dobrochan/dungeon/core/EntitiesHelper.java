package ru.dobrochan.dungeon.core;

import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 * Exposes supporting instance methods for work with EntityContainer.
 *
 * @author SkinnyMan
 */
public class EntitiesHelper
{
	private IEntityContainer entities;

	/**
	 * Initialize a new instance of EntitiesHalper class for specified EntityContainer.
	 *
	 * @param entities the container
	 */
	public EntitiesHelper(IEntityContainer entities)
	{
		this.entities = entities;
	}

	/**
	 * Gets the entity by position.
	 *
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @return the entity that placed in specified position
	 */
	public IEntity getEntityFromXY(int x, int y)
	{
		for (IEntity entity : entities)
		{
			int entityX = (Integer)entity.getParam(U_X);
			int entityY = (Integer)entity.getParam(U_Y);
			int entityW = (Integer)entity.getParam(U_WIDTH);
			int entityH = (Integer)entity.getParam(U_HEIGHT);
			if (x >= entityX && x < entityX + entityW)
				if (y >= entityY && y < entityY + entityH)
					return entity;
		}
		return null;
	}

	/**
	 * Gets the entity with specified ID.
	 *
	 * @param id ID
	 * @return entity with specified ID
	 */
	public IEntity getEntityByID(int id)
	{
		for (IEntity entity : entities)
		{
			if ((Integer)entity.getParam(U_ID) == id)
				return entity;
		}
		return null;
	}
}
