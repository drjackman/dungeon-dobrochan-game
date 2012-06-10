/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dobrochan.dungeon.ui;

import org.newdawn.slick.gui.GUIContext;
import ru.dobrochan.dungeon.content.ResourceManager;
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
		normal = ResourceManager.getInstance().getImage("MENU_BUTTON_NORMAL");
		hovered = ResourceManager.getInstance().getImage("MENU_BUTTON_FOCUSED");
		pressed = ResourceManager.getInstance().getImage("MENU_BUTTON_PRESSED");
		disabled = ResourceManager.getInstance().getImage("MENU_BUTTON_DISABLED");
		setWidth(normal.getWidth());
		setHeight(normal.getHeight());
		setLocation(x, y);
	}

}

