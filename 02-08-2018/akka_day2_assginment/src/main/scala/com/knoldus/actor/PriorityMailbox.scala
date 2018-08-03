package com.knoldus.actor

import akka.actor.{ActorSystem, PoisonPill}
import akka.dispatch.{PriorityGenerator, UnboundedStablePriorityMailbox}
import com.knoldus.app.Tweet
import com.typesafe.config.Config

class PriorityMailbox (settings: ActorSystem.Settings, config: Config)
  extends UnboundedStablePriorityMailbox(
    PriorityGenerator {
      case tweet: Tweet if tweet.friendsCount >= 500 => 0

      case tweet: Tweet if tweet.friendsCount < 500 => 1

      case PoisonPill   => 3

      case otherwise    => 2
    })
