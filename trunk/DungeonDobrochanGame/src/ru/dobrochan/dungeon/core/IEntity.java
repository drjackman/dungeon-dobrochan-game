
package ru.dobrochan.dungeon.core;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author SkinnyMan
 */
public interface IEntity
{
	IEntity Clone();
	void setParams(Map params);
	void setParam(String name, Object value);
	Object getParam(String name);
	Set<String> getParams();
}
