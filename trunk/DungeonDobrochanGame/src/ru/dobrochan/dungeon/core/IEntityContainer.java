package ru.dobrochan.dungeon.core;

/**
 * Представляет контейнер для игровых сущностей.
 *
 * @author SkinnyMan
 */
public interface IEntityContainer extends Iterable<IEntity>
{
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
