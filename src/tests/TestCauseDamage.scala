package tests

import org.scalatest._
import Game116.Game
import Game116.HealthPotion
import Game116.Weapons
import Game116.Location
import Game116.Player

class TestCauseDamage extends FunSuite{
  test("test attack functions"){
    var nLoc: Location = new Location(5,4)
    var loc2: Location = new Location(5,6)
    var loc3: Location = new Location(4,8)
    var loc4: Location = new Location(3,7)
    var wp: Weapons = new Weapons(loc3)
    var player1: Player = new Player("DeVante",nLoc)
    var player2: Player = new Player("Patryk",loc2)
    player1.attack(player2)
    player2.attack(player1)
    player2.attack(player1)
    //attack works when players are close
    assert(player1.health==94.0)
    assert(player2.health==97.0)
    player1.loc = new Location(2,7)
    //player health does not change bc not close enough
    player1.attack(player2)
    assert(player2.health==97.0)
    player1.health = 15.0
    //attacks with a weapon this time
    Game.causeDamage(player1,wp)
    //cause damage works in normal case
    assert(player1.health==5.0)
    Game.causeDamage(player1,wp)
    //health cant go below 0
    assert(player1.health==0.0)

  }
}
