package ru.dobrochan.dungeon.ui.events;

public class EventArgs 
{
	EventArgs()
	{
	}
	
	static EventArgs empty = new EventArgs();
	
	static EventArgs getEmpty() { return empty; }
}
