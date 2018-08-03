package com.knoldus.actor

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{Actor, Props, Terminated}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router, RoutingLogic}
import com.knoldus.app.{Report, Tweet}


class Router(val routingLogic : RoutingLogic)  extends Actor{

  val counter : AtomicInteger = new AtomicInteger()

  val routees: Vector[ActorRefRoutee] = Vector.fill(7) {
    val workerCount = counter.getAndIncrement()
    val r = context.actorOf(Props(
      new Worker(workerCount)), name = s"workerActor-$workerCount")
    context watch r
    ActorRefRoutee(r)
  }


  var router = Router(routingLogic, routees)


  def receive: PartialFunction[Any, Unit] = {
    case tweet :Tweet if tweet.friendsCount >= 500 =>
      router.route(tweet, sender())
    case tweetLower :Tweet if tweetLower.friendsCount < 500 =>
      router.route(tweetLower, sender())
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
