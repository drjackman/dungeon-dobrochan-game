
package ru.dobrochan.dungeon.core.gameentity.units;

import ru.dobrochan.dungeon.core.gameentity.*;

/**
 *
 * @author SkinnyMan
 */
public class UnitEntity extends GameEntity implements IPlaceholder
{
	private int x;
	private int y;
	private int width;
	private int height;
	private Obstacle obstacle;

	// Zaebus' to write accessors.

	// Units params.

	public int owner;

	public int lvl;

	// Base params.

	public int bStr;
	public int bStam;
	public int bAgi;
	public int bSens;
	public int bWill;

	public Armor bArmor;
	public Size bSize;
	public Movement bMovement;
	public MeleeWeapon bMeleeWeapon;
	public RangeWeapon bRangeWeapon;

	public float bSkillMelee;
	public float bSkillRange;
	public float bSkillDodge;
	public float bSkillTactic;
	public float bSkillMagic;
	public float bSkillControl;
	public float bSkillConcentration;

	// Recalculated params.

	public int rStr;
	public int rStam;
	public int rAgi;
	public int rSens;
	public int rWill;

	public Armor rArmor;
	public Size rSize;
	public Movement rMovement;
	public MeleeWeapon rMeleeWeapon;
	public RangeWeapon rRangeWeapon;

	public float rSkillMelee;
	public float rSkillRange;
	public float rSkillDodge;
	public float rSkillTactic;
	public float rSkillMagic;
	public float rSkillControl;
	public float rSkillConcentration;

	// Secondary params.

	public int sTurnPriority;

	public int sMaxHP;
	public int sHP;

	public int sMaxAP;		// Action Point
	public int sAP;

	public int sRegeneration;

	public int sMeleeBonus;
	public int sMeleeHit;
	public int sMeleeDamage;
	public int sMeleeAPConsumption;

	public int sRangeBonus;
	public int sRangeHit;
	public int sRangeDamage;
	public int sRangeAPConsumption;

	public int sFleeRate;


	@Override
	public int getX() { return x; }

	@Override
	public void setX(int x) { this.x = x; }

	@Override
	public int getY() { return y; }

	@Override
	public void setY(int y) { this.y = y; }

	@Override
	public int getWidth() { return width; }

	@Override
	public void setWidth(int width) { throw new UnsupportedOperationException("Not supported yet."); }

	@Override
	public int getHeight() { return height; }

	@Override
	public void setHeight(int height) { throw new UnsupportedOperationException("Not supported yet."); }

	@Override
	public Obstacle getObstacle() { return obstacle; }

	@Override
	public void setObstacle(Obstacle obstacle) { this.obstacle = obstacle; }

	public UnitEntity(String type)
	{
		super(EntityClass.UNIT, type);
	}

	public UnitEntity(int id, String type)
	{
		super(id, EntityClass.UNIT, type);
	}
}
