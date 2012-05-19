package ru.dobrochan.dungeon;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.gamestates.GamePoolState;
import ru.dobrochan.dungeon.gamestates.GameProcessState;
import ru.dobrochan.dungeon.gamestates.MainMenuState;

/**
 *
 * @author SkinnyMan
 */
public class DungeonDobrochanGame extends StateBasedGame
{
	public static final int MAIN_MENU_STATE = 0;
	public static final int GAME_POOL_STATE = 1;
	public static final int GAME_STATE = 2;

	public DungeonDobrochanGame()
	{
		super("Dungeon");
	}

    @Override
    public void initStatesList(GameContainer container)
			throws SlickException
	{
		MainMenuState menu = new MainMenuState(MAIN_MENU_STATE);
		GameProcessState game = new GameProcessState(GAME_STATE);
		GamePoolState pool = new GamePoolState(GAME_POOL_STATE);
		addState(menu);
		addState(game);
		addState(pool);
		enterState(MAIN_MENU_STATE);
    }

	public static void main(String[] args)
    {
		try
		{
			AppGameContainer app = new AppGameContainer(new DungeonDobrochanGame());
			app.setDisplayMode(Settings.getGameWidth(), Settings.getGameHeight(), false);
			app.start();

		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
    }
}
