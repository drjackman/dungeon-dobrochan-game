
package ru.dobrochan.dungeon.gamestates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import static ru.dobrochan.dungeon.consts.Surface.*;
import static ru.dobrochan.dungeon.consts.UnitParams.*;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.*;
import ru.dobrochan.dungeon.core.renderobjects.BlinkingSubstrateRenderObject;
import ru.dobrochan.dungeon.core.renderobjects.EntityMultiRenderObject;
import ru.dobrochan.dungeon.core.renderobjects.InflictDamageRenderObject;
import ru.dobrochan.dungeon.core.renderobjects.SimpleRenderObject;

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
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = ResourceManager.getInstance().getImage("WINDOW_BACKGROUND");
		windowBorder = ResourceManager.getInstance().getImage("WINDOW_BORDER");

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

		gameField = gfb.buildGameField();

		gameFieldView = new GameFieldView(container);

		IEntity entity = new Entity();
		entity.setParam(U_X, 2);
		entity.setParam(U_Y, 4);
		entity.setParam(U_SIZE, 2);
		entity.setParam(U_WIDTH, 2);
		entity.setParam(U_HEIGHT, 2);

		EntityMultiRenderObject entityRObj = new EntityMultiRenderObject();
		entityRObj.setOwner(entity);

		//SubstrateRenderObject subRObj = new SubstrateRenderObject(Color.blue);
		BlinkingSubstrateRenderObject blinkRObj = new BlinkingSubstrateRenderObject(new Color(0, 0, 1.0f, 1.2f),
				new Color(0, 0, 1.0f, 0.3f));

		//SimpleRenderObject sro1 = new SimpleRenderObject();
		SimpleRenderObject sro2 = new SimpleRenderObject();
		sro2.image = ResourceManager.getInstance().loadImage("Vampire60", ContentPaths.CREATURES + "Vampire60.png");
		entityRObj.addRenderObject(blinkRObj);
		entityRObj.addRenderObject(sro2);

		InflictDamageRenderObject damage = new InflictDamageRenderObject(666);
		damage.setGameFieldView(gameFieldView);
		damage.setOwner(entity);

		ClientEntity clientEntity = new ClientEntity(entity);
		clientEntity.setRenderObject(entityRObj);

		entitiesContainer = new EntitiesContainer();
		gameFieldView.setEntitysContainer(entitiesContainer);
		entitiesContainer.addEntity(clientEntity);

		gameFieldView.setGameField(gameField);
		gameFieldView.rebuildBackground();
		gameFieldView.setLocation(50, 100);
		gameFieldView.addRenderObject(entityRObj);
		gameFieldView.addRenderObject(damage);
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
