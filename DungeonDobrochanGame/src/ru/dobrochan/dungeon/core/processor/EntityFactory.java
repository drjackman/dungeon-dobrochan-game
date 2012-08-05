
package ru.dobrochan.dungeon.core.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import ru.dobrochan.dungeon.core.Entity;
import ru.dobrochan.dungeon.core.IEntity;

/**
 * Represent an entities factory.
 * Класс для создания игровых сущностей. In progress…
 *
 * @author SkinnyMan
 */
public class EntityFactory
{
	private final ArrayList<IEntity> entityTypes;

	/**
	 * Initializes a new instance of EntityFactory.
	 */
	public EntityFactory()
	{
		entityTypes = new ArrayList<IEntity>();
	}

	/**
	 * Add available entities types.
	 *
	 * @param entities
	 */
	public void addTypes(IEntity[] entities)
	{
		entityTypes.addAll(Arrays.asList(entities));
	}

	/**
	 *
	 * @param type
	 * @return
	 */
	public IEntity createEntityByType(String type)
	{
		IEntity entity = null;
		for(IEntity typeEnt : entityTypes)
		{
			if (!typeEnt.getParam("name").equals(type))
				continue;
			entity = typeEnt.clone();
			break;
		}

		return entity;
	}
}
