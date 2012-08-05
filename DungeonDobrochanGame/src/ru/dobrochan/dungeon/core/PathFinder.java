
package ru.dobrochan.dungeon.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static ru.dobrochan.dungeon.consts.UnitParams.*;
import static ru.dobrochan.dungeon.consts.Obstacles.*;

/**
 * Предоставляет методы экземпляра класса для вычисления зоны досягаемости и расчета пути.
 *
 * @author SkinnyMan
 */
public class PathFinder
{
	private static final int UNPASSABLE = -666;

	private GameField gameField;
	private IEntityContainer entities;

	private int[][] obstacles;
	private int[][] passableMap;

	private int mapHeight;
	private int mapWidth;

	/**
	 * Возвращает игровое поле.
	 *
	 * @deprecated будет убрано
	 * @return игровое поле
	 */
	public GameField getGameField() { return gameField;	}

	/**
	 * Задает игровое поле.
	 *
	 * @deprecated будет убрано
	 * @param gameField
	 */
	public void setGameField(GameField gameField) { this.gameField = gameField;	}

	/**
	 * Возвращет контейнер игровых сущностей.
	 *
	 * @return контейнер игровых сущностей
	 */
	public IEntityContainer getEntityContainer() { return entities; }

	/**
	 * Задает контейнер игровых сущностей.
	 *
	 * @param entities контейнер игровых сущностей
	 */
	public void setEntityContainer(IEntityContainer entities) { this.entities = entities; }

	/**
	 * Возвращает множество досягаемых ячеек для указанной игровой сущности.
	 *
	 * @param entity сущности, для которой требуется вычислить досягаемые ячейки
	 * @return множество досягаемых ячеек
	 */
	public Cell[] getReachableCells(IEntity entity)
	{
		int untiSize = (Integer)entity.getParam(U_SIZE);
		int unitX = (Integer)entity.getParam(U_X);
		int unitY = (Integer)entity.getParam(U_Y);
		int unitMovement = (Integer)entity.getParam(U_MOVEMENT);

		obstacles = getGameField().getPassableMap();
		mapHeight = obstacles.length;
		mapWidth = obstacles[0].length;

		// TODO : Доделать
		return null;
	}

	/**
	 * Возвращает множество ячеек являющихся петем указанной сущности к цели target.
	 *
	 * @param entity игровая сущность, для которой требуется вычислить путь
	 * @param target целевая ячейка
	 * @return множество ячеек, являющееся путем
	 */
	public Cell[] getPath(IEntity entity, Cell target)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * Подготавливает массив, представляющий карту проходимости, к расчетам с помощю волнового алгоритма.
	 *
	 * @param entity игровая сущность, для которой ведутся расчеты
	 */
	private void buildWaveAlgorithmMap(IEntity entity)
	{
		int unitMovement = (Integer)entity.getParam(U_MOVEMENT);
		int untiSize = (Integer)entity.getParam(U_SIZE);
		// Добавляем юниты.
		EntitiesHelper helper = new EntitiesHelper(entities);
		for (int i = 0; i < mapHeight; i++)
			for (int j = 0; j < mapWidth; j++)
			{
				IEntity entityFromXY = helper.getEntityFromXY(j, i);
				if (entityFromXY != null && entityFromXY != entity)
				{
					obstacles[i][j] = OBSTACLES_SMALL;
				}
			}

		passableMap = new int[mapHeight][mapWidth];
		// Расширение карты проходимости.
		for (int i = 0; i < mapHeight; i++)
			for (int j = 0; j < mapWidth; j++)
			{
				int obst = obstacles[i][j];
				if (obst >= unitMovement)
				{
					setUnpass(i, j, untiSize);
				}
			}
	}

	/**
	 * Устанавливает заданную область как непроходимую.
	 *
	 * @param i координата ячейки
	 * @param j координата ячейки
	 * @param size размер сущности, для которой происходит вычисления
	 */
	private void setUnpass(int i, int j, int size)
	{
		for (int y = i; y > i - size; y--)
			for (int x = j; x > j - size; x--)
				if (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight)
					passableMap[y][x] = UNPASSABLE;
	}

	/**
	 * Выполняет волновой алгоритм для карты проходимости.
	 *
	 * @param x координата X исходной точки
	 * @param y координата Y исходной точки
	 * @param steps количество шагов, которые можно выполнить
	 */
	private void waveAlgorithm(int x, int y, int steps)
	{
		int step = 1;
		Queue<Cell> wave1 = new LinkedList<Cell>();
		Queue<Cell> wave2 = new LinkedList<Cell>();
		Queue<Cell> tmpWave;
		wave1.add(new Cell(x, y));
		do
		{
			while(wave1.size() > 0)
			{
				ArrayList<Cell> nearCells = getNearCells(wave1.poll());
				wave2.addAll(nearCells);
				for(Cell cell : nearCells)
					passableMap[cell.Y][cell.X] = step;
			}
			// свап
			tmpWave = wave1;
			wave1 = wave2;
			wave2 = tmpWave;
			step++;
		}
		while (step < steps && wave2.size() > 0);
	}

	/**
	 * Вспомогательный метод для волнового алгоритма. Возвращает свободные соседние ячейки для указанной.
	 *
	 * @param cell ячейка, для которой находятся свободные смежные
	 * @return массив свободных соседних ячеек
	 */
	private ArrayList<Cell> getNearCells(Cell cell)
	{
		ArrayList<Cell> cells = new ArrayList<Cell>();
		int i = cell.Y;
		int j = cell.Y;
		// верхние
		if (i-1 >= 0 && j-1 >= 0 && passableMap[i-1][j-1] != UNPASSABLE)
			cells.add(new Cell(j-1, i-1));
		if (i-1 >= 0 && passableMap[i-1][j] != UNPASSABLE)
			cells.add(new Cell(j, i-1));
		if (i-1 >= 0 && j+1 < mapWidth && passableMap[i-1][j+1] != UNPASSABLE)
			cells.add(new Cell(j+1, i-1));

		// левый
		if (j-1 >= 0 && passableMap[i][j-1] != UNPASSABLE)
			cells.add(new Cell(j-1, i));
		// правый
		if (j+1 < mapWidth && passableMap[i][j-1] != UNPASSABLE)
			cells.add(new Cell(j+1, i));

		// нижние
		if (i+1 < mapHeight && j-1 >= 0 && passableMap[i+1][j-1] != UNPASSABLE)
			cells.add(new Cell(j-1, i+1));
		if (i+1 < mapHeight && passableMap[i+1][j] != UNPASSABLE)
			cells.add(new Cell(j, i+1));
		if (i+1 < mapHeight && j+1 < mapWidth && passableMap[i+1][j+1] != UNPASSABLE)
			cells.add(new Cell(j+1, i+1));

		return cells;
	}

}
