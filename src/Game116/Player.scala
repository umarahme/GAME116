package Game116

class Player(var name: String, var loc: Location) {
  var health: Double = 100
  var attackAmt: Double = 3.0
  var weaponsOwned: Double = 0.0


  def attack(p2: Player): Unit = {
    println("this")
    if(Math.abs(p2.loc.x-this.loc.x) <=2 && Math.abs(p2.loc.y-this.loc.y)<=2){
      println(this.loc.x)
      println(this.loc.y)
      p2.health = p2.health - attackAmt
    }
  }
}
