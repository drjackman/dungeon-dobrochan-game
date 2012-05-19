
package ru.dobrochan.dungeon.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author SkinnyMan
 */
public class GameFieldViev extends AbstractComponent
{
	private Image gridBackground;

	public GameFieldViev(GUIContext context)
	{
		super(context);
	}


	@Override
	public void render(GUIContext container, Graphics g) throws SlickException
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void setLocation(int x, int y)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getX()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getY()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getWidth()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getHeight()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
