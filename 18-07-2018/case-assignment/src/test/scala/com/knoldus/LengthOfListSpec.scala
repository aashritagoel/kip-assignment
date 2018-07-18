package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class LengthOfListSpec extends FunSuite with MustMatchers {
  val testLength = new LengthOfList

  test("Checking for valid list"){
    val a = List(56, 78, 2, 10, 15, 54, -6, 11, 17, 13)
    val actualResult = testLength.findLength(a)
    val expectedResult = 10
    actualResult must equal(expectedResult)
  }

  test("Checking for empty list"){
    val a = List()
    val actualResult = testLength.findLength(a)
    val expectedResult = 0
    actualResult must equal(expectedResult)
  }

  test("Checking for list of 1 element"){
    val a = List(5)
    val actualResult = testLength.findLength(a)
    val expectedResult = 1
    actualResult must equal(expectedResult)
  }

}
