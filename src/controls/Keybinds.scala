package controls

import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import Game116.{Player, World, gameState}
import gui.GameGUI


class Keybinds(player:Player,game:gameState) extends EventHandler[KeyEvent] {
  val LEFT: String = "A"
  val RIGHT: String = "D"
  val UP: String = "W"
  val DOWN: String = "S"
  val Attack: String = "F"

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_PRESSED" => keyCode.getName match {
        case this.UP => {
          player.playerIcon.translateY.value -= player.playerSpeed
          println(player.playerIcon.translateY.value)
          GameGUI.addPotion(player)
        }
        case this.LEFT => {
          player.playerIcon.translateX.value -= player.playerSpeed
          println(player.playerIcon.translateX.value)
          GameGUI.addPotion(player)
        }
        case this.DOWN => {
          player.playerIcon.translateY.value += player.playerSpeed
          println(player.playerIcon.translateY.value)
          GameGUI.addPotion(player)
        }
        case this.RIGHT => {
          player.playerIcon.translateX.value += player.playerSpeed
          println(player.playerIcon.translateX.value)
          GameGUI.addPotion(player)
        }
        case this.Attack => {
          player.attack(this.player,game)
        }
        case _ =>{
          GameGUI.addPotion(player)
        }
      }
      case _ =>
    }
  }
}
