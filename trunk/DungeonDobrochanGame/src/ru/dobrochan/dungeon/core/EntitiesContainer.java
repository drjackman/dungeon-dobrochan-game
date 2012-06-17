
package ru.dobrochan.dungeon.core;

import java.util.ArrayList;
import java.util.Iterator;
import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 * Представляет контейнер для сущностей.
 *
 * @author SkinnyMan
 */
public class EntitiesContainer implements IEntityContainer
{
	private ArrayList<IEntity> entitys;

	/**
	 * Инициализирует новый экземпляр класса EntitiesContainer
	 */
	public EntitiesContainer()
	{
		entitys = new ArrayList<IEntity>();
	}

	/**
	 * @see IEntityContainer#addEntity(ru.dobrochan.dungeon.core.IEntity)
	 */
	@Override
	public void addEntity(IEntity entity)
	{
		entitys.add(entity);
	}

	/**
	 * @see IEntityContainer#removeEntity(ru.dobrochan.dungeon.core.IEntity)
	 */
	@Override
	public void removeEntity(IEntity entity)
	{
		entitys.remove(entity);
	}

	/**
	 * @see Iterable#iterator()
	 */
	@Override
	public Iterator<IEntity> iterator()
	{
		return entitys.iterator();
	}

	/**
	 * @see IEntityContainer#size()
	 */
	@Override
	public int size()
	{
		return entitys.size();
	}

	/**
	 * @see IEntityContainer#getEntityFromXY(int, int)
	 */
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
