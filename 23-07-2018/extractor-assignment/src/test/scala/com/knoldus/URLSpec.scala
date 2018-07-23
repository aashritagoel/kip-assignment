package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class URLSpec extends FunSuite with MustMatchers {
  val test = new URL

  test("Checking for apply function"){
    val actualResult = URL("http", "aws.amazon.com", "/console/home", Map("state" -> "hash", "isauthcode" -> "true", "code" -> "112"))
    val expectedResult ="http://aws.amazon.com/console/home?state=hash&isauthcode=true&code=112"
    actualResult must equal(expectedResult)
  }

  test("Checking for unapply function"){
    val actualResult = test.extractUrl("http://aws.amazon.com")
    val expectedResult =s"protocol= http \n domain=aws.amazon.com \n path=  \n params= Map()"
    actualResult must equal(expectedResult)
  }

  test("Checking for unapply function1"){
    val actualResult = test.extractUrl("http://aws.amazon.com/console/home?state=hash&isauthcode=true&code=112")
    val expectedResult =s"protocol= http \n domain=aws.amazon.com \n path= /console/home \n params= Map(state -> hash, isauthcode -> true, code -> 112)"
    actualResult must equal(expectedResult)
  }


}
