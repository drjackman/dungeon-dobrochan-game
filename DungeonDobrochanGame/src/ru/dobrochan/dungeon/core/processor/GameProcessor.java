
package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.GameField;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.IEntityContainer;

/**
 *
 * @author SkinnyMan
 */
public class GameProcessor
{
	private GameField gameField;

	private IEntityContainer entities;

	public GameProcessor()
	{

	}

	public void LoadUnitTypes()
	{

	}

	public void recalcParams(IEntity entity)
	{

	}

	public void ValidateEntity(IEntity entity)
	{

	}

	public GameField getGameField() { return gameField; }

	public void setGameField(GameField gameField) { this.gameField = gameField; }

	public IEntityContainer getEntities() { return entities; }

	public void setEntities(IEntityContainer entities) { this.entities = entities; }
}
