package ru.dobrochan.dungeon.core.processor;

import ru.dobrochan.dungeon.core.Command;
import ru.dobrochan.dungeon.core.IClientConnector;
import ru.dobrochan.dungeon.core.ICommandProcessor;
import ru.dobrochan.dungeon.core.IServerConnector;

/**
 * Адаптер, напрямую соединяющий клиент и сервер.
 *
 * @author SkinnyMan
 */
public class SimpleConnector implements IClientConnector, IServerConnector
{
	private ICommandProcessor client;
	private ICommandProcessor server;

	/**
	 * @see IClientConnector#SetClient(ru.dobrochan.dungeon.core.ICommandProcessor)
	 */
	@Override
	public void SetClient(ICommandProcessor client)
	{
		this.client = client;
	}

	/**
	 * @see IClientConnector#SendCommandToServer(ru.dobrochan.dungeon.core.Command)
	 */
	@Override
	public void SendCommandToServer(Command command)
	{
		if (server != null)
			server.processCommand(command);
	}

	/**
	 * @see IServerConnector#SetServer(ru.dobrochan.dungeon.core.ICommandProcessor)
	 */
	@Override
	public void SetServer(ICommandProcessor server)
	{
		this.server = server;
	}

	/**
	 * @see IServerConnector#SendCommandToClient(ru.dobrochan.dungeon.core.Command)
	 */
	@Override
	public void SendCommandToClient(Command command)
	{
		if (client != null)
			client.processCommand(command);
	}

}
