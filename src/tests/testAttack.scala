package tests
import Game116.{Game, Location, Player, gameState}
import org.scalatest.FunSuite

class testAttack extends FunSuite{
    test("attack functionality"){
      val game:gameState = Game.game
      val player1:Player = new Player("DeVante",new Location(0,0))
      val player2:Player = new Player("Umar",new Location(0,0))
      val player3:Player = new Player("Patryk",new Location(0,0))
      val player4:Player = new Player("Default",new Location(0,0))
      //locations are not determinate of a hit
      game.world.players = game.world.players :+ player1
      game.world.players = game.world.players :+ player2
      game.world.players = game.world.players :+ player3
      game.world.players = game.world.players :+ player4
      // these are the coordinates that determines a registered hit
      player1.playerIcon.translateX.value = 100
      player1.playerIcon.translateY.value = 200

      player2.playerIcon.translateX.value = 105
      player2.playerIcon.translateY.value = 210

      player3.playerIcon.translateX.value = 500
      player3.playerIcon.translateY.value = 456

      player4.playerIcon.translateX.value = 75
      player4.playerIcon.translateY.value = 147

      //This shows two players in the same region attacking each other also not effecting other players
      player1.attack(player1,game)
      assert(player2.health == 90)
      player1.attack(player1,game)
      player1.attack(player1,game)
      assert(player2.health == 70)
      player2.attack(player2,game)
      player2.attack(player2,game)
      assert(player1.health == 80)
      assert(player2.health == 70)
      assert(player3.health == 100)
      assert(player4.health == 100)

      //player not in range of anyone doing no damage
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      assert(player1.health == 80)
      assert(player2.health == 70)
      assert(player3.health == 100)
      assert(player4.health == 100)

      player1.health = 100
      player2.health = 100
      player3.health = 100
      player4.health = 100

      player1.playerIcon.translateX.value = 110
      player1.playerIcon.translateY.value = 115

      player2.playerIcon.translateX.value = 107
      player2.playerIcon.translateY.value = 103

      player3.playerIcon.translateX.value = 100
      player3.playerIcon.translateY.value = 120

      player4.playerIcon.translateX.value = 100
      player4.playerIcon.translateY.value = 120

      //shows multiple surrounding players taking damage
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      player3.attack(player3,game)
      assert(player1.health == 50)
      assert(player2.health == 50)
      assert(player3.health == 100)
      assert(player4.health == 50)


    }
}
