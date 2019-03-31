import javafx.event.EventHandler
import javafx.scene.input.KeyEvent
import Game116.Player

abstract class KeyboardInputs(player: Player) extends EventHandler[KeyEvent] {

  val LEFT: String
  val RIGHT: String
  val UP: String
  val DOWN: String

  override def handle(event: KeyEvent): Unit = {
    val keyCode = event.getCode
    event.getEventType.getName match {
      case "KEY_RELEASED" => keyCode.getName match {
        //case this.LEFT => player.walkLeft()
        //case this.RIGHT => player.walkRight()
        //case this.UP => player.walkUp()
        //case this.DOWN => player.walkDown()
        case _ =>
      }

      case _ =>
    }
  }
}

class WASDInputs(player: Player) extends KeyboardInputs(player) {
  override val LEFT: String = "A"
  override val RIGHT: String = "D"
  override val UP: String = "W"
  override val DOWN: String = "S"

}
