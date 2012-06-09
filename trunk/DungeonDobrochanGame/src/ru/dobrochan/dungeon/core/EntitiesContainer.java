
package ru.dobrochan.dungeon.core;

import static ru.dobrochan.dungeon.consts.UnitParams.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SkinnyMan
 */
public class EntitiesContainer implements IEntityContainer
{
	private ArrayList<IEntity> entitys;

	public EntitiesContainer()
	{
		entitys = new ArrayList<IEntity>();
	}

	@Override
	public void addEntity(IEntity entity)
	{
		entitys.add(entity);
	}

	@Override
	public void removeEntity(IEntity entity)
	{
		entitys.remove(entity);
	}

	@Override
	public Iterator<IEntity> iterator()
	{
		return entitys.iterator();
	}

	@Override
	public int size()
	{
		return entitys.size();
	}

	@Override
	public IEntity getEntityFromXY(int x, int y)
	{
		for (IEntity entity : entitys)
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

}
