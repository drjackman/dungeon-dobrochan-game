
package ru.dobrochan.dungeon.content;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.opengl.CursorLoader;

/**
 *
 * @author SkinnyMan
 */
public class CursorData
{
	private Cursor cursor;
	private int hotSpotX;
	private int hotSpotY;

	public Cursor getCursor() { return cursor; }
	public int getHotSpotX() { return hotSpotX; }
	public int getHotSpotY() { return hotSpotY; }

	public CursorData(String path, int hotX, int hotY)
	{
		try
		{
			cursor = CursorLoader.get().getCursor(path, hotX, hotX);
		}
		catch (IOException ex)
		{
			Logger.getLogger(CursorData.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (LWJGLException ex)
		{
			Logger.getLogger(CursorData.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
