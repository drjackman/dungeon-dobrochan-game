
package ru.dobrochan.dungeon.core;

/**
 * Представляет адаптер игрового сервера.
 *
 * @author SkinnyMan
 */
public interface IServerConnector
{
	/**
	 * Устанавливает игровой сервер.
	 *
	 * @param server игровой сервер
	 */
	void SetServer(ICommandProcessor server);

	/**
	 * Отправляет команду от сервера к клиенту.
	 *
	 * @param command команда
	 */
	void SendCommandToClient(Command command);
}
