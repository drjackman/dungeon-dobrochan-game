
package ru.dobrochan.dungeon.consts;

import com.sun.corba.se.spi.orb.ParserImplTableBase;


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


	private Surface() { }
}

enum Surface2
{
	EMPTY,
	PIT,
	WATER;
}

interface Surface_
{
	int
	SURF_Empty = 0,
	SURF_PIT = 1;
}

class s
{
	void f()
	{
		int s = Surface_.SURF_PIT;
		Surface2 d = Surface2.PIT;
	}
}