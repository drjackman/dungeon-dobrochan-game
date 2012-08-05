
package ru.dobrochan.dungeon.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Представляет игровую сущность.
 * Все, с чем можно взаимодействовать, включая юниты, препядствия, ловушки итд.
 *
 * @author SkinnyMan
 */
public class Entity implements IEntity
{
	private HashMap<String, Object> params = new HashMap<String, Object>();

	/**
	 * Устанавливает значение для указанного параметра.
	 * Если параметр отсутствует — он будет добавлен.
	 *
	 * @param name имя параметра
	 * @param value значение параметра
	 */
	@Override
	public void setParam(String name, Object value)
	{
		params.put(name, value);
	}

	/**
	 * Устанавливает множество параметров.
	 *
	 * @param params множество параметров в виде пар имя/значение
	 */
	@Override
	public void setParams(Map<String, Object> params)
	{
		this.params.putAll(params);
	}

	/**
	 * Возвращает значение параметра.
	 *
	 * @param name имя параметра
	 * @return значение параметра
	 */
	@Override
	public Object getParam(String name)
	{
		return params.get(name);
	}

	/**
	 * Возвращает список имен параметров.
	 * (Может пусть сразу возвращает карту?)
	 *
	 * @return список параметров
	 */
	@Override
	public Set<String> getParams()
	{
		return params.keySet();
	}

	/**
	 * Возвращает копию данной сущности.
	 *
	 * @return копия данной сущности
	 */
	@Override
	public Entity clone()
	{
		// TODO сделать нормальное глубокое копирование
		Entity entity = new Entity();
		Set<String> paramsSet = params.keySet();
		for (String param : paramsSet)
		{
			Object newParam = getParam(param);
			if (newParam instanceof String[])
			{
				entity.setParam(param, ((String[])newParam).clone());
			}
			else
			{
				entity.setParam(param, newParam);
			}
		}
		return entity;
	}
}
