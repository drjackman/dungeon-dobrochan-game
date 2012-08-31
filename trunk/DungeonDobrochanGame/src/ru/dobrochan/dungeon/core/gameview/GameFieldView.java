
package ru.dobrochan.dungeon.core.gameview;

import ru.dobrochan.dungeon.core.gameview.renderobjects.CellBacklightRenderObject;
import ru.dobrochan.dungeon.core.gameview.renderobjects.EntityMultiRenderObject;
import ru.dobrochan.dungeon.core.gameview.renderobjects.SimpleRenderObject;
import ru.dobrochan.dungeon.core.gameview.renderobjects.BlinkingSubstrateRenderObject;
import ru.dobrochan.dungeon.core.gameview.renderobjects.EntityRenderObject;
import ru.dobrochan.dungeon.core.gameview.renderobjects.RenderObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import ru.dobrochan.dungeon.Settings;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.Cell;
import ru.dobrochan.dungeon.core.EntitiesHelper;
import ru.dobrochan.dungeon.core.GameField;
import ru.dobrochan.dungeon.core.IEntity;
import ru.dobrochan.dungeon.core.IEntityContainer;
import ru.dobrochan.dungeon.core.gameview.renderobjects.*;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.events.MouseClickedEventArgs;
import ru.dobrochan.dungeon.ui.events.MouseMovedAction;
import ru.dobrochan.dungeon.ui.events.MouseMovedEventArgs;
import ru.dobrochan.dungeon.ui.primitives.Point;

/**
 * Represents the View of game field.
 *
 * @author SkinnyMan
 */
public class GameFieldView extends AbstractControl
{
	// Just for test.
	private class CursorBacklight
	{
		private CellBacklightRenderObject rObj;
		GameFieldView field;

		public CursorBacklight(GameFieldView field)
		{
			this.field = field;
			rObj = new CellBacklightRenderObject();
			field.addRenderObject(rObj);
		}

		public void update(Cell cell)
		{
			rObj.setCell(cell);
		}
	}

	// Is one Observer are enough?
	private IViewEventListener eventListener;

	public IViewEventListener getEventListener()
	{
		return eventListener;
	}

	public void setEventListener(IViewEventListener eventListener)
	{
		this.eventListener = eventListener;
	}

	private static final int gridOffsetX = 98;
	private static final int gridOffsetY = 153;

	private int coordX;
	private int coordY;

	private int width;
	private int height;

	private int widthInCells = Settings.FIELD_WIDTH;
	private int heightInCells = Settings.FIELD_HEIGHT;

	private int cellWidth;
	private int cellHeight;
	private int cellCountX = 35;
	private int cellCountY = 16;

	private GameField gameField;
	private IEntityContainer entitysContainer;

	private ArrayList<RenderObject> renderObjects;

	private CursorBacklight cursorBacklight;
	
	private FieldBackgroundRenderer backgroundRenderer;

