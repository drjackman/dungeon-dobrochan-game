
package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.processor.servercommanddata.ServerCommandList;
import static ru.dobrochan.dungeon.consts.UnitParams.*;
import ru.dobrochan.dungeon.core.processor.clientcommanddata.ClientCommandList;

/**
 *
 * @author SkinnyMan
 */
class IdleState extends GameProcessorState
{
	IdleState(GameProcessor gameProcessor)
	{
		super(gameProcessor);
	}

	@Override
	void processCommand(Command command)
	{
		int id = command.getID();
		switch (id)
		{
			case ClientCommandList.START_GAME:
				startGame();
				break;
			default:
				System.out.print("Invalid command.");
				break;
		}
	}

	private void startGame()
	{
		// Only for test.
		IEntity entity = gameProcessor.entityFactory.createEntityByType("Скелет мечник");
		entity.setParam(U_ID, 1);
		entity.setParam(U_X, 3);
		entity.setParam(U_Y, 5);
		gameProcessor.getEntityContainer().addEntity(entity);
		sendCommand(new Command(ServerCommandList.ADD_ENTITY, entity.clone()));
		sendCommand(new Command(ServerCommandList.START_GAME));
		sendCommand(new Command(ServerCommandList.SET_CURRENT_ENTITY, 1));
		gameProcessor.playingState.currnetEntity = entity;

		gameProcessor.setState(gameProcessor.playingState);
	}

}
