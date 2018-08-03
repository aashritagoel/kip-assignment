package com.knoldus.actor

import akka.actor.Actor
import akka.event.Logging
import com.knoldus.app.{Report, Tweet}

class Worker (val id : Int) extends Actor {

  val log = Logging(context.system, this)
  var msgCountWithGreaterCount = 0
  var msgCount = 0

  def receive: PartialFunction[Any, Unit] = {
    case tweet :Tweet if tweet.friendsCount >= 500=>
      msgCount += 1
      msgCountWithGreaterCount += 1
      log.info(s"worker {$id} -> Tweet id :${tweet.tweetId}, Friends Count :${tweet.friendsCount}")
      sender() ! s"worker {$id} -> Greater Friends Count Tweet Found!!"

    case tweet :Tweet if tweet.friendsCount < 500=>
      msgCount += 1
      log.info(s"worker {$id} -> Tweet id :${tweet.tweetId}, Friends Count :${tweet.friendsCount}")
      sender() ! s"worker {$id} -> Lesser Friends Count Tweet Found!!"

    case Report =>
      log.info(s"worker {$id} :Messages read so far: $msgCount which include $msgCountWithGreaterCount messages of higher friends count")
      sender() ! s"worker {$id} :Messages read so far: $msgCount"

    case _       => log.error("unknown message")
  }
}

