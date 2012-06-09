
package ru.dobrochan.dungeon.consts;

import static ru.dobrochan.dungeon.consts.ObstaclesAndMovement.*;

/**
 *
 * @author SkinnyMan
 */
public class Surface
{
	public static final int SURF_EMPTY = 0;
	public static final int SURF_PIT = 1;
	public static final int SURF_WATER = 2;
	public static final int SURF_ROCK = 3;
	public static final int SURF_WALL = 4;

	public static int getPassability(int surface)
	{
		switch (surface)
		{
			case SURF_EMPTY:
				return OBSTACLES_NONE;
			case SURF_PIT:
			case SURF_WATER:
				return OBSTACLES_SMALL;
			default:
				return OBSTACLES_BIG;
		}
	}

	private Surface() { }
}
