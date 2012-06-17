
package ru.dobrochan.dungeon.core;

import java.util.Map;
import java.util.Set;
import ru.dobrochan.dungeon.core.renderobjects.IRenderObject;

/**
 * Представляет клиент-версию IEntity, которая уведомляет свое представление при изменении параметров.
 *
 * @author SkinnyMan
 */
public class ClientEntity implements IEntity
{
	private IRenderObject renderObject;

	private IEntity entity;

	/**
	 * Инициализирует новый экземпляр класса ClientEntity из IEntity.
	 *
	 * @param entity сущность, для которой создается клиент-версия.
	 */
	public ClientEntity(IEntity entity)
	{
		this.entity = entity;
	}

	/**
	 * Устанавливает представление для данной сущности.
	 *
	 * @param renderObject объект, являющийся представлением данной сущности.
	 */
	public void setRenderObject(IRenderObject renderObject)
	{
		this.renderObject = renderObject;
	}

	/**
	 * Получает представление данной сущности.
	 *
	 * @return представление данной сущности
	 */
	public IRenderObject getRenderObject()
	{
		return renderObject;
	}

	/**
	 * @see IEntity#setParam(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setParam(String name, Object value)
	{
		entity.setParam(name, value);
		renderObject.stateChanged();
	}

	/**
	 * @see IEntity#getParam(java.lang.String)
	 */
	@Override
	public Object getParam(String name)
	{
		return entity.getParam(name);
	}

	/**
	 * @see IEntity#clone()
	 */
	@Override
	public ClientEntity clone()
	{
		IEntity clonedEntity = entity.clone();
		ClientEntity clientEntity = new ClientEntity(clonedEntity);
		clientEntity.renderObject = this.renderObject;
		return clientEntity;
	}

	/**
	 * @see IEntity#setParams(java.util.Map)
	 */
	@Override
	public void setParams(Map<String, Object> params)
	{
		entity.setParams(params);
		renderObject.stateChanged();
	}

	/**
	 * @see IEntity#getParams()
	 */
	@Override
	public Set<String> getParams()
	{
		return entity.getParams();
	}
}
