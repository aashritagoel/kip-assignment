package com.knoldus.app


import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinRoutingLogic
import com.knoldus.actor.Router

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._


case class Tweet(tweetId: String, createdAt: Long, userId: Long, tweetUserName: String, countryName: String, friendsCount: Long)
case object Report

object TwitterStreamApp extends App {

  val system = ActorSystem("tweetAppSystem")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val buffer: ListBuffer[Tweet] = scala.collection.mutable.ListBuffer[Tweet]()
  val actorRef = system.actorOf(Props(new Router(RoundRobinRoutingLogic())).withDispatcher(
    "akka.actor.prio-dispatcher"))

  system.scheduler.schedule(0 millis, 50 millis) {
    val rg = scala.util.Random
    //noinspection ScalaStyle
    buffer += Tweet(java.util.UUID.randomUUID().toString, rg.nextLong(), rg.nextLong(), rg.nextString(10), "India", rg.nextInt(2000))
  }

  system.scheduler.schedule(10 millis, 1 second) {
      actorRef ! buffer.toList
    buffer.clear()
   }

  system.scheduler.schedule(20 millis, 1500 millis) {
    actorRef ! Report
  }
}


