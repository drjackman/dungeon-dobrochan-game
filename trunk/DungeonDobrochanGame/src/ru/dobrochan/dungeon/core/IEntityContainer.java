
package ru.dobrochan.dungeon.core;

/**
 *
 * @author SkinnyMan
 */
public interface IEntityContainer extends Iterable<IEntity>
{
	IEntity getEntityFromXY(int x, int y);
	int size();
	void addEntity(IEntity entity);
	void removeEntity(IEntity entity);
}
