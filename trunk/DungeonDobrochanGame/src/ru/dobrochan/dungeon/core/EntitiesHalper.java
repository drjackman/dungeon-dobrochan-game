package ru.dobrochan.dungeon.core;

import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 * Предоставляет вспомогательные методы экземпляра класса для работы с коллекцией игровых сущностей.
 *
 * @author SkinnyMan
 */
public class EntitiesHalper
{
	private IEntityContainer entities;

	/**
	 * Выполняет инициализацию нового экземпляра класса EntitiesHalper для заданного контейнера.
	 *
	 * @param entities контейнер, для которогсоздается EntitiesHalper
	 */
	public EntitiesHalper(IEntityContainer entities)
	{
		this.entities = entities;
	}

	/**
	 * Получает сущность по координатам.
	 *
	 * @param x координата X ячейки
	 * @param y координата Y ячейки
	 * @return игровая сущность, находящаяся в данной ячейке.
	 */
	public IEntity getEntityFromXY(int x, int y)
	{
		for (IEntity entity : entities)
		{
			int entityX = (Integer)entity.getParam(U_X);
			int entityY = (Integer)entity.getParam(U_Y);
			int entityW = (Integer)entity.getParam(U_WIDTH);
			int entityH = (Integer)entity.getParam(U_HEIGHT);
			if (x >= entityX && x < entityX + entityW)
				if (y >= entityY && y < entityY + entityH)
					return entity;
		}
		return null;
	}
}
