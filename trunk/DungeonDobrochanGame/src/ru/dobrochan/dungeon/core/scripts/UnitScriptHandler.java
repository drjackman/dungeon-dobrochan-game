
package ru.dobrochan.dungeon.core.scripts;

import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import ru.dobrochan.dungeon.core.Entity;
import ru.dobrochan.dungeon.core.IEntity;
import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

import static ru.dobrochan.dungeon.consts.UnitParams.*;

/**
 *
 *
 * @author SkinnyMan
 */
public class UnitScriptHandler
{
	private static final String UNITS = "Units";

	private ScriptEngine engine;

	private IEntity[] entities;

	public UnitScriptHandler(Reader reader)
	{
		try
		{
			engine = new ScriptEngineManager().getEngineByName("javascript");
			String consts = ConstsScriptHandler.GetInstance().getConsts();
			engine.eval(consts);
			engine.eval(reader);
		}
		catch (ScriptException ex)
		{
			Logger.getLogger(UnitScriptHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public UnitScriptHandler(String data)
	{
		try
		{
			engine = new ScriptEngineManager().getEngineByName("javascript");
			String consts = ConstsScriptHandler.GetInstance().getConsts();
			engine.eval(consts);
			engine.eval(data);
		}
		catch (ScriptException ex)
		{
			Logger.getLogger(UnitScriptHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public IEntity[] getUnits()
	{
		if (entities == null)
			entities = LoadUnits();
		return LoadUnits();
	}

	private IEntity[] LoadUnits()
	{
		String[] unitNames = loadUnitNames();
		int count = unitNames.length;
		IEntity[] returnEntities = new IEntity[count];
		for (int i = 0; i < count; i++)
		{
			returnEntities[i] = loadEntity(unitNames[i]);
		}
		return returnEntities;
	}

	private String[] loadUnitNames()
	{
		NativeArray nativeUnits = (NativeArray)engine.get(UNITS);
		return nativeArrayToStrings(nativeUnits);
	}

	public IEntity loadEntity(String name)
	{
		NativeObject nativeEntity = (NativeObject)engine.get(name);
		Object[] params = nativeEntity.getIds();
		Entity entity = new Entity();
		for(Object paramName : params)
		{
			String param = (String)paramName;
			Object val = nativeEntity.get(param, nativeEntity);
			if (val instanceof NativeArray)
				val = nativeArrayToStrings((NativeArray)val);
			entity.setParam(param, val);
		}
		int size = (int)Math.round((Double)entity.getParam("Size"));	// пиздец
		entity.setParam(U_WIDTH, size);
		entity.setParam(U_HEIGHT, size);
		return entity;
	}

	private static String[] nativeArrayToStrings(NativeArray array)
	{
		int count = (int)array.getLength();
		String[] strings = new String[count];
		for(int i = 0; i < count; i++)
		{
			strings[i] = (String)array.get(i, array);
		}
		return strings;
	}
}
