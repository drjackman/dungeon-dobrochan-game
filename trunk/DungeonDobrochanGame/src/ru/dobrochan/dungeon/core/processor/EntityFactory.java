
package ru.dobrochan.dungeon.core.processor;

import java.util.ArrayList;
import java.util.Arrays;
import ru.dobrochan.dungeon.core.IEntity;

/**
 * Класс для создания игровых сущностей. In progress…
 *
 * @author SkinnyMan
 */
public class EntityFactory
{
	ArrayList<IEntity> entityTypes;

	public EntityFactory()
	{
		entityTypes = new ArrayList<IEntity>();
	}

	public void addTypes(IEntity[] entities)
	{
		entityTypes.addAll(Arrays.asList(entities));
	}

	public IEntity CreateEntityByType(String type)
	{
		IEntity entity = null;
		for(IEntity typeEnt : entityTypes)
		{
			if (typeEnt.getParam("name") == type)
			{
				entity = typeEnt;
				break;
			}
		}
		if (entity == null)
			return null;


		return entity;
	}
}
