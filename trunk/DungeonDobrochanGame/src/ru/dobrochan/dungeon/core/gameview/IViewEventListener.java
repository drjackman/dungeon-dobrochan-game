
package ru.dobrochan.dungeon.core.gameview;

/**
 * Represents the listener interface for receiving action events from Game View.
 *
 * @author SkinnyMan
 */
public interface IViewEventListener
{
	/**
	 * Notification that the cell was clicked.
	 *
	 * @param clickEvent provides data for this event
	 */
	void cellClicked(CellClickEvent clickEvent);

	// Many other events...
}
