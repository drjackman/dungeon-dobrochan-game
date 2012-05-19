/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dobrochan.dungeon.content;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;


public interface IContentManager
{
	public Image getImage(String path, String name);

	public Sound getSound(String path, String name);
}
