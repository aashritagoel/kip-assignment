package com.knoldus.actor

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import com.knoldus.app.{Report, Tweet}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class WorkerSpec() extends TestKit(ActorSystem("WorkerTest")) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  val testWorker: ActorRef = system.actorOf(Props(new Worker(1)))

  "A Worker actor" must {

    "process the tweets (with friends count less than 500) correctly" in {
      //noinspection ScalaStyle
      testWorker ! Tweet("1", 23, 23, "hello", "India", 126)
      expectMsg("worker {1} -> Lesser Friends Count Tweet Found!!")
    }

    "process the tweets (with friends count greater than 500) correctly" in {
      //noinspection ScalaStyle
      testWorker ! Tweet("1", 23, 23, "hello", "India", 826)
      expectMsg("worker {1} -> Greater Friends Count Tweet Found!!")
    }

    "report the tweets processed so far correctly" in {
      testWorker ! Report
      expectMsg("worker {1} :Messages read so far: 2")
    }

    "report the error if invalid message is sent correctly" in {
      testWorker ! "yeah! I am an error"
      expectNoMessage()
    }
  }
}