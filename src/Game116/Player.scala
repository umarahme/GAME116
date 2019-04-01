package Game116

import gui.GameGUI
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Player(var name: String, var loc: Location) {
  var health: Double = 100
  var attackAmt: Double = 3.0
  var weaponsOwned: Double = 0.0
  var playerCircleRadius:Double = 20
  val playerSpeed: Double = 10
  // moving these var here lets us modify them and clear up the gui
  var playerIcon: Circle = new Circle {
    translateX = Math.random() * GameGUI.windowWidth
    translateY = Math.random() * GameGUI.windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  def attack(p2: Player): Unit = {
    println("this")
    if(Math.abs(p2.loc.x-this.loc.x) <=2 && Math.abs(p2.loc.y-this.loc.y)<=2){
      println(this.loc.x)
      println(this.loc.y)
      p2.health = p2.health - attackAmt
    }
    //i think were going to have to implement an animation-ish update call.I tried sleep and that didnt work
    //either here or in the kaybinds class
    this.playerIcon.radius = 30
    this.playerIcon.radius = 20
  }
}
