/*package tests
import org.scalatest._
import Game116.HealthPotion
import Game116.Weapons
import Game116.Location
import Game116.Player
import gui.GameGUI




class TestAddPotion extends FunSuite{
  test("test changing health"){
    var nLoc: Location = new Location(201,1552)
    var loc3: Location = new Location(205,1555)
    var hp: HealthPotion = new HealthPotion(loc3)
    var player1: Player = new Player("DeVante",nLoc)

    var loc2: Location = new Location(187,1552)
    var player2: Player = new Player("Patryk",loc2)
    var newPotion: HealthPotion = new HealthPotion(loc2)

    //Game116.Game.addPotion(player1,hp)
    //player health is already full
    assert(player1.health==100)
    //basic addition to health with potion
    player1.health = 50
    Game116.Game.addPotion(player1,hp)
    assert(player1.health==70.0)
    //health should not add again
    Game116.Game.addPotion(player1,hp)
    assert(player1.health==70.0)
    //health not go over 100
    player2.health = 90.0
    Game116.Game.addPotion(player2,newPotion)
    assert(player2.health==100.0)
    //health should not go below 0
    //health should not add if player is not at same location of potion
    //var hp2: HealthPotion = new HealthPotion(loc3)
    player2.health = 0
    Game116.Game.addPotion(player2, newPotion)
    assert(player2.health==0)

  }
}
*/
