
package ru.dobrochan.dungeon.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
import static ru.dobrochan.dungeon.consts.Surface.SURF_ROCK;
import static ru.dobrochan.dungeon.consts.Surface.SURF_WATER;
import ru.dobrochan.dungeon.DungeonDobrochanGame;
import ru.dobrochan.dungeon.core.*;
import ru.dobrochan.dungeon.core.controller.GameController;
import ru.dobrochan.dungeon.core.gameview.GameFieldView;
import ru.dobrochan.dungeon.core.processor.GameProcessor;
import ru.dobrochan.dungeon.core.processor.SimpleConnector;
import ru.dobrochan.dungeon.core.processor.clientcommanddata.ClientCommandList;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.combined.MainMenu;
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
	
	@Override
	protected RootControl buildRootControl(GUIContext context) throws SlickException 
	{
		RootControl rootControl = super.buildRootControl(context);	
		gameField = surfaceTest();	// test
		gameFieldView = new GameFieldView(context);
		gameFieldView.setGameField(gameField);
		rootControl.addChild(gameFieldView, new Size(10, 10));	
		return rootControl;
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
