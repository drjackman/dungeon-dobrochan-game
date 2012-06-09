
package ru.dobrochan.dungeon.core.scripts;

import sun.security.jca.GetInstance;

/**
 *
 * @author SkinnyMan
 */
public class ConstsScriptHandler
{
	static private ConstsScriptHandler instance = new ConstsScriptHandler();

	private ConstsScriptHandler() {}

	public static ConstsScriptHandler GetInstance()
	{
		return instance;
	}

	private String consts = "";

	public String getConsts()
	{
		return consts;
	}

	public void setConsts(String consts)
	{
		this.consts = consts;
	}


}
