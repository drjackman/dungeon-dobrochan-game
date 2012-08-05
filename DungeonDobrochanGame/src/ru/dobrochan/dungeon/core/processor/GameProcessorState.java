
package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.Command;

/**
 * Represents the GameProcessor's state.
 *
 * @author SkinnyMan
 */
abstract class GameProcessorState
{
	protected GameProcessor gameProcessor;

	/**
	 * Initialize a new instance of GameProcessorState class for specified GameProcessor.
	 *
	 * @param gameProcessor
	 */
	GameProcessorState(GameProcessor gameProcessor)
	{
		this.gameProcessor = gameProcessor;
	}

	/**
	 * Handles the specified command.
	 *
	 * @param command
	 */
	abstract void processCommand(Command command);

	/**
	 * Sends the specified command to client.
	 *
	 * @param command команда
	 */
	protected final void sendCommand(Command command)
	{
		gameProcessor.sendCommand(command);
	}

}
