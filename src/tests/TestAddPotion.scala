package tests
import org.scalatest._
import Game116.Game
import Game116.HealthPotion
import Game116.Weapons
import Game116.Location
import Game116.Player



class TestAddPotion extends FunSuite{
  test("test changing health"){
    var nLoc: Location = new Location(5,4)
    var loc2: Location = new Location(5,6)
    var loc3: Location = new Location(4,8)
    var wp: Weapons = new Weapons(loc3)
    var player1: Player = new Player("DeVante",nLoc)
    var player2: Player = new Player("Patryk",loc2)
    var newPotion: HealthPotion = new HealthPotion(loc2)
    Game.addPotion(player1,newPotion)
    //player health is already full
    assert(player1.health==100)
    //basic addition to health with potion
    player1.attack(player2)
    player1.attack(player2)
    player1.attack(player2)
    Game.causeDamage(player2,wp)
    Game.causeDamage(player2,wp)
    Game.addPotion(player2,newPotion)
    assert(player2.health==91.0)
    //health should not go over 100
    Game.addPotion(player2,newPotion)
    assert(player2.health==100.0)
    //health goes down to 0
    for(i <- 0 to 10){
      Game.causeDamage(player2,wp)
    }
    assert(player2.health==0)
    //health should not go below 0
    Game.causeDamage(player2,wp)
    assert(player2.health==0)
    //health should not add if player is not at same location of potion
    var hp2: HealthPotion = new HealthPotion(loc3)
    Game.addPotion(player2,hp2)
    assert(player2.health==0)

  }
}
