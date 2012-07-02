package ru.dobrochan.dungeon.core.renderobjects;

import ru.dobrochan.dungeon.core.GameFieldView;
import ru.dobrochan.dungeon.core.IEntity;
import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 *
 * @author SkinnyMan
 */
public abstract class EntityRenderObject extends RenderObject
{
	private IEntity owner;

	private int ownerX;
	private int ownerY;

	protected int getOwnerX() { return ownerX; }
	protected int getOwnerY() { return ownerY; }

	public void setOwner(IEntity owner)
	{
		this.owner = owner;
	}

	public IEntity getOwner()
	{
		return owner;
	}

	@Override
	public void stateChanged()
	{
		GameFieldView gameField = getGameFieldView();
		int ownerCellX = (Integer)getOwner().getParam(U_X);
		int ownerCellY = (Integer)getOwner().getParam(U_Y);
		ownerX = gameField.getX() + gameField.getCellWidth() * ownerCellX;
		ownerY = gameField.getY() + gameField.getCellHeight() * ownerCellY;
	}



}
