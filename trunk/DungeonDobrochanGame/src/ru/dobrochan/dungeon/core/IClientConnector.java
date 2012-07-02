
package ru.dobrochan.dungeon.core;

/**
 * Представляет адаптер клиента.
 *
 * @author SkinnyMan
 */
public interface IClientConnector
{
	/**
	 * Устанавливает клиент.
	 *
	 * @param client клиент
	 */
	void SetClient(ICommandProcessor client);

	/**
	 * Отправляет команду от клиента к серверу.
	 *
	 * @param command команда
	 */
	void SendCommandToServer(Command command);
}
