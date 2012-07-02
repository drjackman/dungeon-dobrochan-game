
package ru.dobrochan.dungeon.core;

import java.util.ArrayList;
import java.util.Iterator;
import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 * Представляет контейнер для сущностей.
 *
 * @author SkinnyMan
 */
public class EntityContainer implements IEntityContainer
{
	private ArrayList<IEntity> entitys;

	/**
	 * Инициализирует новый экземпляр класса EntitiesContainer
	 */
	public EntityContainer()
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
}
