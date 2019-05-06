package networking

import Game116.{Attack, Player, Update, World}
import akka.actor.Actor
import gui.GameGUI
import play.api.libs.json.{JsValue, Json}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

case object Attack
case object Update
case object Setup
case object Play

class GameActor(name: String) extends Actor {
  override def receive: Receive = {
    case Attack => Game.game.players(this.name).attack(Game.game.players(name), GameGUI.game)
    case Update => {
      sender() ! toJSON()
    }
    case Setup => {
      Game.generatePotions()
      Game.generateWeapons()
      }
    case Play => {
      Game.game.sceneGraphics.children.add(Game.game.players(this.name).Icon)
    }
  }
  def toJSON():String ={
    val gameState: Map[String, JsValue] = Map(
      "username" -> Json.toJson(this.name),
      "health" -> Json.toJson(Game.createhealth(name)),
      "translateX" -> Json.toJson(Game.x(name)),
      "translateY" -> Json.toJson(Game.y(name))
    )

    Json.stringify(Json.toJson(gameState))

  }


}
