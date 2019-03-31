package gui

import Game116.{Game, HealthPotion, Player}
import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object OurGUI extends JFXApp {
  var lastUpdateTime: Long = System.nanoTime()

  val game = new Game()

  val newPlayer: Circle = createPlayer(game.p1.loc.x,game.p1.loc.y,Color.Blue,20)

  val newPlayer2: Circle = createPlayer(game.p2.loc.x,game.p2.loc.y,Color.Black,20)

  val newPotion: Shape = createPotion(game.h1.Loc.x,game.h1.Loc.y,Color.Green)

  val newPotion1: Shape = createPotion(game.h1.Loc.x,game.h1.Loc.y,Color.Green)

  val newPotion2: Shape = createPotion(game.h1.Loc.x,game.h1.Loc.y,Color.Green)

  val usedPotion: Shape = createPotion(game.h1.Loc.x,game.h1.Loc.y,Color.Green)

  var POTIONS: List[Shape] = List(newPotion,newPotion1,newPotion2,usedPotion)

  var SCENE = new Group{}

  SCENE.children.add(newPlayer)
  SCENE.children.add(newPlayer2)
  SCENE.children.add(newPotion)
  SCENE.children.add(usedPotion)
  SCENE.children.add(newPotion1)
  SCENE.children.add(newPotion2)



  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => {
        newPlayer.translateY.value -= 8
        addPotion(newPlayer,POTIONS,game.h1)
      }
      case "A" => {
        newPlayer.translateX.value -= 8
        addPotion(newPlayer,POTIONS,game.h1)
      }
      case "S" => {
        newPlayer.translateY.value += 8
        addPotion(newPlayer,POTIONS,game.h1)
      }
      case "D" => {
        newPlayer.translateX.value += 8
        addPotion(newPlayer, POTIONS, game.h1)
      }
      case "P" => {
        attack(newPlayer,newPlayer2)
      }
      case _ => {
        println(keyCode.getName + " pressed with no action")
        addPotion(newPlayer,POTIONS,game.h1)
      }
    }


  }

  var attackCircle: Shape = createPlayer(newPlayer.translateX.value,newPlayer.translateY.value,Color.Blue,60)

  def attack(p1: Circle,p2: Circle): Unit ={
    var temp: Int = 20
    var temp2: Int = 60
    if (Math.abs(p1.translateX.value - p2.translateX.value) <= 39 && Math.abs(p1.translateY.value - p2.translateY.value) <= 39) {
      p1.radius = 60
    }

  }

  def addPotion(p: Shape, potions: List[Shape],hp: HealthPotion): Unit = {
    for(health <- potions) {
      if (Math.abs(p.translateX.value - health.translateX.value) <= newPlayer.radius.toDouble && Math.abs(p.translateY.value - health.translateY.value) <= newPlayer.radius.toDouble) {

        //health.fill = Color.White
        SCENE.children.remove(health)
        /*
      if (p.health == 100) {
        p.health = 100
        health.potion = 0
      } else if (p.health + health.potion >= 100) {
        p.health = 100
        health.potion = 0
      } else {
        p.health = p.health + health.potion
        health.potion = 0
      }
      */
        //newPotion.fill = Color.White
      }
    }
  }



  def createPlayer(x: Double, y: Double, color: Color, r: Int): Circle = {
    new Circle{
      translateX = x
      translateY = y
      radius = r
      fill = color
    }
  }

  def createPotion(X: Double,Y: Double,color: Color): Shape = {
    new Rectangle{
      translateX = Math.random()*500
      translateY = Math.random()*500
      height = 10
      width = 10
      fill = color
    }
  }

  this.stage = new PrimaryStage{
    this.title = "Better FortNite"
    scene = new Scene(500,500){
      content = List(SCENE)
    }

    addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
    /*
    val update: Long => Unit = (time: Long) => {
      val dt: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(dt)
    }
    */


    //AnimationTimer(update).start()
  }





}
