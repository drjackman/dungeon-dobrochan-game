
package ru.dobrochan.dungeon.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
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

/**
 *
 * @author SkinnyMan
 */
public class GameProcessState extends BasicGameState
{
	private int id;
	private Image background;
	private Image windowBorder;

	public GameProcessState(int id) { this.id = id; }

	@Override
	public int getID() { return id; }

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
		gameFieldView = new GameFieldView(container);
		entitiesContainer = new EntityContainer();

		// Link game component to each other.
		gameProcessor.addConnector(connector);
		gameController.setConnector(connector);
		gameFieldView.setEntityContainer(entitiesContainer);
		gameController.setEntityContainer(entitiesContainer);
		gameController.setGameFieldView(gameFieldView);

		// Build background for gameFieldView.
		gameField = surfaceTest();	// test
		gameFieldView.setGameField(gameField);

		// Notify GameProcessor(Model) about the beginning of the game.
		connector.SendCommandToServer(new Command(ClientCommandList.START_GAME));
	}



	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = ResourceManager.getInstance().getImage("WINDOW_BACKGROUND");
		windowBorder = ResourceManager.getInstance().getImage("WINDOW_BORDER");

		// test
//
//		IEntity entity = new Entity();
//		entity.setParam(U_X, 2);
//		entity.setParam(U_Y, 4);
//		entity.setParam(U_SIZE, 2);
//		entity.setParam(U_WIDTH, 2);
//		entity.setParam(U_HEIGHT, 2);
//
//
//		EntityMultiRenderObject entityRObj = new EntityMultiRenderObject();
//		entityRObj.setOwner(entity);
//
//		//SubstrateRenderObject subRObj = new SubstrateRenderObject(Color.blue);
//		BlinkingSubstrateRenderObject blinkRObj = new BlinkingSubstrateRenderObject(new Color(0, 0, 1.0f, 1.2f),
//				new Color(0, 0, 1.0f, 0.3f));
//
//		//SimpleRenderObject sro1 = new SimpleRenderObject();
//		SimpleRenderObject sro2 = new SimpleRenderObject();
//		sro2.image = ResourceManager.getInstance().loadImage("Vampire60", ContentPaths.CREATURES + "Vampire60.png");
//		entityRObj.addRenderObject(blinkRObj);
//		entityRObj.addRenderObject(sro2);
//
//		InflictDamageRenderObject damage = new InflictDamageRenderObject(666);
//		damage.setGameFieldView(gameFieldView);
//		damage.setOwner(entity);
//
//		ClientEntity clientEntity = new ClientEntity(entity);
//		clientEntity.setRenderObject(entityRObj);
//
//		gameFieldView.setGameField(gameField);
//		gameFieldView.rebuildBackground();
//		gameFieldView.setLocation(50, 100);
//		gameFieldView.addRenderObject(entityRObj);
//		gameFieldView.addRenderObject(damage);
	}

	private GameField surfaceTest()
	{
		GameFieldBuilder gfb = new GameFieldBuilder();
		gfb.setRect(5, 6, 4, 4, SURF_ROCK);
		gfb.setRect(8, 9, 3, 3, SURF_ROCK);

		gfb.setRect(23, 3, 2, 8, SURF_ROCK);
		gfb.setRect(19, 7, 8, 2, SURF_ROCK);

		//gfb.setCell(7, 3, SURF_ROCK);
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

		//gfb.setRect(0, 0, 35, 16, SURF_WATER);

		return gfb.buildGameField();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.drawImage(background, 0, 0);

		gameFieldView.render(container, g);

		g.drawImage(windowBorder, 0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		gameFieldView.update(delta);
	}

}
