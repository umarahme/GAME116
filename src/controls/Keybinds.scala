package controls

import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import Game116.{Player, World}
import gui.GameGUI
import networking.Game


class Keybinds(player:Player) extends EventHandler[KeyEvent] {
  val LEFT: String = "A"
  val RIGHT: String = "D"
  val UP: String = "W"
  val DOWN: String = "S"
  val Attack: String = "F"

  var game: World = Game.game

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_PRESSED" => keyCode.getName match {
        case this.UP => {
          player.Icon.translateY.value -= player.playerSpeed
          Game.addPotion(player)
          GameGUI.socket.emit("UP")
        }
        case this.LEFT => {
          player.Icon.translateX.value -= player.playerSpeed
          Game.addPotion(player)
          GameGUI.socket.emit("LEFT")
        }
        case this.DOWN => {
          player.Icon.translateY.value += player.playerSpeed
          Game.addPotion(player)
          GameGUI.socket.emit("DOWN")
        }
        case this.RIGHT => {
          player.Icon.translateX.value += player.playerSpeed
          Game.addPotion(player)
          GameGUI.socket.emit("RIGHT")
        }
        case this.Attack => {
          player.attack(this.player,game)
          GameGUI.socket.emit("Attack")
        }
        case _ =>{
          Game.addPotion(player)
        }
      }
      case _ =>
    }
  }
}
