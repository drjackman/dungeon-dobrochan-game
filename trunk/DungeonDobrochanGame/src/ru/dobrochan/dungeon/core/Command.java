package ru.dobrochan.dungeon.core;

/**
 * Represent the command to processing.
 *
 * @author SkinnyMan
 */
public class Command
{
	private int id;
	private Object data;

	/**
	 * Initialize a new instance of Command class without a data.
	 *
	 * @param id the command's identifier
	 */
	public Command(int id)
	{
		this.id = id;
	}

	/**
	 * Initialize a new instance of Command class with the specified data.
	 *
	 * @param id the command's identifier
	 * @param data the data
	 */
	public Command(int id, Object data)
	{
		this.id = id;
		this.data = data;
	}

	/**
	 * Returns the identifier of this command.
	 *
	 * @return identifier
	 */
	public int getID()
	{
		return id;
	}

	/**
	 * Returns the data associated with this command.
	 *
	 * @return data
	 */
	public Object getData()
	{
		return data;
	}

}
