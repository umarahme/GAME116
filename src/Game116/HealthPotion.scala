package Game116

import gui.GameGUI
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class HealthPotion(loc: Location){
  var name: String = (loc.x, loc.y).toString()
  var size: Double = 10
  var potion: Double = 20.0
  var Loc: Location = loc
  var Icon: Circle = new Circle {
    translateX = Math.random() * GameGUI.windowWidth
    translateY = Math.random() * GameGUI.windowHeight
    radius = size
    fill = Color.Red
  }
}
