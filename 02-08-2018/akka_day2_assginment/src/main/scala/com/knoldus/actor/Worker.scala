package com.knoldus.actor

import akka.actor.Actor
import akka.event.Logging
import com.knoldus.app.{Report, Tweet}

class Worker (val id : Int) extends Actor {
  val log = Logging(context.system, this)
  var msgCountWithGreaterCount = 0
  var msgCount = 0

  val actName: String = self.path.name

  def receive: PartialFunction[Any, Unit] = {
    case tweet :Tweet if tweet.friendsCount >= 500=>
      msgCount += 1
      msgCountWithGreaterCount += 1
      log.info(s"worker : {$id}, name : ($actName) ->  ($msgCount) It contains friends count greater than 500")

    case tweet :Tweet if tweet.friendsCount < 500=>
      msgCount += 1
      log.info(s"worker : {$id}, name : ($actName) ->  ($msgCount) It contains friends count lesser than 500")

    case Report =>
      log.info(s"worker : {$id}, name : ($actName) ->  saw total messages : ($msgCount) with $msgCountWithGreaterCount messages having greater friend count")

    case _       => log.error("unknown message")
  }
}

