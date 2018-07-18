package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class MaximumElementSpec extends FunSuite with MustMatchers{

  val testMaximum = new MaximumElement

  test("Checking for valid list"){
    val a = List(56, 78, 2, 10, 15, 54, -6, 11, 17, 13)
    val actualResult = testMaximum.findMaximum(a)
    val expectedResult = Some(78)
    actualResult must equal(expectedResult)
  }

  test("Checking for empty list"){
    val a = List()
    val actualResult = testMaximum.findMaximum(a)
    val expectedResult = None
    actualResult must equal(expectedResult)
  }

  test("Checking for list of 2 elements"){
    val a = List(5, 10)
    val actualResult = testMaximum.findMaximum(a)
    val expectedResult = Some(10)
    actualResult must equal(expectedResult)
  }


  }