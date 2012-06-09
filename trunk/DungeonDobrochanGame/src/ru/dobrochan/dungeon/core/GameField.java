
package ru.dobrochan.dungeon.core;

import java.awt.List;
import java.util.ArrayList;
import ru.dobrochan.dungeon.Settings;
import static ru.dobrochan.dungeon.consts.Surface.*;

/**
 *
 * @author SkinnyMan
 */
public class GameField
{
	private int widthCells = Settings.FIELD_WIDTH;
	private int heightCells = Settings.FIELD_HEIGHT;

	private int[][] field;
	private int[][] passableMap;

	public int getWidth() { return widthCells; }
	public int getHeight() { return heightCells; }

	public GameField(int[][] field)
	{
		this.field = field;
		passableMap = new int[heightCells][widthCells];
		buildPassableMap();
	}

	public GameField()
	{
		field = new int[heightCells][widthCells];
		passableMap = new int[heightCells][widthCells];
		buildPassableMap();
	}

	public int getCell(int x, int y)
	{
		return field[y][x];
	}

	public int[][] getField()
	{
		return field;
	}

	public int[][] getPassableMap()
	{
		return passableMap;
	}

	private void buildPassableMap()
	{
		for (int i = 0; i < heightCells; i++)
			for (int j = 0; j < widthCells; j++)
			{
				passableMap[i][j] = getPassability(field[i][j]);
			}
	}

}
