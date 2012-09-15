
package ru.dobrochan.dungeon.core.gameentity;

/**
 *
 *
 * @author SkinnyMan
 */
public interface IPlaceholder
{
	int getX();
	void setX(int x);

	int getY();
	void setY(int y);

	int getWidth();
	void setWidth(int width);

	int getHeight();
	void setHeight(int height);

	Obstacle getObstacle();
	void setObstacle(Obstacle obstacle);
}
