package Game116

import gui.GameGUI
import gui.GameGUI.sceneGraphics

import scala.collection.mutable.ListBuffer

object Game {
  var game: gameState = new gameState
  var potionCount: Double = 10 /*thinking about doing game.world.players.length and multiplying it by an amount based on how many players we have*/
  var weaponCount: Double = 20
/* Jessie is Daddy */
  def addPotion(p: Player, health: HealthPotion): Unit = {
    if(Math.abs(p.loc.x-health.Loc.x) <=2 && Math.abs(p.loc.y-health.Loc.y)<=2) {
      if (p.health == 100) {
        p.health = 100
      } else if (p.health + health.potion >= 100) {
        p.health = 100
      } else {
        p.health = p.health + health.potion
      }
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
      sceneGraphics.children.add(newPotion.Icon)
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
  //can reuse these functions to generate and add them to the game


  def main(args: Array[String]): Unit = {
    /*var nLoc: Location = new Location(5,4)
    var loc2: Location = new Location(5,7)
    var player1: Player = new Player("DeVante",nLoc)
    var player2: Player = new Player("Patryk",loc2)
    player1.attack(player2)

    println(player2.health)

*/
  }
  /*
  def checkWinner(w:Game116.World): Game116.Player = {

    if(w.players.length == 1){
      w.players(0)
    }

  }
  */



}
