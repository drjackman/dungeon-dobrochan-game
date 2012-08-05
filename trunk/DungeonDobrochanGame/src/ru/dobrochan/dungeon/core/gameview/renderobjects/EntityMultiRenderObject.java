
package ru.dobrochan.dungeon.core.gameview.renderobjects;

import ru.dobrochan.dungeon.core.gameview.renderobjects.EntityRenderObject;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;

/**
 *
 * @author SkinnyMan
 */
public class EntityMultiRenderObject extends EntityRenderObject
{
	ArrayList<EntityRenderObject> renderObjects;

	public EntityMultiRenderObject()
	{
		renderObjects = new ArrayList<EntityRenderObject>();
	}

	public void addRenderObject(EntityRenderObject renderObject)
	{
		renderObject.setOwner(getOwner());
		renderObject.setGameFieldView(getGameFieldView());
		renderObjects.add(renderObject);
	}

	@Override
	public void setGameFieldView(GameFieldView fieldView)
	{
		super.setGameFieldView(fieldView);
		for (EntityRenderObject rObj : renderObjects)
		{
			rObj.setGameFieldView(fieldView);
		}
	}

	@Override
	public void stateChanged()
	{
		for (EntityRenderObject rObj : renderObjects)
		{
			rObj.stateChanged();
		}
	}

	@Override
	public void update(int delta)
	{
		for (EntityRenderObject rObj : renderObjects)
		{
			rObj.update(delta);
		}
	}

	@Override
	public void render(Graphics g)
	{
		for (EntityRenderObject rObj : renderObjects)
		{
			rObj.render(g);
		}
	}
}
