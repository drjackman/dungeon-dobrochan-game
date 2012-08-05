
package ru.dobrochan.dungeon.core.controller;

import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.gameview.IViewEventListener;

/**
 * Represents the GameController's state.
 *
 * @author SkinnyMan
 */
abstract class GameControllerState implements IViewEventListener
{
	protected GameController gameController;

	/**
	 * Initialize a new instance of GameControllerState class for specified GameController.
	 *
	 * @param gameController
	 */
	GameControllerState(GameController gameController)
	{
		this.gameController = gameController;
	}

	/**
	 * Handles the specified command.
	 *
	 * @param command
	 */
	abstract void processCommand(Command command);

	/**
	 * Sends the specified command to server.
	 *
	 * @param command
	 */
	protected final void sendCommand(Command command)
	{
		gameController.sendCommand(command);
	}

}
