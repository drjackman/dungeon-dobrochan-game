
package ru.dobrochan.dungeon.core.xmlloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.newdawn.slick.SlickException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ru.dobrochan.dungeon.core.gameentity.units.UnitEntityPrototype;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author SkinnyMan
 */
public class UnitLoader
{
	private class MutableString
	{
		public String value;
	}

	private class MutableInteger
	{
		public int value;
	}

	private class MutableFloat
	{
		public float value;
	}

	private Map<String, UnitEntityPrototype> unitPrototypes;

	public UnitLoader()
	{
		unitPrototypes = new HashMap<>();
	}

	public void loadUnits(InputStream is) throws SlickException
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		try
		{
			docBuilder = documentBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(is);
		}
		catch (ParserConfigurationException | SAXException | IOException e)
		{
			throw new SlickException("Could not load file", e);
		}

		doc.getDocumentElement().normalize();

		NodeList unitList = doc.getElementsByTagName("unit");

		int unitsCount = unitList.getLength();

		for (int i = 0; i < unitsCount; i++)
		{
			Node unitNode = unitList.item(i);

			if (unitNode.getNodeType() != Node.ELEMENT_NODE)
				continue;
			Element unitElement = (Element)unitNode;
			addUnit(unitElement);
		}
	}

	private void addUnit(Element unitElement)
	{
		UnitEntityPrototype newUnit = new UnitEntityPrototype();

		String typeName = unitElement.getAttribute("typeName");
		if (!typeName.isEmpty())
			newUnit.typeName = typeName;
		else
		{
			attrNotFoundMessage("typeName");
			return;
		}

		NodeList childNodes = unitElement.getChildNodes();
		int childCount = childNodes.getLength();
		boolean result = true;
		for (int i = 0; i < childCount; i++)
		{
			Node node = childNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;

			if (node.getNodeName().equalsIgnoreCase("data"))
			{
				if (!loadData(newUnit, (Element)node))
					result = false;
			}
			else if (node.getNodeName().equalsIgnoreCase("params"))
			{
				if (!loadParams(newUnit, (Element)node))
					result = false;
			}
			else if (node.getNodeName().equalsIgnoreCase("skills"))
			{
				if (!loadSkills(newUnit, (Element)node))
					result = false;
			}

		}
	}

	private boolean loadData(UnitEntityPrototype prototype, Element element)
	{
		boolean result = true;
		MutableString strWrap = new MutableString();

		if (getStrAttr(element, "name", strWrap))
			prototype.name = strWrap.value;
		else
			result = false;

		if (getStrAttr(element, "image", strWrap))
			prototype.imageName = strWrap.value;
		else
			result = false;

		if (getStrAttr(element, "bigImage", strWrap))
			prototype.bigImageName = strWrap.value;
		else
			result = false;

		// Allowed to be empty.
		prototype.description = element.getTextContent();

		return result;
	}

	private boolean loadParams(UnitEntityPrototype prototype, Element element)
	{
		boolean result = true;
		MutableInteger intWrap = new MutableInteger();

		if (getIntAttr(element, "lvl", intWrap))
			prototype.lvl = intWrap.value;
		else
			result = false;

		if (getIntAttr(element, "str", intWrap))
			prototype.str = intWrap.value;
		else
			result = false;

		if (getIntAttr(element, "stam", intWrap))
			prototype.stam = intWrap.value;
		else
			result = false;

		if (getIntAttr(element, "agi", intWrap))
			prototype.agi = intWrap.value;
		else
			result = false;

		if (getIntAttr(element, "sens", intWrap))
			prototype.sens = intWrap.value;
		else
			result = false;

		if (getIntAttr(element, "will", intWrap))
			prototype.will = intWrap.value;
		else
			result = false;

		return result;
	}

	private boolean loadSkills(UnitEntityPrototype prototype, Element element)
	{
		boolean result = true;
		MutableFloat fltWrap = new MutableFloat();

		if (getFloatAttr(element, "melee", fltWrap))
			prototype.skillMelee = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "range", fltWrap))
			prototype.skillRange = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "dodge", fltWrap))
			prototype.skillDodge = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "tactic", fltWrap))
			prototype.skillTactic = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "magic", fltWrap))
			prototype.skillMagic = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "control", fltWrap))
			prototype.skillControl = fltWrap.value;
		else
			result = false;

		if (getFloatAttr(element, "concent", fltWrap))
			prototype.skillConcentration = fltWrap.value;
		else
			result = false;

		return result;
	}

	private void loadAttributes(UnitEntityPrototype prototype, Element element)
	{

	}

	private void loadFeatures(UnitEntityPrototype prototype, Element element)
	{

	}

	private void attrNotFoundMessage(String attrName)
	{
		System.out.format("Attribute '%s' not found.\n", attrName);
	}

	private void invalidValueMessage(String valueName)
	{
		System.out.format("The value of %s's attribute is invalid.\n", valueName);
	}

	private boolean getStrAttr(Element element, String attrName, MutableString string)
	{
		String attrStr = element.getAttribute(attrName);
		if (!attrStr.isEmpty())
		{
			string.value = attrStr;
			return true;
		}
		else
		{
			attrNotFoundMessage(attrName);
			return false;
		}
	}

	private boolean getIntAttr(Element element, String attrName, MutableInteger integer)
	{
		String attrStr = element.getAttribute(attrName);
		if (!attrStr.isEmpty())
		{
			try
			{
				integer.value = Integer.parseInt(attrStr);
			}
			catch (NumberFormatException e)
			{
				invalidValueMessage(attrName);
				return false;
			}
		}
		else
		{
			attrNotFoundMessage(attrName);
			return false;
		}
		return true;
	}

	private boolean getFloatAttr(Element element, String attrName, MutableFloat floatWrap)
	{
		String attrStr = element.getAttribute(attrName);
		if (!attrStr.isEmpty())
		{
			try
			{
				floatWrap.value = Float.parseFloat(attrStr);
			}
			catch (NumberFormatException e)
			{
				invalidValueMessage(attrName);
				return false;
			}
		}
		else
		{
			attrNotFoundMessage(attrName);
			return false;
		}
		return true;
	}
}
