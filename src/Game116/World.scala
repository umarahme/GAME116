package Game116

import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable._
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import scalafx.scene.Group
import scala.collection.mutable.ListBuffer

class World() extends App {
  var players: Map[String, Player] = Map()
  var potions: Map[String, HealthPotion] = Map()
  var walls = new ListBuffer[Wall]
  var weapons = new ListBuffer[Weapons]
  var potionCount: Double = 1
  var weaponCount: Double = 8
  var sceneGraphics: Group = new Group {}

  def toJSONS(): String = {
    implicit var gameFormat: Formats = DefaultFormats
    val jsonString: String = write(this.sceneGraphics)
    jsonString
  }
}
