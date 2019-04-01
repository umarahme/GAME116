package gui


import Game116.Game.game
import Game116.{Game, Player, gameState}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.{Group, Scene}
import controls.Keybinds
import javafx.event.ActionEvent
import scalafx.scene.layout.VBox
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.shape.Circle

import scala.collection.mutable.ListBuffer

object GameGUI extends JFXApp {
  val windowWidth: Double = 1600
  val windowHeight: Double = 900
  var game: gameState = Game.game
  val inputDisplay: TextField = new TextField {
    style = "-fx-font: 18 ariel;"
  }
  val name: String = inputDisplay.text.value
  val player = new Player(name, Game.generateLocation)
  game.world.players = game.world.players :+ player
// Umar sucks on so many different types of balls its ridiculous. I am testing patyks dumb ass computer
  var sceneGraphics: Group = game.sceneGraphics
  Game.generateWeapons()
  Game.generatePotions()
  var n1: Circle = game.world.potions(0).Icon
  var n2: Circle = game.world.potions(1).Icon
  var n3: Circle = game.world.potions(2).Icon
  var n4: Circle = game.world.potions(3).Icon
  var n5: Circle = game.world.potions(4).Icon
  var n6: Circle = game.world.potions(5).Icon
  var n7: Circle = game.world.potions(6).Icon
  var n8: Circle = game.world.potions(7).Icon
  var n9: Circle = game.world.potions(8).Icon
  var n10: Circle = game.world.potions(9).Icon

  var nList: ListBuffer[Circle] = ListBuffer(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10)

  sceneGraphics.children.add(n1)
  sceneGraphics.children.add(n2)
  sceneGraphics.children.add(n3)
  sceneGraphics.children.add(n4)
  sceneGraphics.children.add(n5)
  sceneGraphics.children.add(n6)
  sceneGraphics.children.add(n7)
  sceneGraphics.children.add(n8)
  sceneGraphics.children.add(n9)
  sceneGraphics.children.add(n10)


  def addPotion(p: Player): Unit = {
    for(health <- GameGUI.nList) {
      if (Math.abs(p.playerIcon.translateX.value - health.translateX.value) <= 30 && Math.abs(p.playerIcon.translateY.value - health.translateY.value) <= 30) {

        //health.fill = Color.White
        sceneGraphics.children.remove(health)
        var tempIndex: Int = nList.indexOf(health)

        println(p.health)
        println(game.world.potions(tempIndex).potion)

        if (p.health == 100) {
          p.health = 100
        } else if (p.health + game.world.potions(tempIndex).potion >= 100) {
          p.health = 100
        } else {
          p.health = p.health + game.world.potions(tempIndex).potion
        }

        game.world.potions(tempIndex).potion = 0

        println(p.health)
        println(game.world.potions(tempIndex).potion)

      }
    }
  }


  def playGame(): Unit ={
    this.stage.scene = new Scene(windowWidth,windowHeight) {
      content = List(sceneGraphics)
    }
    sceneGraphics.children.add(player.playerIcon)
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
      addEventHandler(KeyEvent.ANY, new Keybinds(player))

    }


    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)

}
