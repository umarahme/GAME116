package Game116

import gui.GameGUI
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Weapons(loc: Location){
  var damage: Double = 10.0
  var Loc: Location = loc
  var size: Double = 15
  var Icon: Circle = new Circle {
    centerX = Math.random() * GameGUI.windowWidth
    centerY = Math.random() * GameGUI.windowHeight
    radius = size
    fill = Color.Black
  }
}

