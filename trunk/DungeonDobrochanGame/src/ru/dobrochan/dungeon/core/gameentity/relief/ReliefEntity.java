
package ru.dobrochan.dungeon.core.gameentity.relief;

import ru.dobrochan.dungeon.core.gameentity.EntityClass;
import ru.dobrochan.dungeon.core.gameentity.GameEntity;
import ru.dobrochan.dungeon.core.gameentity.IPlaceholder;
import ru.dobrochan.dungeon.core.gameentity.Obstacle;

/**
 *
 * @author SkinnyMan
 */
public class ReliefEntity extends GameEntity implements IPlaceholder
{
	private int x;
	private int y;
	private int width;
	private int height;
	private Obstacle obstacle;

	@Override
	public int getX() { return x; }

	@Override
	public void setX(int x) { this.x = x; }

	@Override
	public int getY() { return y; }

	@Override
	public void setY(int y) { this.y = y; }

	@Override
	public int getWidth() { return width; }

	@Override
	public void setWidth(int width) { this.width = width; }

	@Override
	public int getHeight() { return height; }

	@Override
	public void setHeight(int height) { this.height = height; }

	@Override
	public Obstacle getObstacle() { return obstacle; }

	@Override
	public void setObstacle(Obstacle obstacle) { this.obstacle = obstacle; }

	public ReliefEntity(int id, String name)
	{
		super(id, EntityClass.RELIEF, name);
	}

	public ReliefEntity(String name)
	{
		super(EntityClass.RELIEF, name);
	}

}
