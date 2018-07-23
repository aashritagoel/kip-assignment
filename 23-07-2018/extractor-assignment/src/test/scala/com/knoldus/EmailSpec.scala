package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class EmailSpec extends FunSuite with MustMatchers {

  val test = new Email
  test("Checking for valid email"){
    val actualResult = test.regexEmail("knol@knoldus.com")
    val expectedResult = Some(("knol","knoldus.com"))
    actualResult must equal(expectedResult)
  }
  test("Checking for invalid email"){
    val actualResult = test.regexEmail("knolknoldus.com")
    val expectedResult = None
    actualResult must equal(expectedResult)
  }

}