	public GameFieldView(GUIContext context)
	{
		super(context);
		
		try
		{
			Cursor cursor = ResourceManager.getInstance().getCursor("CURSOR_MAIN");
			try
			{
				Mouse.setNativeCursor(cursor);
			}
			catch (LWJGLException ex)
			{
				Logger.getLogger(GameFieldView.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		catch (Exception ex)
		{
			Logger.getLogger(GameFieldView.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		width = 1085;
		height = 496;
		cellWidth = 31;
		cellHeight = 31;
		renderObjects = new ArrayList<RenderObject>();
		cursorBacklight = new CursorBacklight(this);
		backgroundRenderer = new FieldBackgroundRenderer();
		
		onMouseMovedAdd(new MouseMovedAction(){
			@Override
			public void execute(AbstractControl sender, MouseMovedEventArgs e) {
	            Cell c = getCellByPoint(e.getNewLocation());
	            if (c != null)
	            {
		            cursorBacklight.update(c);
	            }
			}
		});
		
		onMouseClickedAdd(new MouseClickedAction(){
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {
	            Cell c = getCellByPoint(e.getLocation());
	            if (c != null && eventListener != null)
	            	eventListener.cellClicked(new CellClickEvent(c));}
		});
	}

	@Override
	public void setLocation(int x, int y)
	{
		coordX = gridOffsetX;
		coordY = gridOffsetY;
	}

	@Override
	public int getX() { return coordX; }

	@Override
	public int getY() { return coordY; }

	@Override
	public int getWidth() { return width; }

	@Override
	public int getHeight() { return height; }

	public int getCellWidth() { return cellWidth; }

	public int getCellHeight() { return cellHeight; }

	public void addUnitEntity(IEntity entity)
	{
		try
		{
			EntityMultiRenderObject entityRObj = new EntityMultiRenderObject();
			entityRObj.setOwner(entity);
			BlinkingSubstrateRenderObject blinkRObj = new BlinkingSubstrateRenderObject(
					new Color(0, 0, 1.0f, 1.2f), new Color(0, 0, 1.0f, 0.3f));
			SimpleRenderObject sro = new SimpleRenderObject();
			sro.image = ResourceManager.getInstance().loadImage(
				(String)entity.getParam("image"), ContentPaths.CREATURES + (String)entity.getParam("image") + ".png");
			entityRObj.addRenderObject(blinkRObj);
			entityRObj.addRenderObject(sro);
			addRenderObject(entityRObj);
		}
		catch (SlickException ex)
		{
			Logger.getLogger(GameFieldView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void moveUnitEntity(IEntity entity, Cell[] path)
	{
		for (RenderObject rObj : renderObjects)
		{
			if (rObj instanceof EntityRenderObject)
			{
				IEntity owner = ((EntityRenderObject)rObj).getOwner();
				if (owner == entity)
				{
					removeRenderObject(rObj);
					MovingEntityRenderObject mrObj = new MovingEntityRenderObject(owner, path);
					addRenderObject(mrObj);
					break;
				}
			}
		}
	}

	/**
	 * Добавляет RenderObject в процесс отрисовки.
	 *
	 * @param renderObject
	 */
	public void addRenderObject(RenderObject renderObject)
	{
		renderObject.setGameFieldView(this);
		renderObject.stateChanged();
		renderObjects.add(renderObject);
		Collections.sort(renderObjects, new RenderObjectComparator());
	}

	private class RenderObjectComparator implements Comparator<RenderObject>
	{
		@Override
		public int compare(RenderObject t, RenderObject t1)
		{
			return t.getLayer() - t1.getLayer();
		}
	}

	/**
	 * Удаляет RenderObject из процесса отрисовки.
	 *
	 * @param renderObject
	 */
	public void removeRenderObject(RenderObject renderObject)
	{
		renderObjects.remove(renderObject);
	}

	/**
	 * Вызывает обновление RenderObject'ов игрового поля.
	 *
	 * @param delta
	 */
	public void update(int delta)
	{
		for (int i = 0; i < renderObjects.size(); i++)
		{
			renderObjects.get(i).update(delta);
		}
//		for (RenderObject rObj : renderObjects)
//		{
//			rObj.update(delta);
//		}
	}

	/**
	 * Вызывает перерисовку игрового поля.
	 *
	 * @param container
	 * @param g
	 * @throws SlickException
	 */
	@Override
	public void render(GUIContext container, Graphics g) throws SlickException
	{
		backgroundRenderer.drawBackground(g);
		for (RenderObject rObj : renderObjects)
		{
			rObj.render(g);
		}
	}

	public Cell getCellByPoint(Point pt)
	{
		int cx = (pt.getX() - coordX)/cellWidth;
		int cy = (pt.getY()  - coordY)/cellHeight;
		if (cx < 0 || cx >= cellCountX || cy < 0 || cy >= cellCountY)
			return null;
		return new Cell(cx, cy);
	}

	public IEntityContainer getEntityContainer() { return entitysContainer; }

	public void setEntityContainer(IEntityContainer entitysContainer) 
	{
		this.entitysContainer = entitysContainer; 
	}

	public GameField getGameField() { return gameField; }

	public void setGameField(GameField gameField) 
	{ 
		this.gameField = gameField;
		backgroundRenderer.setGameField(gameField);
	}
}
