
package ru.dobrochan.dungeon.core.gameview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import ru.dobrochan.dungeon.Settings;
import static ru.dobrochan.dungeon.consts.Surface.*;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.core.GameField;

/**
 * Представляет методы экземпляра класса для создания фона и неизменяемых объектов для GameFieldView.
 *
 * @author SkinnyMan
 */
public class FieldBackgroundBuilder
{
	private static final int SINGLE = 0;
	private static final int CENTER = 1;
	private static final int EXT_ANGLE = 2;
	private static final int INT_ANGLE = 3;
	private static final int CROSS_ANGLE = 4;
	private static final int HSIDE = 5;
	private static final int VSIDE = 6;

	private static final int angularDecorOffsetX = 22;
	private static final int angularDecorOffsetY = 22;

	private static final int gridOffsetX = 98;
	private static final int gridOffsetY = 153;

	private int cellWidth = 31;
	private int cellHeight = 31;

	private int gridWidth;
	private int gridHeight;

	private ArrayList<Image> angularDecoration = new ArrayList<Image>();
	private ArrayList<Image> borderDecoration = new ArrayList<Image>();

	private HashMap<Integer, HashMap<Integer, List<Image>>> surfaceMap =
											new HashMap<Integer, HashMap<Integer, List<Image>>>();

	private Image grid;
	private Image gridBorder;

	private GameField gameField;
	private int gfWidth;
	private int gfHeight;

	private Graphics g;

	private Random random = new Random();

