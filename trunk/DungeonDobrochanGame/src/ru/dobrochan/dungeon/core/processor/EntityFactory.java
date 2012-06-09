
package ru.dobrochan.dungeon.core.processor;

import java.util.ArrayList;
import java.util.Arrays;
import ru.dobrochan.dungeon.core.IEntity;

/**
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
		for(IEntity ent : entityTypes)
		{
			if (ent.getParam("name") == type)
			{
				entity = ent.Clone();
			}
		}

		return entity;
	}
}
