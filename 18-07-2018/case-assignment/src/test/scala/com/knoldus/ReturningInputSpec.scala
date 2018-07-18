package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class ReturningInputSpec extends FunSuite with MustMatchers {

  val testElement = new ReturningInput

  test("Test for string"){
    val a = "Aashrita"
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "You gave me this string: Aashrita"
    actualResult must equal(expectedResult)
  }

  test("Test for integer"){
    val a = 90
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the int: 90"
    actualResult must equal(expectedResult)
  }

  test("Test for float"){
    val a = 90.7f
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the float: 90.7"
    actualResult must equal(expectedResult)
  }

  test("Test for array of Integers"){
    val a = Array(4, 5, 10, 4)
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the array of integers: 4,5,10,4"
    actualResult must equal(expectedResult)
  }

  test("Test for array of Strings"){
    val a = Array("scala", "akka", "spark" )
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the array of strings: scala,akka,spark"
    actualResult must equal(expectedResult)
  }

  test("Test for case classes"){
    val a = Pet("Ginger", "dog")
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Ginger is a good dog"
    actualResult must equal(expectedResult)
  }

  test("Test for list of any"){
    val a = List(4, 5, "Hi")
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the list of any: List(4, 5, Hi)"
    actualResult must equal(expectedResult)
  }

  test("Test for Map"){
    val a = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val actualResult = testElement.returnInputBack(a)
    val expectedResult = "Thanks for the map: Map(a -> 1, b -> 2, c -> 3)"
    actualResult must equal(expectedResult)
  }
}
