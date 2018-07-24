package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class PointTransitiveSpec extends FunSuite with MustMatchers {

  val point = new PointTransitive(1, 2)
  val coloredPointRed = new ColoredPoint(1, 2, Color.Red)
  val coloredPointBlue = new ColoredPoint(1, 2, Color.Blue)

  test("To check if red point is equal to point"){
    val actualResult = coloredPointRed == point
    val expectedResult = false
    actualResult must equal(expectedResult)
  }

  test("To check if point is equal to blue point"){
    val actualResult = point == coloredPointBlue
    val expectedResult = false
    actualResult must equal(expectedResult)
  }

  test("To check if red point is equal to blue point"){
    val actualResult = coloredPointRed == coloredPointBlue
    val expectedResult = false
    actualResult must equal(expectedResult)
  }

}
