package com.knoldus

import org.scalatest.AsyncFlatSpec

class TwitterSpec extends AsyncFlatSpec {

  val test = new Twitter

  "Twitter class" should "fetch the tweets" in {
    test.getNumberOfTweets("mango").map(actual => assert(actual >= 1))
  }

  it should "fetch the average number of tweets corresponding to input" in {
    test.getAverageTweets("mango").map(actual => assert(actual >= 1))
  }

  it should "fetch the average number of likes per tweet corresponding to input" in {
    test.getAverageLikes("mango").map(actual => assert(actual >= 0))
  }

  it should "give exception when there exists no tweet for the input string" in {
    recoverToSucceededIf[ArithmeticException]{
      test.getAverageLikes("itrdex")
    }
  }

  it should "fetch the average number of re-tweets per tweet corresponding to inputwhile computing average likes per tweet" in {
    test.getAverageReTweets("mango").map(actual => assert(actual >= 0))
  }

  it should "give exception when there exists no tweet for the input string while computing average re-tweet per tweet" in {
    recoverToSucceededIf[ArithmeticException]{
      test.getAverageLikes("kjhhggd")
    }
  }
}
