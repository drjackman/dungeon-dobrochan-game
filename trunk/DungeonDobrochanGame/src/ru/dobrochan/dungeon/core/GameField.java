
package ru.dobrochan.dungeon.core;

import java.awt.List;
import java.util.ArrayList;
import ru.dobrochan.dungeon.Settings;
import static ru.dobrochan.dungeon.consts.Surface.*;

/**
 * Предстваляет декоративный рельеф игрового поля.
 *
 * @author SkinnyMan
 */
public class GameField
{
	private int widthCells = Settings.FIELD_WIDTH;
	private int heightCells = Settings.FIELD_HEIGHT;

	private int[][] field;

	// Будет убрано.
	private int[][] passableMap;

	/**
	 * Возвращает ширину игрового поля.
	 *
	 * @return ширина игрового поля в ячейках
	 */
	public int getWidth() { return widthCells; }

	/**
	 * Возвращает высоту игрового поля.
	 *
	 * @return высота игрового поля в ячейках
	 */
	public int getHeight() { return heightCells; }

	/**
	 * Инициализирует новый экземпляр класса с предварительно созданным полем.
	 *
	 * @param field поле с иденификаторами ландшафта ячеек
	 */
	public GameField(int[][] field)
	{
		this.field = field;
		passableMap = new int[heightCells][widthCells];
		buildPassableMap();
	}

	/**
	 * Инициализирует новый экземпляр класса с пустым полем.
	 */
	public GameField()
	{
		field = new int[heightCells][widthCells];
		passableMap = new int[heightCells][widthCells];
		buildPassableMap();
	}

	/**
	 * Возвращает идентификатор рельефа ячейки.
	 *
	 * @param x координата X ячейки
	 * @param y координата Y ячейки
	 * @return идентификатор рельефа ячейки
	 */
	public int getCell(int x, int y)
	{
		return field[y][x];
	}

	/**
	 * Возвращает поле.
	 *
	 * @return массив, представляющий поле.
	 */
	public int[][] getField()
	{
		return field;
	}

	/**
	 * @deprecated не нужно
	 * @return
	 */
	public int[][] getPassableMap()
	{
		return passableMap;
	}

	/**
	 * @deprecated не нужно
	 */
	private void buildPassableMap()
	{

	}

}
