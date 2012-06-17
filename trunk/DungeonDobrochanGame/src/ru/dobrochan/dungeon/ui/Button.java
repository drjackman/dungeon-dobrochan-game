/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dobrochan.dungeon.ui;

import java.util.Set;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author SkinnyMan
 */
public class Button extends AbstractComponent
{
	private ActionHandler handler;

	private boolean isHovered;
	private boolean isPressed;
	private boolean isVisible;
	private boolean isEnabled;

	protected Image normal;
	protected Image hovered;
	protected Image pressed;
	protected Image disabled;

	private int x;
	private int y;

	private int width;
	private int height;

	private Rectangle zone;

	public Button(GUIContext context)
	{
		this(context, 0, 0, 100, 20);
	}

	public Button(GUIContext context, int x, int y, int width, int height)
	{
		this(context, x, y, width, height, null, null, null, null);
	}

	public Button(GUIContext context, int x, int y, int width, int height,
					Image normal, Image hovered, Image pressed, Image disabled)
	{
		super(context);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.normal = normal;
		this.hovered = hovered;
		this.pressed = pressed;
		this.disabled = disabled;
		isVisible = true;
		isEnabled = true;
		updateZone();
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException
	{
		Image currentImage;
		if (!isVisible)
			return;
		if (isEnabled)
		{
			currentImage = normal;
			if (isHovered)
				currentImage = hovered;
			if (isPressed)
				currentImage = pressed;
		}
		else
		{
			currentImage = disabled;
		}
		if (currentImage != null)
			currentImage.draw(x, y);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
	{
		updateHovered(newx, newy);
	}

	@Override
	public void mousePressed(int button, int x, int y)
	{
		updateHovered(x, y);
		if (isHovered)
			isPressed = true;
	}

	@Override
	public void mouseReleased(int button, int x, int y)
	{
		updateHovered(x, y);
		isPressed = false;
		if (isHovered)
			if (handler != null)
				handler.run();	// Click
	}

	@Override
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		updateZone();
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setActionHandler(ActionHandler handler)
	{
		this.handler = handler;
	}

	private void updateZone()
	{
		zone = new Rectangle(x, y, width, height);
	}

	private void updateHovered(int x, int y)
	{
		if (zone.contains(x, y))
			isHovered = true;
		else
			isHovered = false;
	}

	@Override
	public boolean isAcceptingInput()
	{
		return true;
	}

}
