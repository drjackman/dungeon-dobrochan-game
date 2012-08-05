
package ru.dobrochan.dungeon.core.gameview.renderobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import sun.misc.BASE64Decoder;

/**
 *
 * @author SkinnyMan
 */
public class SimpleRenderObject extends EntityRenderObject
{
	public Image image;

	@Override
	public void stateChanged()
	{
		super.stateChanged();
	}

	@Override
	public void update(int delta)
	{

	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(image, getOwnerX(), getOwnerY());
	}

}
