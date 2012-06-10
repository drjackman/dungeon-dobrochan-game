package ru.dobrochan.dungeon.gamestates;

import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.DungeonDobrochanGame;
import ru.dobrochan.dungeon.Settings;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.ActionHandler;
import ru.dobrochan.dungeon.ui.MainMenuButton;
import ru.dobrochan.dungeon.ui.MyTextField;

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

	MyTextField textField;

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

		background = ResourceManager.getInstance().getImage("WINDOW_BACKGROUND");
		windowBorder = ResourceManager.getInstance().getImage("WINDOW_BORDER");
		logo = ResourceManager.getInstance().getImage("WINDOW_LOGO");
		logo.setCenterOfRotation(logo.getWidth(), 0);
		java.awt.Font jFont = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);

		String fontPath = "fonts"+ File.separator +"GABRIOLA.ttf";
		UnicodeFont uFont = new UnicodeFont(fontPath , 20, false, false); //Create Instance
		uFont.addAsciiGlyphs();   //Add Glyphs
		//uFont.addGlyphs(0, 65535); //Add Glyphs
		//uFont.addGlyphs("Привет");

		uFont.addGlyphs(0x400, 0x4ff); //Add rus Glyphs
		uFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE)); //Add Effects
		uFont.loadGlyphs();  //Load Glyphs

		textField = new MyTextField(container, uFont, 100, 50, 300, 200);
		textField.setBackgroundColor(new Color(0, 0, 0, 0));
		textField.setBorderColor(new Color(0, 0, 0, 0));
		textField.setText("\n\rHello\n\rпривет\n\r");

		String sStr = "ы";
		byte[] bytes = sStr.getBytes();
		char c = (char)bytes[0];

		button = new MainMenuButton(container, "Start Game");
		button.setActionHandler(new ActionHandler()
		{
			@Override
			public void run()
			{
				game.enterState(DungeonDobrochanGame.GAME_STATE);
			}
		});
		int bx = (Settings.SCREEN_WIDTH - button.getWidth())/2;
		int by = 400;
		button.setLocation(bx,by);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{

		background.draw(0, 0);
		logo.drawCentered(Settings.SCREEN_WIDTH / 2, 150);

		button.render(container, g);

		textField.render(container, g);

		windowBorder.draw(0, 0);
	}

	private int counter = 0;

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		counter++;
		if (counter >= 1000)
		{
			System.out.println(counter);
			counter = 0;
		}
	}
}
