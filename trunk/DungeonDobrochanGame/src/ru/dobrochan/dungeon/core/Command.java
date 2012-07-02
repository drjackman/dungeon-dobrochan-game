package ru.dobrochan.dungeon.core;

/**
 * Команда для обработки.
 *
 * @author SkinnyMan
 */
public class Command
{
	private int id;
	private Object data;

	/**
	 * Инициирует новый экземпляр класса Command без данных.
	 *
	 * @param id идентификатор комманды
	 */
	public Command(int id)
	{
		this.id = id;
	}

	/**
	 * Инициирует новый экземпляр класса Command с указанными данными.
	 *
	 * @param id идентификатор комманды
	 * @param data данные
	 */
	public Command(int id, Object data)
	{
		this.id = id;
		this.data = data;
	}

	/**
	 * Возвращает идентификатор команды
	 *
	 * @return идентификатор команды
	 */
	public int getID()
	{
		return id;
	}

	/**
	 * Возвращает данные связанные с данный командой.
	 *
	 * @return данные
	 */
	public Object getData()
	{
		return data;
	}

}
