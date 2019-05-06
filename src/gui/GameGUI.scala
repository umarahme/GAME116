package gui

import Game116.{Player, World}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import controls.Keybinds
import javafx.event.ActionEvent
import scalafx.scene.layout.VBox
import scalafx.scene.control.{Button, TextField}
import play.api.libs.json.{JsValue, Json}
import io.socket.client.{IO, Socket}
import io.socket.emitter.Emitter
import javafx.application.Platform
import networking.Game

class HandleMessagesFromPython() extends Emitter.Listener {
  override def call(objects: Object*): Unit = {
    Platform.runLater(() => {
      val jsonGameState = objects.apply(0).toString
      println(jsonGameState)
      val gameState: JsValue = Json.parse(jsonGameState)
    })
  }
}

object GameGUI extends JFXApp {

  var socket: Socket = IO.socket("http://localhost:8080/")
  socket.on("message", new HandleMessagesFromPython)

  socket.connect()
  socket.emit("register", "myuser")

  val windowWidth: Double = 1600
  val windowHeight: Double = 900

  var game: World = Game.game
  val inputDisplay: TextField = new TextField {
    style = "-fx-font: 18 ariel;"
  }
  val username: String = inputDisplay.text.value
  val player = new Player(username)
  game.players = game.players += player.name -> player
  var sceneGraphics: Group = game.sceneGraphics

 /* def playGame(): Unit ={
    this.stage.scene = new Scene(windowWidth,windowHeight) {
      content = List(sceneGraphics)
    }
    sceneGraphics.children.add(this.player.Icon)
}
  val button: Button = new Button {
    minWidth = 100
    minHeight = 100
    style = "-fx-font: 28 ariel;"
    text = "Play"

    onAction = (event: ActionEvent) =>{ socket.emit("play")
    }
}*/
  socket.emit("play")
  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene(windowWidth,windowHeight) {
      content = List(sceneGraphics)
    }

      // add an EventHandler[KeyEvent] to control player movement
      addEventHandler(KeyEvent.ANY, new Keybinds(player))

    }
    //socket.connect()
    //socket.emit("register", username)

    // Start Animations. Calls update 60 times per second (takes update as an argument)

}
