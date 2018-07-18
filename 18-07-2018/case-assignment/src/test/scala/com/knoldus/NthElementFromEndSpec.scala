package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class NthElementFromEndSpec extends FunSuite with MustMatchers {
  val testElement = new NthElementFromEnd

  test("Checking for valid list"){
    val a = List(56, 78, 2, 10, 15, 54, -6, 11, 17, 13)
    val actualResult = testElement.findNthElement(6, a)
    val expectedResult = Some(15)
    actualResult must equal(expectedResult)
  }

  test("Checking for invalid value of n"){
    val a = List(3, 5)
    val actualResult = testElement.findNthElement(5, a)
    val expectedResult = None
    actualResult must equal(expectedResult)
  }

}
