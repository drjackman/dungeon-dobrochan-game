package ru.dobrochan.dungeon;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.scripts.CalcScriptHandler;
import ru.dobrochan.dungeon.core.scripts.ConstsScriptHandler;
import ru.dobrochan.dungeon.core.scripts.UnitScriptHandler;
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
		InputStream is = null;
		try
		{
			is = new FileInputStream("Resources.xml");
			ResourceManager.getInstance().loadResources(is, true);
			MainMenuState menu = new MainMenuState(MAIN_MENU_STATE);
			GameProcessState game = new GameProcessState(GAME_STATE);
			GamePoolState pool = new GamePoolState(GAME_POOL_STATE);
			addState(menu);
			addState(game);
			addState(pool);
			enterState(MAIN_MENU_STATE);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (IOException ex)
			{
				Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

    }

	public static void main(String[] args)
    {
		try
		{
			Test();
			AppGameContainer app = new AppGameContainer(new DungeonDobrochanGame());
			app.setDisplayMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);
			app.setUpdateOnlyWhenVisible(false);
			app.setAlwaysRender(true);
			app.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
    }

	private static void MusicTest()
	{

	}

	private static void Test()
	{
		Properties pr = System.getProperties();

		FileReader fr = null;
		try
		{
			File file = new File("");
			String absolutePath = file.getAbsolutePath();
			fr = new FileReader(absolutePath + ContentPaths.SCRIPTS + "Const.js");
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			String data = "";
			while (line != null)
			{
				data += line + "\n\r";
				line = br.readLine();
			}

			ConstsScriptHandler.GetInstance().setConsts(data);
			fr = new FileReader(absolutePath + ContentPaths.SCRIPTS + "Units.js");
			UnitScriptHandler unitLoader = new UnitScriptHandler(fr);
			String[] unitNames = unitLoader.loadUnitNames();
			IEntity[] entities = unitLoader.LoadUnits();

			fr = new FileReader(absolutePath + ContentPaths.SCRIPTS + "Calc.js");
			CalcScriptHandler calc = new CalcScriptHandler(fr);

			calc.calc(entities[0]);

			int i = 0;
		}
		catch (IOException ex)
		{
			Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				fr.close();
			}
			catch (IOException ex)
			{
				Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
