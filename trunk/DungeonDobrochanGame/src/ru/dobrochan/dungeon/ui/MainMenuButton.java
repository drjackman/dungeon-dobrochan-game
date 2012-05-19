/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dobrochan.dungeon.ui;

import org.newdawn.slick.gui.GUIContext;
import ru.dobrochan.dungeon.content.ContentManager;
import ru.dobrochan.dungeon.content.ContentPaths;
/**
 *
 * @author Admin
 */
public class MainMenuButton extends TextButton
{
	public MainMenuButton(GUIContext context, String label)
	{
		this(context, label, 0, 0);
	}

	public MainMenuButton(GUIContext context, String label, int x, int y)
	{
		super(context, label);
		normal = ContentManager.getInstance().getImage(ContentPaths.BUTTON, "LongMenuButton");
		hovered = ContentManager.getInstance().getImage(ContentPaths.BUTTON, "LongMenuButtonPreLight");
		pressed = ContentManager.getInstance().getImage(ContentPaths.BUTTON, "LongMenuButtonPressed");
		disabled = ContentManager.getInstance().getImage(ContentPaths.BUTTON, "LongMenuButtonGray");
		setWidth(normal.getWidth());
		setHeight(normal.getHeight());
		setLocation(x, y);
	}

}

