
package ru.dobrochan.dungeon.gamestates;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.ui.IComponent;

/**
 *
 * @author SkinnyMan
 */
public abstract class GameState extends BasicGameState
{
	private int id;
	protected ArrayList<IComponent> components;

	protected GameState(int id)
	{
		this.id = id;
		components = new ArrayList<IComponent>();
	}

	@Override
	public final int getID()
	{
		return id;
	}

	public void addComponent(IComponent component)
	{

	}

	public void removeComponent(IComponent component)
	{

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