	/**
	 * Инициализирует новый экземпляр класса FieldBackgroundBuilder.
	 */
	public FieldBackgroundBuilder()
	{
		ResourceManager rm = ResourceManager.getInstance();

		// Заполнение карт текстур.
		// Скала.
		HashMap<Integer, List<Image>> rockMap = new HashMap<Integer, List<Image>>();
		rockMap.put(SINGLE, rm.getImageList("BATTLEFIELD_ROCK_SINGLE"));
		rockMap.put(CENTER, rm.getImageList("BATTLEFIELD_ROCK_CENTER"));
		rockMap.put(EXT_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_EXT_ANGLE"));
		rockMap.put(INT_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_INT_ANGLE"));
		rockMap.put(CROSS_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_CROSS_ANGLE"));
		rockMap.put(HSIDE, rm.getImageList("BATTLEFIELD_ROCK_HSIDE"));
		rockMap.put(VSIDE, rm.getImageList("BATTLEFIELD_ROCK_VSIDE"));

		// Вода.
		HashMap<Integer, List<Image>> waterMap = new HashMap<Integer, List<Image>>();
		waterMap.put(SINGLE, rm.getImageList("BATTLEFIELD_WATER_SINGLE"));
		waterMap.put(CENTER, rm.getImageList("BATTLEFIELD_WATER_CENTER"));
		waterMap.put(EXT_ANGLE, rm.getImageList("BATTLEFIELD_WATER_EXT_ANGLE"));
		waterMap.put(INT_ANGLE, rm.getImageList("BATTLEFIELD_WATER_INT_ANGLE"));
		waterMap.put(CROSS_ANGLE, rm.getImageList("BATTLEFIELD_WATER_CROSS_ANGLE"));
		waterMap.put(HSIDE, rm.getImageList("BATTLEFIELD_WATER_HSIDE"));
		waterMap.put(VSIDE, rm.getImageList("BATTLEFIELD_WATER_VSIDE"));

		surfaceMap.put(SURF_ROCK, rockMap);
		surfaceMap.put(SURF_WATER, waterMap);

		grid = ResourceManager.getInstance().getImage("BATTLEFIELD_GRID");
		gridBorder = ResourceManager.getInstance().getImage("BATTLEFIELD_BORDER");
		gridWidth = grid.getWidth();
		gridHeight = grid.getHeight();

		// Угловые декорации.
		angularDecoration.addAll(ResourceManager.getInstance().getImageList("BATTLEFIELD_ANGULAR_DECORATION"));

		// Бордюрные декорации.
		borderDecoration.addAll(ResourceManager.getInstance().getImageList("BATTLEFIELD_BORDER_DECORATION"));


	}

	/**
	 * Получает GameField, который будет использоваться при построении фона.
	 *
	 * @return GameField
	 */
	public GameField getGameField()	{ return gameField; }

	/**
	 * Задает GameField, который будет использоваться при построении фона.
	 *
	 * @param gameField GameField
	 */
	public void setGameField(GameField gameField)
	{
		this.gameField = gameField;
		gfWidth = gameField.getWidth();
		gfHeight = gameField.getHeight();
	}

	/**
	 * Создает фон для GameFieldView, содержащий неизменяемые элементы игрового поля.
	 *
	 * @return изображение, содержащие неизменяемые элементы игрового поля.
	 */
	public Image buildBackground()
	{
		try
		{
			Image background = new Image(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
			g = background.getGraphics();
			drawBorder();
			drawGrid();
			g.flush();
			return background;
		}
		catch (SlickException ex)
		{
			Logger.getLogger(FieldBackgroundBuilder.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	/**
	 * Бордюрчик с декорациями.
	 */
	private void drawBorder()
	{
		g.drawImage(gridBorder, 0, 0);
		// Угловые декорации.
		drawAngularDecoration();
		drawBorderDecoration();
	}

	/**
	 * Угловые декорации.
	 */
	private void drawAngularDecoration()
	{
		// Вероятность отрисовки декорации в угле.
		final double angRnd = 0.5;

		// TODO Выглядит нормально, но метод setRotation(), а точнее setCenterOfRotation() работает не корректно. Передаелать под отражение.

		// Левый верхний.
		if (random.nextDouble() < angRnd)
		{
			int angImg = random.nextInt(angularDecoration.size());
			Image img = angularDecoration.get(angImg);
			img.setRotation(180);
			g.drawImage(img, gridOffsetX - img.getWidth() + angularDecorOffsetX,
							 gridOffsetY - img.getHeight() + angularDecorOffsetY);
		}

		// Правый верхний.
		if (random.nextDouble() < angRnd)
		{
			int angImg = random.nextInt(angularDecoration.size());
			Image img = angularDecoration.get(angImg);
			img.setRotation(270);
			g.drawImage(img, gridOffsetX + gridWidth - angularDecorOffsetX,
							 gridOffsetY - img.getHeight() + angularDecorOffsetY);
		}

		// Правый нижний.
		if (random.nextDouble() < angRnd)
		{
			int angImg = random.nextInt(angularDecoration.size());
			Image img = angularDecoration.get(angImg);
			img.setRotation(0);
			g.drawImage(img, gridOffsetX + gridWidth - angularDecorOffsetX,
							 gridOffsetY + gridHeight - angularDecorOffsetY);
		}

		// Левый нижний.
		if (random.nextDouble() < angRnd)
		{
			int angImg = random.nextInt(angularDecoration.size());
			Image img = angularDecoration.get(angImg);
			img.setRotation(90);
			g.drawImage(img, gridOffsetX - img.getWidth() + angularDecorOffsetX,
							 gridOffsetY + gridHeight - angularDecorOffsetY);
		}
	}

	/**
	 * Бордюрные декорации.
	 */
	private void drawBorderDecoration()
	{
		// Максимальное кол-во декораций вверху и внизу.
		final int horizCount = 3;

		// Максимальное кол-во декораций слева и справа.
		final int vertCount = 2;

		// Область на бордюрах возле углов, куда нельзя размещать декорации.
		final int indent = 20;

		// Верх
		int count = random.nextInt(horizCount + 1);
		if (count > 0)
		{
			int segm = (int)((float)(gridWidth - 2 * indent) / (float)count);
			for (int i = 0; i < count; i++)
			{
				int angImg = random.nextInt(borderDecoration.size());
				Image img = borderDecoration.get(angImg);
				int offset = (int)((segm - img.getWidth()) * random.nextDouble());
				img.setRotation(0);
				g.drawImage(img, gridOffsetX + segm * i + offset + indent,
							 gridOffsetY - img.getHeight());
			}
		}

		// Право
		count = random.nextInt(vertCount + 1);
		if (count > 0)
		{
			int segm = (int)((float)(gridHeight - 2 * indent) / (float)count);
			for (int i = 0; i < count; i++)
			{
				int angImg = random.nextInt(borderDecoration.size());
				Image img = borderDecoration.get(angImg);
				img.setRotation(90);
				int offset = (int)((segm - img.getWidth()) * random.nextDouble());
				g.drawImage(img, gridOffsetX + gridWidth - (img.getWidth() - img.getHeight())/2,
							 gridOffsetY + segm * i + offset + indent + (img.getWidth() - img.getHeight())/2);
			}
		}

		// Низ
		count = random.nextInt(horizCount + 1);
		if (count > 0)
		{
			int segm = (int)((float)(gridWidth - 2 * indent) / (float)count);
			for (int i = 0; i < count; i++)
			{
				int angImg = random.nextInt(borderDecoration.size());
				Image img = borderDecoration.get(angImg);
				img.setRotation(180);
				int offset = (int)((segm - img.getWidth()) * random.nextDouble());
				g.drawImage(img, gridOffsetX + segm * i + offset + indent,
							 gridOffsetY + gridHeight);
			}
		}

		// Лево
		count = random.nextInt(vertCount + 1);
		if (count > 0)
		{
			int segm = (int)((float)(gridHeight - 2 * indent) / (float)count);
			for (int i = 0; i < count; i++)
			{
				int angImg = random.nextInt(borderDecoration.size());
				Image img = borderDecoration.get(angImg);
				img.setRotation(270);
				int offset = (int)((segm - img.getWidth()) * random.nextDouble());
				g.drawImage(img, gridOffsetX - img.getHeight() - (img.getWidth() - img.getHeight())/2,
							 gridOffsetY + segm * i + offset + indent + (img.getWidth() - img.getHeight())/2);
			}
		}
	}

	/**
	 * Рисуем сетку и поверхность.
	 */
	private void drawGrid()
	{
		g.drawImage(grid, gridOffsetX, gridOffsetY);
		drawLandscape();
	}

	/**
	 * Рисуем поверхность.
	 */
	private void drawLandscape()
	{
		for (int i = 0; i < gfHeight; i++)
			for (int j = 0; j < gfWidth; j++)
				processCell(j, i);
	}

	/**
	 * Анализ каждой ячейки для отрисовки поверхности.
	 *
	 * @param x координата X ячейки
	 * @param y координата Y ячейки
	 */
	private void processCell(int x, int y)
	{
		int gfW = gfWidth - 1;
		int gfH = gfHeight - 1;

		int id = gameField.getCell(x, y);

		// Пусто? Ничего не делаем.
		if (id == SURF_EMPTY)
			return;

		// Подсчет смежных занятых ячеек для уголков.
		// Уголок - ячейка по диагонали и 2 смежные с ней по бокам.
		// lu - LeftUp, rd - RightDown и т.д.
		int luC = 0;
		int ruC = 0;
		int rdC = 0;
		int ldC = 0;

		// Количество смежных занятых ячеек.
		int cellCount = 0;

		// Проверяем по часовой стрелке начиная с левого.
		if (x>0 && gameField.getCell(x-1, y) == id)
		{
			luC++;
			ldC++;
			cellCount++;
		}
		if (x>0 && y>0 && gameField.getCell(x-1, y-1) == id)
		{
			luC++;
			cellCount++;
		}
		if (y>0 && gameField.getCell(x, y-1) == id)
		{
			luC++;
			ruC++;
			cellCount++;
		}
		if (x<gfW && y>0 && gameField.getCell(x+1, y-1) == id)
		{
			ruC++;
			cellCount++;
		}
		if (x<gfW && gameField.getCell(x+1, y) == id)
		{
			ruC++;
			rdC++;
			cellCount++;
		}
		if (x<gfW && y<gfH && gameField.getCell(x+1, y+1) == id)
		{
			rdC++;
			cellCount++;
		}
		if (y<gfH && gameField.getCell(x, y+1) == id)
		{
			rdC++;
			ldC++;
			cellCount++;
		}
		if (x>0 && y<gfH && gameField.getCell(x-1, y+1) == id)
		{
			ldC++;
			cellCount++;
		}

		// Абсолютные координаты ячейки.
		int pointX = gridOffsetX + cellWidth * x;
		int pointY = gridOffsetY + cellHeight * y;

		// Центральная ячейка.
		if (cellCount == 8)
			drawCenter(pointX, pointY, id);

		// Проверка углов.
		boolean luB = false;
		boolean ruB = false;
		boolean rdB = false;
		boolean ldB = false;
		int cornCount = 0;

		if (luC == 3)
		{
			luB = true;
			cornCount++;
		}
		if (ruC == 3)
		{
			ruB = true;
			cornCount++;
		}
		if (rdC == 3)
		{
			rdB = true;
			cornCount++;
		}
		if (ldC == 3)
		{
			ldB = true;
			cornCount++;
		}

		// Внутренний угол.
		if (cornCount == 3)
		{
			int cornDir = 0;
			if (!ldB)
				cornDir = 0;
			if (!luB)
				cornDir = 1;
			if (!ruB)
				cornDir = 2;
			if (!rdB)
				cornDir = 3;
			drawIntAngle(pointX, pointY, cornDir, id);
		}

		if (cornCount == 2)
		{
			// Боковушки.
			int bordDir = -1;
			if (ruB && rdB)
				bordDir = 0;
			if (rdB && ldB)
				bordDir = 1;
			if (luB && ldB)
				bordDir = 2;
			if (luB && ruB)
				bordDir = 3;
			if (bordDir != -1)
				drawSide(pointX, pointY, bordDir, id);

			// Двойной внутренний угол.
			int angDir = -1;
			if (luB && rdB)
				angDir = 0;
			if (ldB && ruB)
				angDir = 1;
			if (angDir != -1)
				drawCrossAngle(pointX, pointY, angDir, id);
		}

		// Уголок.
		if (cornCount == 1)
		{
			int angDir = 0;
			if (ruB)
				angDir = 0;
			if (rdB)
				angDir = 1;
			if (ldB)
				angDir = 2;
			if (luB)
				angDir = 3;
			drawExtAngle(pointX, pointY, angDir, id);
		}

		// Одиночный.
		if (cornCount == 0)
		{
			drawSingle(pointX, pointY, id);
		}
	}

	/**
	 * Рисуем одиночную ячейку с рельефом.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param surface тип рельефа
	 */
	private void drawSingle(int x, int y, int surface)
	{
		List<Image> imgList = surfaceMap.get(surface).get(SINGLE);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		g.drawImage(img, x, y);
	}

	/**
	 * Рисуем центровую ячейку с рельефом.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param surface тип рельефа
	 */
	private void drawCenter(int x, int y, int surface)
	{
		List<Image> imgList = surfaceMap.get(surface).get(CENTER);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		int rnd = random.nextInt(4);
		if (rnd == 1)
			img = img.getFlippedCopy(true, false);
		if (rnd == 2)
			img = img.getFlippedCopy(false, true);
		if (rnd == 3)
			img = img.getFlippedCopy(true, false);
		g.drawImage(img, x, y);
	}

	/**
	 * Рисуем внешний угол с рельефом.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param rotation ориентация угла
	 * @param surface тип рельефа
	 */
	private void drawExtAngle(int x, int y, int rotation, int surface)
	{
		List<Image> imgList = surfaceMap.get(surface).get(EXT_ANGLE);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		if (rotation == 1)
			img = img.getFlippedCopy(false, true);
		if (rotation == 2)
			img = img.getFlippedCopy(true, true);
		if (rotation == 3)
			img = img.getFlippedCopy(true, false);
		g.drawImage(img, x, y);
	}

	/**
	 * Рисуем внетренний угол.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param rotation ориентация угла
	 * @param surface тип рельефа
	 */
	private void drawIntAngle(int x, int y, int rotation, int surface)
	{
		List<Image> imgList = surfaceMap.get(surface).get(INT_ANGLE);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		if (rotation == 1)
			img = img.getFlippedCopy(false, true);
		if (rotation == 2)
			img = img.getFlippedCopy(true, true);
		if (rotation == 3)
			img = img.getFlippedCopy(true, false);
		g.drawImage(img, x, y);
	}

	/**
	 * Рисуем двойной угол.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param rotation ориентация угла
	 * @param surface тип рельефа
	 */
	private void drawCrossAngle(int x, int y, int rotation, int surface)
	{
		List<Image> imgList = surfaceMap.get(surface).get(CROSS_ANGLE);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		if (rotation == 1)
			img = img.getFlippedCopy(false, true);
		if (rotation == 2)
			img = img.getFlippedCopy(true, true);
		if (rotation == 3)
			img = img.getFlippedCopy(true, false);
		g.drawImage(img, x, y);
	}

	/**
	 * Рисуем боковую сторону.
	 *
	 * @param x абсолютная координата X ячейки
	 * @param y абсолютная координата Y ячейки
	 * @param rotation ориентация боковушки
	 * @param surface тип рельефа
	 */
	private void drawSide(int x, int y, int rotation, int surface)
	{
		List<Image> imgList;
		if (rotation == 0 || rotation == 2)
			imgList = surfaceMap.get(surface).get(VSIDE);
		else
			imgList = surfaceMap.get(surface).get(HSIDE);
		int len = imgList.size();
		Image img = imgList.get(random.nextInt(len));
		if (rotation == 2)
			img = img.getFlippedCopy(true, false);
		if (rotation == 3)
			img = img.getFlippedCopy(false, true);
		g.drawImage(img, x, y);
	}

}
