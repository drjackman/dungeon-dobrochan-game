
package ru.dobrochan.dungeon.core.gameview.renderobjects;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.Cell;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 *
 * @author SkinnyMan
 */
public class MovingEntityRenderObject extends EntityRenderObject
{
	private Cell[] path;
	private int currentCell;
	private Image image;
	private double cellX;
	private double cellY;
	private int t;

	public MovingEntityRenderObject(IEntity entity, Cell[] path)
	{
		try
		{
			setOwner(entity);
			image = ResourceManager.getInstance().loadImage((String)entity.getParam("image"),
				ContentPaths.CREATURES + (String)entity.getParam("image") + ".png");
			this.path = path;
			currentCell = 0;
			t = 0;
			update(0);
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MovingEntityRenderObject.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public final void update(int delta)
	{
		final int interval = 200;
		t += 1;

		if (t > interval)
		{
			t = 0;
			currentCell++;
		}
		if (currentCell >= path.length - 1)
			return;
		Cell current = path[currentCell];
		Cell next = path[currentCell + 1];
		double d = (double)t / interval;

		cellX = current.X + (next.X - current.X) * d;
		cellY = current.Y + (next.Y - current.Y) * d;
	}

	@Override
	public void render(Graphics g)
	{
		GameFieldView gfv = getGameFieldView();
		int imgX = gfv.getX() + (int)(gfv.getCellWidth() * cellX);
		int imgY = gfv.getY() + (int)(gfv.getCellHeight() * cellY);
		g.drawImage(image, imgX, imgY);
	}

}
