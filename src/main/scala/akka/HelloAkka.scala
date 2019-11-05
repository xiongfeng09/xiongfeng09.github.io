package akka

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

import scala.io.Source

class MyActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case "test" ⇒ log.info("received test")
    case _ ⇒ log.info("received unknown message")
  }
}

object Main extends App {
  val system = ActorSystem("MySystem")
  val myActor = system.actorOf(Props[MyActor], name = "myactor")
  myActor ! "test"

}
