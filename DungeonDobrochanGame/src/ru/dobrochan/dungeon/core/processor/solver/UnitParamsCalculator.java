
package ru.dobrochan.dungeon.core.processor.solver;

import ru.dobrochan.dungeon.core.gameentity.units.*;

/**
 *
 * @author SkinnyMan
 */
public class UnitParamsCalculator
{
	/**
	 *
	 *
	 * @param entity
	 */
	public static void RecalcAllParams(UnitEntity entity)
	{
		// Copy recalculated base params.

		// Apply abilities that affects base params.

		// Calc secondary params.

		// Apply abilities that affects secondary params.
	}

	public static void calcBaseParams(UnitEntity entity)
	{
		entity.rStr = entity.bStr;
		entity.rStam = entity.bStam;
		entity.rAgi = entity.bAgi;
		entity.rSens = entity.bSens;
		entity.rWill = entity.bWill;

		entity.rArmor = entity.bArmor;
		entity.rSize = entity.bSize;
		entity.rMovement = entity.bMovement;
		entity.rMeleeWeapon = entity.bMeleeWeapon;
		entity.rRangeWeapon = entity.bRangeWeapon;

		entity.rSkillMelee = entity.bSkillMelee;
		entity.rSkillRange = entity.bSkillRange;
		entity.rSkillDodge = entity.bSkillDodge;
		entity.rSkillTactic = entity.bSkillTactic;
		entity.rSkillMagic = entity.bSkillMagic;
		entity.rSkillControl = entity.bSkillControl;
		entity.rSkillConcentration = entity.bSkillConcentration;
	}

	public static void calcSecondaryParams(UnitEntity entity)
	{
		// Temp variables.
		int str = entity.rStr;
		int stam = entity.rStam;
		int agi = entity.rAgi;
		int sens = entity.rSens;
		int will = entity.rWill;

		// Note that next variables are gets the values of corresponding params.
		int armor = entity.rArmor.value();
		int size = entity.rSize.value();
		int movement = entity.rMovement.passability();
		int meleeWeapon = entity.rMeleeWeapon.value();
		int rangeWeapon = entity.rRangeWeapon.value();

		float skillMelee = entity.rSkillMelee;
		float skillRange = entity.rSkillRange;
		float skillDodge = entity.rSkillDodge;
		float skillTactic = entity.rSkillTactic;
		float skillMagic = entity.rSkillMagic;
		float skillControl = entity.rSkillControl;
		float skillConcentration = entity.rSkillConcentration;

		// TurnPriority
		entity.sTurnPriority = (int)(10 * skillTactic *(sens + 2 * movement));

		// MaxHP
		entity.sMaxHP = (int)Math.ceil((str + stam * 1.5) * size);
		if (entity.sHP > entity.sMaxHP)
			entity.sHP = entity.sMaxHP;

		// MaxAP
		entity.sMaxAP = 5 + (int)Math.floor(agi / 2);
		if (entity.sAP > entity.sMaxAP)
			entity.sAP = entity.sMaxAP;

		// // Melee // //

		// Melee attack bonus
		entity.sMeleeBonus = Math.max(str - 5, 0);		// Add points every 'str' more then 5.
		if (entity.rMeleeWeapon == MeleeWeapon.HANDS || entity.rMeleeWeapon == MeleeWeapon.CLAWS)
			entity.sMeleeBonus += size;

		// Melee hit
		entity.sMeleeHit = (int)((str + agi) * skillMelee);

		// Melee damage
		entity.sMeleeDamage = 1 + 2 * meleeWeapon + entity.sMeleeBonus;

		// Melee APConsumption
		entity.sMeleeAPConsumption = (int)Math.ceil(1 + armor/2 + size/2 + meleeWeapon/2);

		// // Range // //

		// Range attack bonus
		entity.sRangeBonus = Math.max(sens - 5, 0);		// Add points every 'sens' more then 5.
		if (entity.rRangeWeapon == RangeWeapon.THROW)
			entity.sRangeBonus += size;

		// Range hit
		entity.sRangeHit = (int)((agi + sens) * skillRange);

		// Range damage
		entity.sRangeDamage = 1 + 2 * rangeWeapon + entity.sRangeBonus;

		// Range APConsumption
		entity.sRangeAPConsumption = (int)Math.ceil(1 + armor/2 + size/2 + rangeWeapon/2);
	}

}
