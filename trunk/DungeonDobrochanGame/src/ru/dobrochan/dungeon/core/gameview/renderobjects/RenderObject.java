
package ru.dobrochan.dungeon.core.gameview.renderobjects;

import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import ru.dobrochan.dungeon.core.gameview.renderobjects.IRenderObject;

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
