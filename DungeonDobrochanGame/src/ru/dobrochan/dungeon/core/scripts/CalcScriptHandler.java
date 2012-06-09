
package ru.dobrochan.dungeon.core.scripts;

import java.io.Reader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import ru.dobrochan.dungeon.core.IEntity;

/**
 *
 * @author SkinnyMan
 */
public class CalcScriptHandler
{
	private ScriptEngine engine;
	private ScriptContext context;

	public CalcScriptHandler(Reader reader)
	{
		try
		{
			engine = new ScriptEngineManager().getEngineByName("javascript");
			String consts = ConstsScriptHandler.GetInstance().getConsts();
			engine.eval(consts);
			engine.eval(reader);
		    context = engine.getContext();
		}
		catch (ScriptException ex)
		{
			Logger.getLogger(CalcScriptHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void calc(IEntity entity)
	{
		try
		{
			Set<String> params = entity.getParams();

			engine.eval("var unit = new Object()");
			for (String param : params)
			{
				String paramName = param;
				Object val = entity.getParam(param);

//				engine.put("key", paramName);
//				engine.put("value", val);
//				engine.eval("unit[key] = value");

				engine.put(paramName, val);

			}

			//engine.put("unit", entity);
			Object hp = engine.eval("isUndead()");
			//int iHP = (int)hp;
			int i = 0;
		}
		catch (ScriptException ex)
		{
			Logger.getLogger(CalcScriptHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setRParams(IEntity entity)
	{
		entity.setParam("rStr", entity.getParam("bStr"));
		entity.setParam("rStam", entity.getParam("bStam"));
		entity.setParam("rAgi", entity.getParam("bAgi"));
		entity.setParam("rWill", entity.getParam("bWill"));
		entity.setParam("rSens", entity.getParam("bSens"));
	}
}
