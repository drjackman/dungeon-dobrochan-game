
package ru.dobrochan.dungeon.core.processor.servercommanddata;

/**
 * Contains the list of server-to-client command's IDs.
 * Содержит список команд.
 *
 * @author SkinnyMan
 */
public class ServerCommandList
{
	public static final int START_GAME = 0;

	public static final int ADD_ENTITY = 1;
	public static final int MOVE_ENTITY = 2;
	public static final int SET_CURRENT_ENTITY = 3;

	private ServerCommandList()
	{ }
}
