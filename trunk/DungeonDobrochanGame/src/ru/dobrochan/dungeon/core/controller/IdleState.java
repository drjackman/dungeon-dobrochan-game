
package ru.dobrochan.dungeon.core.controller;

import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.gameview.CellClickEvent;
import ru.dobrochan.dungeon.core.processor.servercommanddata.ServerCommandList;

/**
 *
 * @author SkinnyMan
 */
public class IdleState extends GameControllerState
{

	public IdleState(GameController gameController)
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
			case ServerCommandList.START_GAME:
				startGame();
				break;
			default:
				System.out.print("Invalid command.");
				break;
		}
	}

	@Override
	public void cellClicked(CellClickEvent clickEvent)
	{
		// Do nothing.
	}

	private void addEntity(IEntity entity)
	{
		gameController.getEntityContainer().addEntity(entity);
		gameController.getGameFieldView().addUnitEntity(entity);
	}

	private void startGame()
	{
		gameController.setState(gameController.gameProcessState);
	}

}
