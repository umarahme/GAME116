package Game116

import scala.collection.mutable.ListBuffer

class World() {
  var players= new ListBuffer[Player]
  var potions = new ListBuffer[HealthPotion]
  var walls= new ListBuffer[Wall]
  var weapons= new ListBuffer[Weapons]
  //changed these to listbuffers so we can edit and change them throughout the game state
}
