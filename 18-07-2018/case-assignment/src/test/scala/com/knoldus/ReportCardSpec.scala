package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

class ReportCardSpec extends FunSuite with MustMatchers {
//212, 192, 271
  val listOfStudents: List[Student] = List(
    Student(1, "Bob", "11", "A"),
    Student(2, "Dab", "10", "C"),
    Student(3, "Tab", "10", "B"),
    Student(4, "Cab", "9", "S")
  )

  val listOfStudents2: List[Student] = List(
    Student(1, "Bob", "11", "A"),
    Student(2, "Dab", "10", "C"),
  )

  val listOfScoreCard: List[ScoreCard] = List(
    ScoreCard(1, 88.0f, 45.0f, 79.0f),
    ScoreCard(2, 67.0f, 80.0f, 45.0f),
    ScoreCard(3, 99.0f, 87.0f, 85.0f)
  )

  val testResult = new ReportCard

  test("Test for finding three toppers"){
    val actualResult = testResult.findFirstThreeToppers(listOfStudents, listOfScoreCard)
    val expectedResult = List((3,"Tab"), (1,"Bob"), (2,"Dab"))
    actualResult must equal(expectedResult)
  }

  test("Test for finding subject1 topper"){
    val actualResult = testResult.findSubjectTopper("subject1", listOfStudents, listOfScoreCard)
    val expectedResult = (3,"Tab",99.0,87.0,85.0,271.0)
    actualResult must equal(expectedResult)
  }

  test("Test for finding subject2 topper"){
    val actualResult = testResult.findSubjectTopper("subject2", listOfStudents, listOfScoreCard)
    val expectedResult = (3,"Tab",99.0,87.0,85.0,271.0)
    actualResult must equal(expectedResult)
  }

  test("Test for finding subject3 topper"){
    val actualResult = testResult.findSubjectTopper("subject3", listOfStudents, listOfScoreCard)
    val expectedResult = (3,"Tab",99.0,87.0,85.0,271.0)
    actualResult must equal(expectedResult)
  }

  test("Test for displaying marksheet"){
    val actualResult = testResult.displayMarksheet(1, listOfStudents, listOfScoreCard)
    val expectedResult ="ID : 1 " +
                        " Name : Bob " +
                        " MARKS : " +
                        " SUBJECTS : " +
                        " SUBJECT1 : 88.0" +
                        " SUBJECT2 : 45.0" +
                        " SUBJECT3 : 79.0" +
                        " TOTAL MARKS : 212.0"

    actualResult must equal(expectedResult)
  }

  test("Test for find three topper with list of students having 2 students"){
    assertThrows[IllegalArgumentException]{
      testResult.findFirstThreeToppers(listOfStudents2, listOfScoreCard)
    }
  }
}
