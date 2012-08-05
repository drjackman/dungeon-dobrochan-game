
package ru.dobrochan.dungeon.core.controller;

import ru.dobrochan.dungeon.core.*;
import ru.dobrochan.dungeon.core.gameview.CellClickEvent;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import ru.dobrochan.dungeon.core.gameview.IViewEventListener;

/**
 * Represents the Controller.
 *
 * @author SkinnyMan
 */
public final class GameController implements IViewEventListener, ICommandProcessor
{
	private GameControllerState state;

	IdleState idleState;
	PlayingState gameProcessState;
	// …and some other states.

	private IClientConnector connector;
	private GameFieldView view;
	private IEntityContainer entities;

	/**
	 * Initialize a new instance of GameController class.
	 */
	public GameController()
	{
		idleState = new IdleState(this);
		gameProcessState = new PlayingState(this);

		state = idleState;
	}

	/**
	 * Sets the current GameFieldView.
	 *
	 * @param gameFieldView current GameFieldView
	 */
	public void setGameFieldView(GameFieldView gameFieldView)
	{
		gameFieldView.setEventListener(this);
		view = gameFieldView;
	}

	/**
	 * Sets the current Connector.
	 *
	 * @param connector
	 */
	public void setConnector(IClientConnector connector)
	{
		connector.SetClient(this);
		this.connector = connector;
	}

	/**
	 * Sets the current EntityContainer
	 *
	 * @param entityContainer
	 */
	public void setEntityContainer(IEntityContainer entityContainer)
	{
		this.entities = entityContainer;
	}

	IEntityContainer getEntityContainer() { return entities; }

	GameFieldView getGameFieldView() { return view; }

	/**
	 * Sends the specified command to the server.
	 *
	 * @param command
	 */
	void sendCommand(Command command)
	{
		connector.SendCommandToServer(command);
	}

	void setState(GameControllerState state)
	{
		this.state = state;
	}

	@Override
	public void cellClicked(CellClickEvent clickEvent)
	{
		state.cellClicked(clickEvent);
	}

	/**
	 * Handles the specified command.
	 *
	 * @param command command
	 */
	@Override
	public void processCommand(Command command)
	{
		// Delegates handle to the current state.
		state.processCommand(command);
	}

}
