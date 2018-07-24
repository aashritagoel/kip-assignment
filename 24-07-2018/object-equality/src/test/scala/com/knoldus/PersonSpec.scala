package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class PersonSpec extends FunSuite with MustMatchers {

  val personOne = new Person("Ashrita", "Goel")
  val collection = scala.collection.mutable.HashSet(personOne)
  personOne.firstName = "Aashrita"
  val personTwo = new Person("Aashrita", "Goel")

  test("To check if personOne is present in same bucket of HashSet"){
    val actualResult = collection contains(personOne)
    val expectedResult = true
    actualResult must equal(expectedResult)
  }

  test("To check if personOne is present in collection"){
    val actualResult = collection.iterator contains(personOne)
    val expectedResult = true
    actualResult must equal(expectedResult)
  }

  test("To check if personOne is equal to personTwo"){
    val actualResult = personOne == personTwo
    val expectedResult = true
    actualResult must equal(expectedResult)
  }

}
