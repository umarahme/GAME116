package networking

import java.sql.{Connection, DriverManager, ResultSet}

import Game116._
import gui.GameGUI
import gui.GameGUI.sceneGraphics
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

import scala.collection.mutable.ListBuffer

object Game {

  var game: World = new World

  def createPlayer(name:String): Player ={
    var tempPlayer1: Player = game.players(name)
    tempPlayer1
  }
  def createhealth(name:String): Double = {
    var player: Player = game.players(name)
    var health: Double = player.health
    health
  }
  def x(name:String): Double = {
    var player: Player = game.players(name)
    player.x
  }
  def y(name:String): Double = {
    var player: Player = game.players(name)
    player.y
  }

  def addPotion(p: Player): Unit = {
    for(health <- game.potions.values){
      if(Math.abs(p.Icon.translateX.toDouble - health.Icon.translateX.toDouble) <= 25 && Math.abs(p.Icon.translateY.toDouble-health.Icon.translateY.toDouble) <= 25) {
        if (p.health == 100) {
          p.health = 100
        } else if (p.health + health.potion >= 100) {
          p.health = 100
          game.potions -= health.name
          GameGUI.sceneGraphics.children.remove(health.Icon)
        } else {
          p.health = p.health + health.potion
          game.potions -= health.name
          GameGUI.sceneGraphics.children.remove(health.Icon)
        }
      }
    }
    println(game.potions.size)
  }

  def generateLocation:Location = {
    val x: Double = Math.random() * GameGUI.windowWidth
    val y: Double = Math.random() * GameGUI.windowHeight
    val newLoc: Location = new Location(x, y)
    newLoc
  }

  def generatePotions(): Unit = {
    var potionList = game.potions
    var potion = new HealthPotion(generateLocation)
    potionList += potion.name -> potion
    while (potionList.values.size < game.potionCount){
      var newPotion: HealthPotion = new HealthPotion(generateLocation)
      potionList = potionList += newPotion.name -> newPotion
      game.potions += newPotion.name -> newPotion
      sceneGraphics.children.add(newPotion.Icon)
    }
  }

  def generateWeapons(): Unit = {
    var weaponList: ListBuffer[Weapons] = game.weapons
    while (weaponList.length < game.weaponCount){
      var newWeapon: Weapons = new Weapons(generateLocation)
      weaponList = weaponList :+ newWeapon
      sceneGraphics.children.add(newWeapon.Icon)
    }
  }
}

