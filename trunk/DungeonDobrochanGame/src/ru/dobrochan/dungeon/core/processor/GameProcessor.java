
package ru.dobrochan.dungeon.core.processor;

import java.util.ArrayList;
import java.util.List;
import ru.dobrochan.dungeon.core.*;
import static ru.dobrochan.dungeon.core.CommandList.*;

/**
 * Ядро для реализации игровой логики.
 *
 * @author SkinnyMan
 */
public class GameProcessor implements ICommandProcessor
{
	// Паттерн Состояние.
	private GameProcessorState state;

	private GameField gameField;

	private IEntityContainer entities;

	// Может быть 1-2 слушателя (в общем случае произвольное кол-во), поэтому делаем так…
	// Сорт оф Наблюдатель.
	private List<IServerConnector> connectors;

	/**
	 * Инициализирует новый экземпляр класса GameProcessor.
	 */
	public GameProcessor()
	{
		entities = new ServerEntityContainer();
		connectors = new ArrayList<>();
	}

	/**
	 * Добавляет адаптер.
	 *
	 * @param connector
	 */
	public void addConnector(IServerConnector connector)
	{
		connectors.add(connector);
	}

	/**
	 * Удаляет адаптер.
	 *
	 * @param connector
	 */
	public void removeConnector(IServerConnector connector)
	{
		connectors.remove(connector);
	}

	/**
	 * Отправляет команду всем адаптерам.
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
	 * Устанавливает игровое состояние.
	 *
	 * @param state
	 */
	void setState(GameProcessorState state)
	{
		// Конкретные команды обрабатываются в состояниях.
		this.state = state;
	}

	/**
	 * Вызывает обработку команды.
	 *
	 * @param command команда
	 */
	@Override
	public void processCommand(Command command)
	{
		state.processCommand(command);
	}

	private void loadUnitTypes()
	{

	}

	private void recalcParams(IEntity entity)
	{

	}

	private void validateEntity(IEntity entity)
	{

	}
}
