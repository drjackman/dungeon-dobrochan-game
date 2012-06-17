
package ru.dobrochan.dungeon.consts;

/**
 * Содержит константы, характеризующие препядствия.
 *
 * @author SkinnyMan
 */
public class Obstacles
{
	/** Препядствие отсутствует. Проходимо для всех. */
	public static final int OBSTACLES_NONE = 1;

	/** Небольшое препядствие. Непроходимо для пеших, проходимо для остальных. */
	public static final int OBSTACLES_SMALL = 2;

	/** Большое препядствие. Непроходимо для пеших и летающих, проходимо для остальных. */
	public static final int OBSTACLES_BIG = 3;

	private Obstacles() {}
}
