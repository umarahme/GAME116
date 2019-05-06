package networking

import java.net.InetSocketAddress

import play.api.libs.json.{JsValue, Json}
import Game116._
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.Json
import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState
import net.liftweb.json.DefaultFormats

import scala.collection.mutable.ListBuffer

case object UpdateGames

case class GameState(gameState: String)

case class SendToClients(message: String)

class FortServer extends Actor {

  import akka.io.Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))
  var gameActors: Map[String, ActorRef] = Map()
  var clients: Set[ActorRef] = Set()

  var game: World = new World

  override def receive: Receive = {
    case b: Bound => println("Listening on port: " + b.localAddress.getPort)
    case c: Connected =>
      println("Client Connected: " + c.remoteAddress)
      this.clients = this.clients + sender()
      sender() ! Register(self)
    case PeerClosed =>
      println("Client Disconnected: " + sender())
      this.clients = this.clients - sender()
    case r: Received =>
      println("Received: " + r.data.utf8String)
      val data = Json.parse(r.data.utf8String)
      //val players: Map[String, Player] = (data \ "Players").as[Map[String, Player]]
      //val potions: Map[String, HealthPotion] = (data \ "Potions").as[Map[String, HealthPotion]]
      val action = (data \ "action").as[String]
      val userName = (data \ "username").as[String]
      /*val health = (data \ "health").as[Int]
      val x = (data \ "translateX").as[Int]
      val y = (data \ "translateY").as[Int]

      var tempPlayer: Player = Game.game.players(userName)
      tempPlayer.x = x
      tempPlayer.y = y
      tempPlayer.health = health
*/
      var childActor: ActorRef = null

      if (!this.gameActors.contains(userName)) {
        childActor = context.actorOf(Props(classOf[GameActor], userName))
        gameActors += userName -> childActor
      } else {
        childActor = this.gameActors(userName)
      }

      println(action.toString())
      println("connected")
      if (action == "connected") {
      }
      else if (action == "disconnected") {
        childActor ! PoisonPill
        gameActors -= userName
      }
      else if (action == "Attack") {
        childActor ! Attack
      }
      else if (action == "play") {
        childActor ! Play
      }
    case send: SendToClients =>
      println("Sending: " + send.message)
      this.clients.foreach((client: ActorRef) => client ! Write(ByteString(send.message)))

    case UpdateGames =>

      gameActors.values.foreach(i => {
        i ! Update
      })

    case gs: GameState =>
      val delimiter = "~"
      self ! SendToClients(gs.gameState + delimiter)


  }

}
  object FortServer {

    def main(args: Array[String]): Unit = {
      val actorSystem = ActorSystem()

      import actorSystem.dispatcher

      import scala.concurrent.duration._

      val server = actorSystem.actorOf(Props(classOf[FortServer]))

      actorSystem.scheduler.schedule(0 milliseconds, 100 milliseconds, server, UpdateGames)

    }
  }
