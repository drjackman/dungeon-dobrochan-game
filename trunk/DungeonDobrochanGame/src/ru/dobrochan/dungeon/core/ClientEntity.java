
package ru.dobrochan.dungeon.core;

import ru.dobrochan.dungeon.core.gameview.renderobjects.IRenderObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 *
 * @deprecated
 * @author SkinnyMan
 */
public class ClientEntity extends Entity
{
	private IRenderObject renderObject;

	private IEntity entity;

	public ClientEntity(IEntity entity)
	{
		this.entity = entity;
	}

	@Override
	public void setParam(String name, Object value)
	{
		entity.setParam(name, value);
		renderObject.stateChanged();
	}

	@Override
	public Object getParam(String name)
	{
		return entity.getParam(name);
	}

	public void setRenderObject(IRenderObject aspect)
	{
		renderObject = aspect;
	}

	public IRenderObject getRenderObject()
	{
		return renderObject;
	}
}
