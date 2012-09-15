
package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.Cell;
import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.processor.clientcommanddata.ClientCommandList;
import ru.dobrochan.dungeon.core.processor.servercommanddata.MoveEntityData;
import ru.dobrochan.dungeon.core.processor.servercommanddata.ServerCommandList;
import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 *
 * @author SkinnyMan
 */
class PlayingState extends GameProcessorState
{
	IEntity currnetEntity;

	PlayingState(GameProcessor gameProcessor)
	{
		super(gameProcessor);
	}

	@Override
	void processCommand(Command command)
	{
		int id = command.getID();
		switch (id)
		{
			case ClientCommandList.ENTITY_MOVE_REQUEST:
				assert (command.getData() instanceof Cell);
				moveRequest((Cell)command.getData());
				break;
			default:
				System.out.print("Invalid command.");
		}

	}

	private void moveRequest(Cell target)
	{
		int id = (Integer)currnetEntity.getParam(U_ID);
		// Must be real pathfinding algorithm.
		Cell[] path = new Cell[2];
		int x = (Integer)currnetEntity.getParam(U_X);
		int y = (Integer)currnetEntity.getParam(U_Y);
		Cell source = new Cell(x, y);
		path[0] = source;
		path[1] = target;
		MoveEntityData data = new MoveEntityData(id, path);
		Command command = new Command(ServerCommandList.MOVE_ENTITY, data);

		currnetEntity.setParam(U_X, target.X);
		currnetEntity.setParam(U_Y, target.Y);

		sendCommand(command);
	}

	private void checkEndOfBattle()
	{

	}

	private void onStartRound()
	{

	}

	private void onEndRound()
	{

	}

}
