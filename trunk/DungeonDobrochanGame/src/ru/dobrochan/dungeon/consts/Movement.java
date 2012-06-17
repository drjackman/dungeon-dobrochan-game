
package ru.dobrochan.dungeon.consts;

/**
 * Содержит константы, характеризующие возможность сущности перемещаться.
 *
 * @author SkinnyMan
 */
public class Movement
{
	/** Сущность не может перемещаться. */
	public static final int MOVEMENT_IMMOBILE = 1;

	/** Сущность может перемещаться по земле. */
	public static final int MOVEMENT_GROUND = 2;

	/** Сущность может перемещаться по воздуху. */
	public static final int MOVEMENT_FLY = 3;

	/** Сущность может телепортироваться. */
	public static final int MOVEMENT_TELEPORT = 4;

	private Movement() {}
}
