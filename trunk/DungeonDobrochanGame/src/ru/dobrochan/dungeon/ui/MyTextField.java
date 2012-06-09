
package ru.dobrochan.dungeon.ui;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Font;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

/**
 *
 * @author SkinnyMan
 */
public class MyTextField extends TextField
{
	public MyTextField(GUIContext container, Font font, int x, int y, int width, int height)
	{
		super(container, font, x, y, width, height);
	}

	public MyTextField(GUIContext container, Font font, int x, int y, int width, int height, ComponentListener listener)
	{
		super(container, font, x, y, width, height, listener);
	}

	@Override
	public void keyPressed(int key, char c)
	{
		super.keyPressed(key, '\uffd1');
		System.out.println("Key pressed: " + key + " " + c);
	}

	@Override
	public void keyReleased(int key, char c)
	{
		try
		{

			char[] cc = new char[1];
			cc[0] = c;
			String str = new String(cc);
			byte[] bytes = str.getBytes("Cp1251");
			cc[0] = (char)(bytes[0]& 0x00FF);
			super.keyReleased(key, '\uffd1');

			System.out.println("Key released: " + key + " " + c);
		}
		catch (UnsupportedEncodingException ex)
		{
			Logger.getLogger(MyTextField.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


}
