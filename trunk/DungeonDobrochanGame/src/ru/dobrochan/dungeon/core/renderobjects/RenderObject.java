
package ru.dobrochan.dungeon.core.renderobjects;

import ru.dobrochan.dungeon.core.GameFieldView;
import ru.dobrochan.dungeon.core.renderobjects.IRenderObject;

/**
 *
 * @author SkinnyMan
 */
public abstract class RenderObject implements IRenderObject
{
	private GameFieldView gameFieldView;

	@Override
	public int getLayer()
	{
		return 10;
	}


	public GameFieldView getGameFieldView() { return gameFieldView; }
	public void setGameFieldView(GameFieldView fieldView) { gameFieldView = fieldView; }
}
