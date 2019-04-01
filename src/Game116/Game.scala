package Game116

import gui.GameGUI
import gui.GameGUI.sceneGraphics
import scalafx.scene.shape.{Circle, Shape}

import scala.collection.mutable.ListBuffer

object Game {

  var game: gameState = new gameState
  var potionCount: Double = 10 /*thinking about doing game.world.players.length and multiplying it by an amount based on how many players we have*/
  var weaponCount: Double = 20

  //var potionList: List[HealthPotion] = List()
  //var weaponList: List[Weapons] = List()

  def addPotion(p: Player, health: HealthPotion): Unit = {
    if(Math.abs(p.loc.x-health.Loc.x) <=30 && Math.abs(p.loc.y-health.Loc.y)<=30) {
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
      health.potion = 0
    }
  }

  def causeDamage(p:Player, w: Weapons): Unit = {
    if(p.health - w.damage <= 0){
      p.health = 0
    }else{
      p.health = p.health - w.damage
    }
  }
  def generateLocation:Location = {
    val x: Double = Math.random() * GameGUI.windowWidth
    val y: Double = Math.random() * GameGUI.windowHeight
    val newLoc: Location = new Location(x, y)
    newLoc
  }

  def eliminatePlayer(w:World): Unit = {
    var i: Int = 0
    var first = new ListBuffer[Player]
    var end = new ListBuffer[Player]
    var temp = new ListBuffer[Player]
    if(w.players.length>0){
      for (p <- w.players){
        if (p.health == 0){
          i = w.players.indexOf(p)
          if (i == 0) {
            first = w.players.slice(1, w.players.length)
            w.players = first
          } else if (i == w.players.length - 1) {
            first = w.players.slice(0, w.players.length - 1)
            w.players = first
          }
          else {
            first = w.players.slice(0, i)
            end = w.players.slice(i + 1, w.players.length)
            w.players = first ++ end

          }
        }
      }
    }
  }


  def generatePotions(): Unit = {
    var potionList: ListBuffer[HealthPotion] = game.world.potions
    while (potionList.length < potionCount){
      var newPotion: HealthPotion = new HealthPotion(generateLocation)
      potionList = potionList :+ newPotion
      game.world.potions = potionList
      //sceneGraphics.children.add(newPotion.Icon)
    }
  }


  def generateWeapons(): Unit = {
    var weaponList: ListBuffer[Weapons] = game.world.weapons
    while (weaponList.length < weaponCount){
      var newWeapon: Weapons = new Weapons(generateLocation)
      weaponList = weaponList :+ newWeapon
      sceneGraphics.children.add(newWeapon.Icon)
    }
  }

/*
  def addPotion(p: Player): Unit = {
    for(health <- GameGUI.nList) {
      if (Math.abs(p.playerIcon.translateX.value - health.translateX.value) <= 39 && Math.abs(p.playerIcon.translateY.value - health.translateY.value) <= 39) {
        println(p.playerIcon.translateX.value)
        println(health.translateX.value)
        println(p.playerIcon.translateX.value - health.translateX.value)
        println(health)
        //health.fill = Color.White
        sceneGraphics.children.remove(health)
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
  */
  //can reuse these functions to generate and add them to the game


}

