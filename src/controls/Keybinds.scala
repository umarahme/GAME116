package controls

import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import Game116.Player


class Keybinds(player:Player) extends EventHandler[KeyEvent] {
  val LEFT: String = "A"
  val RIGHT: String = "D"
  val UP: String = "W"
  val DOWN: String = "S"
  val Attack: String = "F"
  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_PRESSED" => keyCode.getName match {
        case this.UP => player.playerIcon.translateY.value -= player.playerSpeed
        case this.LEFT => player.playerIcon.translateX.value -= player.playerSpeed
        case this.DOWN => player.playerIcon.translateY.value += player.playerSpeed
        case this.RIGHT => player.playerIcon.translateX.value += player.playerSpeed
        case this.Attack => player.attack(this.player)
        case _ =>
      }
      case _ =>
    }
  }
}
