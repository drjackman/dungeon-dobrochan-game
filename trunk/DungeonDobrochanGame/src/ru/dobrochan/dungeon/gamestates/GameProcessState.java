
package ru.dobrochan.dungeon.gamestates;

import java.io.File;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import static ru.dobrochan.dungeon.consts.Surface.SURF_ROCK;
import static ru.dobrochan.dungeon.consts.Surface.SURF_WATER;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.*;
import ru.dobrochan.dungeon.core.controller.GameController;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import ru.dobrochan.dungeon.core.processor.GameProcessor;
import ru.dobrochan.dungeon.core.processor.SimpleConnector;
import ru.dobrochan.dungeon.core.processor.clientcommanddata.ClientCommandList;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.controls.combined.CreaturesBar;
import ru.dobrochan.dungeon.ui.controls.combined.LogControl;
import ru.dobrochan.dungeon.ui.controls.combined.RootControl;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.events.MouseClickedEventArgs;
import ru.dobrochan.dungeon.ui.primitives.Size;

/**
 *
 * @author SkinnyMan
 */
public class GameProcessState extends GameState
{

	public GameProcessState(int id) { super(id); }

	private GameField gameField;
	private GameFieldView gameFieldView;

	private IEntityContainer entitiesContainer;

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.enter(container, game);
		
		// Create main game components.
		SimpleConnector connector = new SimpleConnector();
		GameProcessor gameProcessor = new GameProcessor();
		GameController gameController = new GameController();
		entitiesContainer = new EntityContainer();
		
		// Link game component to each other.
		gameProcessor.addConnector(connector);
		gameController.setConnector(connector);
		gameController.setEntityContainer(entitiesContainer);
		gameController.setGameFieldView(gameFieldView);
		gameFieldView.setEntityContainer(entitiesContainer);

		// Notify GameProcessor(Model) about the beginning of the game.
		connector.SendCommandToServer(new Command(ClientCommandList.START_GAME));
	}
	
	LogControl logControl;
	Picture btnSettings, btnSave, btnLoad, popupAbility, popupMagic, popupWait;
	CreaturesBar creaturesBar;
	
	@Override
	protected RootControl buildRootControl(GUIContext context) throws SlickException 
	{
		RootControl rootControl = super.buildRootControl(context);	
		
		Font font = new UnicodeFont("fonts"+ File.separator +"GABRIOLA.ttf", 12, false, false);
		
		gameField = surfaceTest();	// test
		gameFieldView = new GameFieldView(context);
		gameFieldView.setGameField(gameField);			

		logControl = new LogControl(context, font);
		
		btnSettings = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_SETTINGS"));
		btnSave = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_SAVE"));
		btnLoad = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_LOAD"));
		
		popupAbility = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_ABILITY"));
		popupMagic = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_MAGIC"));
		popupWait = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_WAIT"));

		creaturesBar = new CreaturesBar(context, font);
		
		rootControl.addChild(gameFieldView, new Size(10, 10));
		rootControl.addChild(logControl, new Size(0, 15));
		
		int SCREEN_WIDTH = 1280;
		int barX = (SCREEN_WIDTH - creaturesBar.getWidth()) / 2;
		rootControl.addChild(creaturesBar, new Size(barX, getDockedControlYOffset(creaturesBar) + 7));
		
		rootControl.addChild(btnSettings, new Size(100, 
				getDockedControlYOffset(btnSettings)));
		rootControl.addChild(btnSave, new Size(100 + (btnSettings.getWidth() + 10), 
				getDockedControlYOffset(btnSave)));
		rootControl.addChild(btnLoad, new Size(100 + (btnSettings.getWidth() + 10) + (btnSave.getWidth() + 10),  
				getDockedControlYOffset(btnLoad)));
		
		rootControl.addChild(popupAbility, new Size(970, getDockedControlYOffset(popupAbility)));
		rootControl.addChild(popupMagic, new Size(1070, getDockedControlYOffset(popupMagic)));
		rootControl.addChild(popupWait, new Size(1150, getDockedControlYOffset(popupWait)));
		
		controlsTest();
		
		return rootControl;
	}
	
	private void controlsTest() throws SlickException{		
		logControl.addMessage("1ssssss");
		logControl.addMessage("2ssssss");
		logControl.addMessage("3ssssss");
		logControl.addMessage("4ssssss");
		logControl.addMessage("5ssssss");
		logControl.addMessage("6ssssss");
		logControl.addMessage("7ssssss");
		
		creaturesBar.setLabelText("10/15");
		
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));	
	}
	
	private int getDockedControlYOffset(AbstractControl control){
		int SCREEN_HEIGHT = 800;		
		int BORDER_WIDTH = 7;
		return SCREEN_HEIGHT - control.getHeight() - BORDER_WIDTH;
	}
	
	private Image buildMenuButtonPicture(String iconName) throws SlickException{
		Image background = ResourceManager.getInstance().getImage("BATTLE_SCREEN_SHORT_MENU").copy();
		Image icon = ResourceManager.getInstance().getImage(iconName);
		int x = (background.getWidth() - icon.getWidth()) / 2;
		int y = (background.getHeight() - icon.getHeight()) / 2 + 5;
		background.getGraphics().drawImage(icon, x, y);
		background.getGraphics().flush();
		return background;
	}
	
	private GameField surfaceTest()
	{
		GameFieldBuilder gfb = new GameFieldBuilder();
		gfb.setRect(5, 6, 4, 4, SURF_ROCK);
		gfb.setRect(8, 9, 3, 3, SURF_ROCK);

		gfb.setRect(23, 3, 2, 8, SURF_ROCK);
		gfb.setRect(19, 7, 8, 2, SURF_ROCK);

		gfb.setCell(7, 3, SURF_ROCK);
		gfb.setRect(7, 3, 2, 2, SURF_ROCK);

		gfb.setRect(15, 12, 5, 2, SURF_ROCK);
		gfb.setRect(16, 11, 4, 1, SURF_ROCK);
		gfb.setRect(17, 10, 3, 1, SURF_ROCK);
		gfb.setRect(18, 9, 2, 1, SURF_ROCK);

		gfb.setRect(0, 0, 35, 2, SURF_ROCK);
		gfb.setRect(0, 0, 2, 16, SURF_ROCK);
		gfb.setRect(30, 0, 5, 16, SURF_ROCK);
		gfb.setRect(0, 14, 35, 2, SURF_ROCK);

		gfb.setRect(10, 5, 4, 3, SURF_WATER);
		gfb.clearCell(13, 7);

		gfb.setRect(17, 5, 6, 4, SURF_WATER);
		gfb.setRect(20, 9, 3, 2, SURF_WATER);

		gfb.clearRect(0, 3, 2, 4);
		gfb.clearRect(30, 10, 5, 3);

		return gfb.buildGameField();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		gameFieldView.update(delta);
	}
}
