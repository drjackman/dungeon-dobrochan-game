
package ru.dobrochan.dungeon.core.processor;

import java.util.Iterator;
import ru.dobrochan.dungeon.core.EntityContainer;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.IEntityContainer;

/**
 *
 * @author SkinnyMan
 */
public class ServerEntityContainer implements IEntityContainer
{
	private IEntityContainer entities;

	public ServerEntityContainer()
	{
		entities = new EntityContainer();
	}

	public ServerEntityContainer(IEntityContainer entities)
	{
		this.entities = entities;
	}

	@Override
	public int size()
	{
		return entities.size();
	}

	@Override
	public void addEntity(IEntity entity)
	{
		entities.addEntity(entity);
	}

	@Override
	public void removeEntity(IEntity entity)
	{
		entities.removeEntity(entity);
	}

	@Override
	public Iterator<IEntity> iterator()
	{
		return entities.iterator();
	}

}
