/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dobrochan.dungeon.content;

import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 *
 * @author Admin
 */
public class ContentManager
{
	private static final String EXT = ".png";

	private static ContentManager instance;

	public HashMap<String, Image> imageCache;

	private ContentManager()
	{
		imageCache = new HashMap<String, Image>();
	}

	public static ContentManager getInstance()
	{
		if (instance == null)
			instance = new ContentManager();
		return instance;
	}

	public Image getImage(String path, String name)
	{
		String fullPath = path + name + EXT;
		try
		{
		Image tempImage = imageCache.get(fullPath);
		if (tempImage != null)
			return tempImage;
		tempImage = new Image(fullPath);
		imageCache.put(fullPath, tempImage);
		return tempImage;
		}
		catch (SlickException e)
		{
			return null;
		}
	}

	public Sound getSound(String path, String name)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
