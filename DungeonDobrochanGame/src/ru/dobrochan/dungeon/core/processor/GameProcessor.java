
package ru.dobrochan.dungeon.core.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.dobrochan.dungeon.FileHelper;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.*;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import static ru.dobrochan.dungeon.core.processor.servercommanddata.ServerCommandList.*;
import ru.dobrochan.dungeon.core.scripts.ConstsScriptHandler;
import ru.dobrochan.dungeon.core.scripts.UnitScriptHandler;

/**
 * Represents the core of game logic.
 *
 * @author SkinnyMan
 */
public final class GameProcessor implements ICommandProcessor
{
	// State pattern.
	private GameProcessorState state;

	IdleState idleState;
	PlayingState playingState;
	// Many other states...

	EntityFactory entityFactory;

	private GameFieldView view;
	private IEntityContainer entities;

	// Can be any number of listeners so use Observer pattern.
	private List<IServerConnector> connectors;

	IEntityContainer getEntityContainer() { return entities; }

	GameFieldView getGameFieldView() { return view; }

	/**
	 * Initialize a new instance of GameProcessor class.
	 */
	public GameProcessor()
	{
		try
		{
			entities = new EntityContainer();
			connectors = new ArrayList<>();

			idleState = new IdleState(this);
			playingState = new PlayingState(this);

			entityFactory = new EntityFactory();

			// The file open process must be encapsulated.
			String constScriptTxt = FileHelper.ReadFileToEnd(ResourceManager.PATH + ContentPaths.SCRIPTS + "Const.js");
			String unitScriptTxt = FileHelper.ReadFileToEnd(ResourceManager.PATH + ContentPaths.SCRIPTS + "Units.js");

			ConstsScriptHandler.GetInstance().setConsts(constScriptTxt);
			UnitScriptHandler ush = new UnitScriptHandler(unitScriptTxt);
			entityFactory = new EntityFactory();
			entityFactory.addTypes(ush.getUnits());


			setState(idleState);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(GameProcessor.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Adds connector.
	 *
	 * @param connector
	 */
	public void addConnector(IServerConnector connector)
	{
		connector.SetServer(this);
		connectors.add(connector);

	}

	/**
	 * Removes connector.
	 *
	 * @param connector
	 */
	public void removeConnector(IServerConnector connector)
	{
		connectors.remove(connector);
	}

	/**
	 * Sends the specified command to all clients.
	 *
	 * @param command
	 */
	void sendCommand(Command command)
	{
		for (IServerConnector connector : connectors)
		{
			connector.SendCommandToClient(command);
		}
	}

	/**
	 * Sets the current game state.
	 *
	 * @param state
	 */
	void setState(GameProcessorState state)
	{
		this.state = state;
	}

	/**
	 * Handles the specified command.
	 *
	 * @param command команда
	 */
	@Override
	public void processCommand(Command command)
	{
		// Delegates handle to the current state.
		state.processCommand(command);
	}
}
