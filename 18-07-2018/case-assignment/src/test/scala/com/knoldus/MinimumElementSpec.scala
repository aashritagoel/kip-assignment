package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}


class MinimumElementSpec extends FunSuite with MustMatchers {

  val testMinimum = new MinimumElement

  test("Checking for valid list"){
    val a = List(56, 78, 2, 10, 15, 54, -6, 11, 17, 13)
    val actualResult = testMinimum.findMinimum(a)
    val expectedResult = Some(-6)
    actualResult must equal(expectedResult)
  }

  test("Checking for empty list"){
    val a = List()
    val actualResult = testMinimum.findMinimum(a)
    val expectedResult = None
    actualResult must equal(expectedResult)
  }

  test("Checking for list of 2 elements"){
    val a = List(5, 10)
    val actualResult = testMinimum.findMinimum(a)
    val expectedResult = Some(5)
    actualResult must equal(expectedResult)
  }


}
