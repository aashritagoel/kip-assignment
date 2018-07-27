package com.knoldus

import java.util
import collection.JavaConversions._
import twitter4j.conf.ConfigurationBuilder
import twitter4j.TwitterFactory
import twitter4j.Query
import twitter4j.Status
import scala.concurrent.ExecutionContext.Implicits.global
import scala.annotation.tailrec
import scala.concurrent.Future

class Twitter{

  def setConfigurations() :ConfigurationBuilder = {

    val config = new ConfigurationBuilder
    config.setDebugEnabled(true)
      .setOAuthConsumerKey("consumer-key")
      .setOAuthConsumerSecret("consumer-secret")
      .setOAuthAccessToken("access-token")
      .setOAuthAccessTokenSecret("access-token-secret")
  }

  def retrieveTweet(input :String) :util.List[Status] = {
    val tf = new TwitterFactory(setConfigurations().build)
    val twitter = tf.getInstance
    twitter.search(new Query("#" + input)).getTweets
    /*to fetch the username and tweet we can use the below statement
    for (status: Status <- statuses) yield (status.getUser, status.getText)*/
  }

  def getNumberOfTweets(input :String) :Future[Int] = Future{
    val statuses:util.List[Status]= retrieveTweet(input)
    statuses.size
  }

  def getAverageTweets(input:String) :Future[Int] = {
    getNumberOfTweets(input).map(num => num / 7)
  }

  def getAverageLikes(input: String) :Future[Int] = Future{
    val statuses:util.List[Status]= retrieveTweet(input)
    val listOfLikesCount = (for (status: Status <- statuses) yield status.getFavoriteCount).toList
    val totalLikes = getTotalOfList(listOfLikesCount, 0)
    val numberOfTweets = statuses.size
    numberOfTweets match {
      case 0 => throw new ArithmeticException
      case _ => totalLikes / numberOfTweets
    }
  }

  def getAverageReTweets(input:String) :Future[Int] = Future{
    val statuses:util.List[Status]= retrieveTweet(input)
    val listOfReTweetCount = (for (status: Status <- statuses) yield status.getRetweetCount).toList
    val totalReTweets = getTotalOfList(listOfReTweetCount, 0)
    val numberOfTweets = statuses.size
    numberOfTweets match {
      case 0 => throw new ArithmeticException
      case _ => totalReTweets / numberOfTweets
    }
  }

  @tailrec
  private def getTotalOfList(list: List[Int], sum: Int): Int ={
    list match {
      case a :: rest => getTotalOfList(rest, sum + a)
      case _ => sum
    }
  }
}
