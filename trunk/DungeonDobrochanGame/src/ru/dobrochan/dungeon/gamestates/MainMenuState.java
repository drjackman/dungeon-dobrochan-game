package ru.dobrochan.dungeon.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.DungeonDobrochanGame;
import ru.dobrochan.dungeon.Settings;
import ru.dobrochan.dungeon.content.ContentManager;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.ui.ActionHandler;
import ru.dobrochan.dungeon.ui.MainMenuButton;

/**
 *
 * @author SkinnyMan
 */
public class MainMenuState extends BasicGameState
{
	Image background;
	Image windowBorder;
	Image logo;
	MainMenuButton button;

	DungeonDobrochanGame game;

	private int id;

	public MainMenuState(int id)
	{
		this.id = id;
	}

	@Override
	public int getID()
	{
		return id;
	}

	@Override
	public void init(GameContainer container, final StateBasedGame game) throws SlickException
	{
		this.game = (DungeonDobrochanGame)game;

		background = ContentManager.getInstance().getImage(ContentPaths.INTERFACE, "BackBaseColor");
		windowBorder = ContentManager.getInstance().getImage(ContentPaths.INTERFACE, "WindowBorder");
		logo = ContentManager.getInstance().getImage(ContentPaths.MAIN_MENU, "Logo");
		logo.setCenterOfRotation(logo.getWidth(), 0);


		button = new MainMenuButton(container, "Start Game");
		button.setActionHandler(new ActionHandler()
		{
			@Override
			public void run()
			{
				game.enterState(DungeonDobrochanGame.GAME_STATE);
			}
		});
		int bx = (Settings.getGameWidth()-button.getWidth())/2;
		int by = 400;
		button.setLocation(bx,by);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw(0, 0);
		logo.drawCentered(Settings.getGameWidth() / 2, 150);

		button.render(container, g);

		windowBorder.draw(0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

	}

}
