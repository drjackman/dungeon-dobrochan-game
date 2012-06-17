package ru.dobrochan.dungeon.core;

/**
 * Представляет контейнер для игровых сущностей.
 *
 * @author SkinnyMan
 */
public interface IEntityContainer extends Iterable<IEntity>
{
	/**
	 * Получает сущность по координатам.
	 * Скорей всего перенесу этот метод в другой класс.
	 *
	 * @deprecated Будет в другом классе.
	 *
	 * @param x координата X ячейки
	 * @param y координата Y ячейки
	 * @return игровая сущность, находящаяся в данной ячейке.
	 */
	IEntity getEntityFromXY(int x, int y);

	/**
	 * Возвращает количество сущностей в контейнере.
	 *
	 * @return количество сущностей в контейнере
	 */
	int size();

	/**
	 * Добавляет игровую сущность в контейнер.
	 *
	 * @param entity добавляемая сущность
	 */
	void addEntity(IEntity entity);

	/**
	 * Удаляет сущность из контейнера.
	 *
	 * @param entity удаляемая сущность.
	 */
	void removeEntity(IEntity entity);
}
