
package ru.dobrochan.dungeon.core.processor;

import java.util.Map;
import java.util.Set;
import ru.dobrochan.dungeon.core.Entity;
import ru.dobrochan.dungeon.core.IEntity;

/**
 *
 * @author SkinnyMan
 */
public class ServerEntity implements IEntity
{
	private IEntity entity;

	public ServerEntity()
	{
		entity = new Entity();
	}

	public ServerEntity(IEntity entity)
	{
		this.entity = entity;
	}

	@Override
	public void setParam(String name, Object value)
	{
		entity.setParam(name, value);
	}

	@Override
	public void setParams(Map params)
	{
		entity.setParams(params);
	}

	@Override
	public IEntity clone()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object getParam(String name)
	{
		return entity.getParam(name);
	}

	@Override
	public Set<String> getParams()
	{
		return entity.getParams();
	}


}
