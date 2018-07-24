package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class PointSpec extends FunSuite with MustMatchers {

  val point = new Point(1, 2)
  val anonymousPoint = new Point(1, 1){override val y = 2}

  test("To check if point is equal to anonymous point"){
    val actualResult = point == anonymousPoint
    val expectedResult = true
    actualResult must equal(expectedResult)
  }

}
