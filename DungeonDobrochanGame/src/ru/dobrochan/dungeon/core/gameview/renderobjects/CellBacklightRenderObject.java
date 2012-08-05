
package ru.dobrochan.dungeon.core.gameview.renderobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.Cell;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;

/**
 *
 * @author SkinnyMan
 */
public class CellBacklightRenderObject extends RenderObject
{
	private static Image image = ResourceManager.getInstance().getImage("CURSOR_BACKLIGHT");

	private int x;
	private int y;
	private Cell cell;

	public void setCell(Cell cell)
	{
		GameFieldView field = getGameFieldView();
		this.cell = cell;
		if (cell == null)
			return;
		x = field.getX() + field.getCellWidth() * cell.X;
		y = field.getY() + field.getCellHeight() * cell.Y;
	}

	@Override
	public void stateChanged()
	{

	}

	@Override
	public void update(int delta)
	{

	}

	@Override
	public void render(Graphics g)
	{
		if (cell == null)
			return;
		g.drawImage(image, x, y, new Color(0.0f, 0.0f, 0.0f, 0.4f));
	}

	@Override
	public int getLayer()
	{
		return 5;
	}



}
