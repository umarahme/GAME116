package gui


import Game116.{Player, gameState,Game}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import controls.Keybinds
import javafx.event.ActionEvent
import scalafx.scene.layout.VBox
import scalafx.scene.control.{Button, TextField}

object GameGUI extends JFXApp {
  val windowWidth: Double = 1600
  val windowHeight: Double = 900
  var game: gameState = Game.game
  val inputDisplay: TextField = new TextField {
    style = "-fx-font: 18 ariel;"
  }
  val name: String = inputDisplay.text.value
  val player = new Player(name, Game.generateLocation)
  val player2 = new Player("Patryk", Game.generateLocation)// lol his name is a spelling error
  game.world.players = game.world.players :+ player
  game.world.players = game.world.players :+ player2

  var sceneGraphics: Group = game.sceneGraphics
  Game.generateWeapons()
  Game.generatePotions()

  def playGame(): Unit ={
    this.stage.scene = new Scene(windowWidth,windowHeight) {
      content = List(sceneGraphics)
    }
    sceneGraphics.children.add(player.playerIcon)
    sceneGraphics.children.add(player2.playerIcon)
  }
  val button: Button = new Button {
    minWidth = 100
    minHeight = 100
    style = "-fx-font: 28 ariel;"
    text = "Play"
    onAction = (event: ActionEvent) => playGame()
  }
// for further development i think we would have to store the primarystage, sceneGraphics and the gameState(mightve already done it) somewhere other than the gui and have the gui only contain visuals for the client
  this.stage = new PrimaryStage {
    this.title = "2D Graphics"
    scene = new Scene() {
      content = List(
        new VBox() {
          children = List(inputDisplay, button)
        }
      )

      // add an EventHandler[KeyEvent] to control player movement
      addEventHandler(KeyEvent.ANY, new Keybinds(player,game))

    }


    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)

}
