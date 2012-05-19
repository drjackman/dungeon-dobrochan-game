
package ru.dobrochan.dungeon.ui;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author SkinnyMan
 */
public class TextButton extends Button
{
	private String label;

	public TextButton(GUIContext context, String label)
	{
		this(context, label, 0, 0, 100, 20);
	}

	public TextButton(GUIContext context, String label, int x, int y, int width, int height)
	{
		this(context, label, x, y, width, height, null, null, null, null);
	}

	public TextButton(GUIContext context, String label, int x, int y, int width, int height,
					Image normal, Image hovered, Image pressed, Image disabled)
	{
		super(context, x, y, width, height, normal, hovered, pressed, disabled);
		this.label = label;
	}

	@Override
	public void render(GUIContext container, Graphics g) throws SlickException
	{
		super.render(container, g);
		Font f = g.getFont();
		int w = f.getWidth(label);
		int h = f.getHeight(label);
		int lx = getX() + (this.getWidth() - w)/2;
		int ly = getY() + (this.getHeight() - h)/2;
		g.drawString(label, lx, ly);
	}


}
