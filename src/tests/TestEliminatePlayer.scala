package tests

import org.scalatest._
import Game116.Game
import Game116.HealthPotion
import Game116.Weapons
import Game116.Location
import Game116.Player
import Game116.World
import scala.collection.mutable.ListBuffer

//may have broken testing

class TestEliminatePlayer extends FunSuite{
  test("test eliminate player"){
    var loc1: Location = new Location(4,3)
    var loc2: Location = new Location(5,6)
    var loc3: Location = new Location(4,8)
    var loc4: Location = new Location(3,7)
    var loc5: Location = new Location(1,4)
    var loc6: Location = new Location(6,3)
    var player1: Player = new Player("DeVante",loc1)
    var player2: Player = new Player("Patryk",loc2)
    var player3: Player = new Player("Umar",loc3)
    var player4: Player = new Player("Bob",loc4)
    var player5: Player = new Player("Jesse",loc5)
    var player6: Player = new Player("Carl",loc6)
    var newList = new ListBuffer[Player]
    newList = ListBuffer(player1,player2,player3,player4,player5,player6)
    var newWorld: World = new World()
    newWorld.players = newList
    player1.health = 0
    Game.eliminatePlayer(newWorld)
    //eliminates player from beginning
    assert(newWorld.players == ListBuffer(player2,player3,player4,player5,player6))
    player6.health = 0
    player3.health = 0
    Game.eliminatePlayer(newWorld)
    //eliminates player from middle and end
    assert(newWorld.players == ListBuffer(player2,player4,player5))
    Game.eliminatePlayer(newWorld)
    //no players have 0 health
    assert(newWorld.players == ListBuffer(player2,player4,player5))
    player2.health = 0
    player4.health = 0
    Game.eliminatePlayer(newWorld)
    assert(newWorld.players == ListBuffer(player5))
    player5.health = 0
    Game.eliminatePlayer(newWorld)
    //if 1 player is left and has 0 health
    assert(newWorld.players == ListBuffer())
    //function does not go through if no players are alive
    Game.eliminatePlayer(newWorld)
    assert(newWorld.players == ListBuffer())


  }
}

