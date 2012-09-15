
package ru.dobrochan.dungeon.core.gameentity.units;

/**
 *
 * @author SkinnyMan
 */
public class UnitEntityPrototype
{
	public String typeName;
	public String name;
	public String description;

	public String imageName;
	public String bigImageName;

	public int lvl;

	public int str;
	public int stam;
	public int agi;
	public int sens;
	public int will;

	public float skillMelee;
	public float skillRange;
	public float skillDodge;
	public float skillTactic;
	public float skillMagic;
	public float skillControl;
	public float skillConcentration;

	public Armor armor;
	public Size size;
	public Movement movement;
	public MeleeWeapon meleeWeapon;
	public RangeWeapon rangeWeapon;
}
