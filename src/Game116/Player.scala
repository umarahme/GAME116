package Game116

import gui.GameGUI
import gui.GameGUI.sceneGraphics
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Player(var name: String, var loc: Location) {
  var health: Double = 100
  var attackAmt: Double = 10.0
  var weaponsOwned: Double = 0.0
  var playerCircleRadius:Double = 20
  val playerSpeed: Double = 13.9
  // moving these var here lets us modify them and clear up the gui
  var playerIcon: Circle = new Circle {
    translateX = Math.random() * GameGUI.windowWidth
    translateY = Math.random() * GameGUI.windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  def attack(attackingPlayer: Player,game:gameState): Unit = {
    for (otherPlayers <- game.world.players) {
      if (attackingPlayer != otherPlayers){
        if (Math.abs(attackingPlayer.playerIcon.translateX.value - otherPlayers.playerIcon.translateX.value) <= 20 &&
          Math.abs(attackingPlayer.playerIcon.translateY.value - otherPlayers.playerIcon.translateY.value) <= 20) {
          otherPlayers.health = otherPlayers.health - attackingPlayer.attackAmt
          println("Player2 Health: "+ otherPlayers.health)
          if (otherPlayers.health <= 0){
            sceneGraphics.children.remove(otherPlayers.playerIcon)
            println(otherPlayers.name," got wrecked")
          }
          //Game.eliminatePlayer(game.world) This was supposed to eliminate my dude but he still breathing in the negative health
        }
      }
    }
  }
}
