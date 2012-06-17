
package ru.dobrochan.dungeon.core;

import java.util.Map;
import java.util.Set;

/**
 * Представляет игровую сущность.
 *
 * @author SkinnyMan
 */
public interface IEntity extends Cloneable
{
	/**
	 * Устанавливает параметр сущности.
	 *
	 * @param name имя параметра
	 * @param value значение параметра
	 */
	void setParam(String name, Object value);

	/**
	 * Устанавливает множество параметров.
	 *
	 * @param params множество параметров в виде пар имя/значение.
	 */
	void setParams(Map<String, Object> params);

	/**
	 * Возвращает значение параметра.
	 *
	 * @param name имя параметра
	 * @return значение параметра
	 */
	Object getParam(String name);

	/**
	 * Возвращает список имен параметров.
	 *
	 * @return список имен параметров
	 */
	Set<String> getParams();

	/**
	 *
	 * @return
	 */
	IEntity clone();
}
