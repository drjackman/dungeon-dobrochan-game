
package ru.dobrochan.dungeon.core;

import static ru.dobrochan.dungeon.consts.Surface.*;
import ru.dobrochan.dungeon.Settings;

/**
 *
 * @author SkinnyMan
 */
public class GameFieldBuilder
{
	private GameField gameField;

	private int widthCells = Settings.FIELD_WIDTH;
	private int heightCells = Settings.FIELD_HEIGHT;

	private int[][] field;

	public GameFieldBuilder()
	{
		field = new int[heightCells][widthCells];
	}

	public GameField buildGameField()
	{
		return new GameField(field);
	}

	public void setCell(int x, int y, int surface)
	{
		field[y][x] = surface;
	}

	public void setRect(int x, int y, int w, int h, int surface)
	{
		for (int i = y; i < y + h; i++)
			for (int j = x; j < x + w; j++)
				field[i][j] = surface;
	}

	public void clearCell(int x, int y)
	{
		field[y][x] = SURF_EMPTY;
	}

	public void clearRect(int x, int y, int w, int h)
	{
		for (int i = y; i < y + h; i++)
			for (int j = x; j < x + w; j++)
				field[i][j] = SURF_EMPTY;
	}

}
