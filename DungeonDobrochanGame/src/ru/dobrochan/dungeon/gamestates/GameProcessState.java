
package ru.dobrochan.dungeon.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.content.ContentManager;
import ru.dobrochan.dungeon.content.ContentPaths;

/**
 *
 * @author SkinnyMan
 */
public class GameProcessState extends BasicGameState
{
	private int id;
	private Image background;
	private Image windowBorder;

	private Image gridBorder;
	private Image grid;

	public GameProcessState(int id)
	{
		this.id = id;
	}

	@Override
	public int getID()
	{
		return id;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = ContentManager.getInstance().getImage(ContentPaths.INTERFACE, "BackBaseColor");
		windowBorder = ContentManager.getInstance().getImage(ContentPaths.INTERFACE, "WindowBorder");
		gridBorder = ContentManager.getInstance().getImage(ContentPaths.BACK, "GridBorder");
		grid = ContentManager.getInstance().getImage(ContentPaths.BACK, "Grid001");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.drawImage(background, 0, 0);

		g.drawImage(gridBorder, 0, 0);
		g.drawImage(grid, 98, 152);

		g.drawImage(windowBorder, 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

	}

}
