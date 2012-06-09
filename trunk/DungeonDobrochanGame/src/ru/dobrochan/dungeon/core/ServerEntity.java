
package ru.dobrochan.dungeon.core;

import java.util.Map;

/**
 *
 * @author SkinnyMan
 */
public class ServerEntity extends Entity
{


	@Override
	public void setParam(String name, Object value)
	{
		super.setParam(name, value);
		// Уведомление об изменениях.
	}

	@Override
	public void setParams(Map params)
	{
		super.setParams(params);
		// Уведомление об изменениях.
	}


}
