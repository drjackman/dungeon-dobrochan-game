
package ru.dobrochan.dungeon.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author SkinnyMan
 */
public class Entity implements IEntity
{
	private HashMap<String, Object> params = new HashMap<String, Object>();

	@Override
	public void setParam(String name, Object value)
	{
		params.put(name, value);
	}

	@Override
	public void setParams(Map params)
	{
		this.params.putAll(params);
	}

	@Override
	public Object getParam(String name)
	{
		return params.get(name);
	}

	@Override
	public Set<String> getParams()
	{
		return params.keySet();
	}

	@Override
	public IEntity Clone()
	{
		IEntity entity = new Entity();
		Set<String> sParams = params.keySet();
		for (String param : sParams)
		{
			entity.setParam(param, getParam(param));
		}
		return entity;
	}
}
