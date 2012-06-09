
package ru.dobrochan.dungeon.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import static ru.dobrochan.dungeon.consts.UnitParams.*;
import static ru.dobrochan.dungeon.consts.ObstaclesAndMovement.*;

/**
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

	public Cell[] getReachableCells(IEntity entity)
	{
		int untiSize = (Integer)entity.getParam(U_SIZE);
		int unitX = (Integer)entity.getParam(U_X);
		int unitY = (Integer)entity.getParam(U_Y);
		int unitMovement = (Integer)entity.getParam(U_MOVEMENT);

		obstacles = getGameField().getPassableMap();
		mapHeight = obstacles.length;
		mapWidth = obstacles[0].length;

		return null;
	}

	public Cell[] getPath(IEntity entity, Cell target)
	{
		return null;
	}

	private void buildWaveAlgorithmMap(IEntity entity)
	{
		int unitMovement = (Integer)entity.getParam(U_MOVEMENT);
		int untiSize = (Integer)entity.getParam(U_SIZE);
		// Добавляем юниты.
		for (int i = 0; i < mapHeight; i++)
			for (int j = 0; j < mapWidth; j++)
			{
				IEntity entityFromXY = getEntityContainer().getEntityFromXY(j, i);
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

	private void setUnpass(int i, int j, int size)
	{
		for (int y = i; y > i - size; y--)
			for (int x = j; x > j - size; x--)
				if (x >= 0 && x < mapWidth && y >= 0 && y < mapHeight)
					passableMap[y][x] = UNPASSABLE;
	}

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
			tmpWave = wave1;
			wave1 = wave2;
			wave2 = tmpWave;
			step++;
		}
		while (step < steps && wave2.size() > 0);
	}

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

	public GameField getGameField() { return gameField;	}

	public void setGameField(GameField gameField) { this.gameField = gameField;	}

	public IEntityContainer getEntityContainer() { return entities; }

	public void setEntityContainer(IEntityContainer entities) { this.entities = entities; }
}
