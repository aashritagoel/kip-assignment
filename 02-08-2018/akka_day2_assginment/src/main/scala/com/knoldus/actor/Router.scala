package com.knoldus.actor

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, Props, Terminated}
import akka.routing.{ActorRefRoutee, Router, RoutingLogic}
import com.knoldus.app.{Report, Tweet}

class Router(val routingLogic : RoutingLogic)  extends Actor{

  val counter : AtomicInteger = new AtomicInteger()
  val noOfRoutee = 5
  val routees: Vector[ActorRefRoutee] = Vector.fill(noOfRoutee) {
    val workerCount = counter.getAndIncrement()
    val r = context.actorOf(Props(
      new Worker(workerCount)), name = s"workerActor-$workerCount")
    context watch r
    ActorRefRoutee(r)
  }

  var router = Router(routingLogic, routees)

  def receive: PartialFunction[Any, Unit] = {

    case buffer :List[Tweet]=> for (i <- buffer.indices) router.route(buffer(i), sender())

    case Report => routees.foreach(ref => ref.send(Report, sender()))

    case Terminated(a) =>
      router = router.removeRoutee(a)
      val workerCount = counter.getAndIncrement()
      val r = context.actorOf(Props(
        new Worker(workerCount)), name = s"workerActor-$workerCount")
      context watch r
      router = router.addRoutee(r)
  }
}
