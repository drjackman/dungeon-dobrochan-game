package ru.dobrochan.dungeon.core.views
import ru.dobrochan.dungeon.ui.gamefield.GameFieldControl
import ru.dobrochan.dungeon.core.actions._;
import ru.dobrochan.dungeon.core.{IEntity, DummyEntity};
import ru.dobrochan.dungeon.ui.gamefield.renderobjects.MovingEntityRenderObject

trait View { 
  def update(action : AbstractAction)
}

class GameProcessView(val gameFieldControl: GameFieldControl) extends View{  
  def update(action : AbstractAction) = { 
    action match {
	    case TestMoveAction(contId, unitId, fromX, fromY, toX, toY) => {
	      val renderObj = new MovingEntityRenderObject(new DummyEntity(), fromX, fromY, toX, toY)
	      gameFieldControl.addRenderObject(renderObj)
	    }
    }
  }
}