package Game116

object Game {


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

  def eliminatePlayer(w:World): Unit = {
    var i: Int = 0
    var first: List[Player] = List()
    var end: List[Player] = List()
    var temp: List[Player] = List()
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


  def main(args: Array[String]): Unit = {
    var nLoc: Location = new Location(5,4)
    var loc2: Location = new Location(5,7)
    var player1: Player = new Player("DeVante",nLoc)
    var player2: Player = new Player("Patryk",loc2)
    player1.attack(player2)

    println(player2.health)


  }
  /*
  def checkWinner(w:Game116.World): Game116.Player = {

    if(w.players.length == 1){
      w.players(0)
    }

  }
  */



}
