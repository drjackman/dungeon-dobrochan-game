
package ru.dobrochan.dungeon.core.gameentity;

/**
 *
 * @author SkinnyMan
 */
public abstract class GameEntity
{
	private static int nextID = 1;

	private int id;
	private EntityClass entityClass;
	private String typeName;

	int getID()	{ return id; }
	EntityClass getEntityClass() { return entityClass; }
	String getTypeName() { return typeName; }

	protected GameEntity(int id, EntityClass entityClass, String typeName)
	{
		this.id = id;
		this.entityClass = entityClass;
		this.typeName = typeName;
	}

	protected GameEntity(EntityClass entityClass, String typeName)
	{
		this.id = generateID();
		this.entityClass = entityClass;
		this.typeName = typeName;
	}

	private static int generateID()
	{
		return nextID++;
	}

}
