
package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.Command;

/**
 * Представляет игровое состояние.
 *
 * @author SkinnyMan
 */
abstract class GameProcessorState
{
	protected GameProcessor gameProcessor;

	/**
	 * Инициализирует новый экземпляр класса GameProcessorState для указанного GameProcessor'а.
	 *
	 * @param gameProcessor
	 */
	GameProcessorState(GameProcessor gameProcessor)
	{
		this.gameProcessor = gameProcessor;
	}

	/**
	 * Выполняет обработку указанной команды.
	 *
	 * @param command
	 */
	void processCommand(Command command)
	{

	}

	/**
	 * Посылает команду.
	 *
	 * @param command команда
	 */
	protected void sendCommand(Command command)
	{
		gameProcessor.sendCommand(command);
	}

}
