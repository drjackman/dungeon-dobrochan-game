package ru.dobrochan.dungeon.core

import ru.dobrochan.dungeon.core.consts.Surface
import ru.dobrochan.dungeon.Settings

class GameField 
{  
	val widthInCells = Settings.FIELD_WIDTH
	val heightInCells = Settings.FIELD_HEIGHT
	private val surfaces = Array.ofDim[Integer](widthInCells, heightInCells)
	
	def getSurfaceAt(x : Integer, y : Integer) : Integer = surfaces(x)(y);
	def setSurfaceAt(value : Integer, x : Integer, y : Integer) { surfaces(x)(y) = value } ;
}