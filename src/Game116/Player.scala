package Game116


import gui.GameGUI
import gui.GameGUI.sceneGraphics
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

case object Attack
case object Update


class Player(var name: String){

  var health: Double = 100
  var attackAmt: Double = 10.0
  var playerCircleRadius:Double = 20
  val playerSpeed: Double = 13.9
  var x: Double = Math.random() * GameGUI.windowWidth
  var y: Double = Math.random() * GameGUI.windowHeight
  var Icon: Circle = new Circle {
    translateX = x
    translateY = y
    radius = playerCircleRadius
    fill = Color.Green
  }
  def attack(attackingPlayer: Player,game:World): Unit = {
    for (otherPlayers <- game.players.values) {
      if (attackingPlayer != otherPlayers){
        if (Math.abs(attackingPlayer.Icon.translateX.value - otherPlayers.Icon.translateX.value) <= 20 &&
          Math.abs(attackingPlayer.Icon.translateY.value - otherPlayers.Icon.translateY.value) <= 20) {
          otherPlayers.health = otherPlayers.health - attackingPlayer.attackAmt
          println("Player2 Health: "+ otherPlayers.health)
          if (otherPlayers.health <= 0){
            sceneGraphics.children.remove(otherPlayers.Icon)
            game.players -= otherPlayers.name
            println(otherPlayers.name," got wrecked")
          }
        }
      }
    }
  }
}
