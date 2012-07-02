
package ru.dobrochan.dungeon.core;

/**
 * Представляет обработчик команд.
 *
 * @author SkinnyMan
 */
public interface ICommandProcessor
{
	/**
	 * Отправляет команду на обработку.
	 *
	 * @param command обрабатываемая команда
	 */
	void processCommand(Command command);
}
