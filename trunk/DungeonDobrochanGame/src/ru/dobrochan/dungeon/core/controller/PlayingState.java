
package ru.dobrochan.dungeon.core.controller;

import static ru.dobrochan.dungeon.consts.UnitParams.*;
import ru.dobrochan.dungeon.core.Cell;
import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.EntitiesHelper;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.gameview.CellClickEvent;
import ru.dobrochan.dungeon.core.processor.clientcommanddata.ClientCommandList;
import ru.dobrochan.dungeon.core.processor.servercommanddata.MoveEntityData;
import ru.dobrochan.dungeon.core.processor.servercommanddata.ServerCommandList;

/**
 *
 * @author SkinnyMan
 */
public class PlayingState extends GameControllerState
{
	private IEntity currentEntity;

	private EntitiesHelper halper;

	public PlayingState(GameController gameController)
	{
		super(gameController);
	}

	@Override
	void processCommand(Command command)
	{
		int id = command.getID();
		switch (id)
		{
			case ServerCommandList.ADD_ENTITY:
				assert (command.getData() instanceof IEntity);
				addEntity((IEntity)command.getData());
				break;
			case ServerCommandList.MOVE_ENTITY:
				assert (command.getData() instanceof MoveEntityData);
				moveEntity((MoveEntityData)command.getData());
				break;
			case ServerCommandList.SET_CURRENT_ENTITY:
				assert (command.getData() instanceof Integer);
				setCurrentEntity((Integer)command.getData());
				break;
			default:
				System.out.print("Invalid command.");
				break;
		}
	}

	@Override
	public void cellClicked(CellClickEvent clickEvent)
	{
		Command command = new Command(ClientCommandList.ENTITY_MOVE_REQUEST, clickEvent.getCell());
		sendCommand(command);
	}

	private void addEntity(IEntity entity)
	{
		gameController.getEntityContainer().addEntity(entity);
		gameController.getGameFieldView().addUnitEntity(entity);
	}

	private void setCurrentEntity(int id)
	{
		halper = new EntitiesHelper(gameController.getEntityContainer());
		currentEntity = halper.getEntityByID(id);
	}

	private void moveEntity(MoveEntityData data)
	{
		halper = new EntitiesHelper(gameController.getEntityContainer());
		IEntity entity = halper.getEntityByID(data.getEntityID());
		Cell[] path = data.getPath();
		Cell target = path[path.length - 1];
		entity.setParam(U_X, target.X);
		entity.setParam(U_Y, target.Y);

		gameController.getGameFieldView().moveUnitEntity(entity, path);
	}
}
