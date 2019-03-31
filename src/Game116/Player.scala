package Game116

class Player(var name: String, var loc: Location) {
  var health: Double = 100
  var attackAmt: Double = 3.0
  var weaponsOwned: Double = 0.0
  var timeInState: Double = 0.0


  def attack(p2: Player): Unit = {
    if(Math.abs(p2.loc.x-this.loc.x) <=2 && Math.abs(p2.loc.y-this.loc.y)<=2){
      println(this.loc.x)
      println(this.loc.y)
      p2.health = p2.health - attackAmt
    }
  }

  def walkLeft(): Unit = {
    this.loc.x -= 5
  }

  def walkRight(): Unit = {
    this.loc.x += 5
  }

  def walkUp(): Unit = {
    this.loc.y += 5
  }

  def walkDown(): Unit = {
    this.loc.y -= 5
  }

  def update(dt: Double): Unit = {
    timeInState += dt
  }



}
