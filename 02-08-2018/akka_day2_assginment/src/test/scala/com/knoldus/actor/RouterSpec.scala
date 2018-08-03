package com.knoldus.actor

import akka.actor.{ActorRef, ActorSystem, PoisonPill, Props, Terminated}
import akka.routing.RoundRobinRoutingLogic
import akka.testkit.{ImplicitSender, TestKit}
import com.knoldus.app.{Report, Tweet}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class RouterSpec() extends TestKit(ActorSystem("RouterTest")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  val testRouter: ActorRef = system.actorOf(Props(new Router(RoundRobinRoutingLogic())))

  "A Router actor" must {

    "route the tweets (with friends count less than 500) correctly" in {
      //noinspection ScalaStyle
      testRouter ! List(Tweet("1", 23, 23, "hello", "India", 126))
      expectMsg("worker {0} -> Lesser Friends Count Tweet Found!!")
    }

    "route the tweets processed so far correctly" in {
      testRouter ! Report
      expectMsgAllClassOf(classOf[String], classOf[String], classOf[String], classOf[String], classOf[String])

    }

    "handle the condition of terminated routee" in {
      val testRouter1: ActorRef = system.actorOf(Props(new Router(RoundRobinRoutingLogic())))
      //noinspection ScalaStyle
      val testWorker = system.actorOf(Props(new Worker(9)))
      testWorker ! PoisonPill
      testRouter1 ! Terminated(testWorker)(existenceConfirmed = false,addressTerminated = true)
      expectNoMessage()
    }
  }
}