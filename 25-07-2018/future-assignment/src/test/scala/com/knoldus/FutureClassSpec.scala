package com.knoldus

import scala.concurrent.Await
import scala.concurrent.duration._
import org.scalatest.{FunSuite, Matchers}
import java.io.File

class FutureClassSpec extends FunSuite with Matchers{

  val testClass = new FutureClass

  test("To check if all the files in Folder1 are listed correctly"){
    val actualResult = Await.result(testClass.findFilesInDirectory("src/main/Folder1"), 15.seconds)
    actualResult shouldBe List(new File("src/main/Folder1/File1"), new File("src/main/Folder1/Folder3/File3"), new File("src/main/Folder1/Folder3/File2"))
  }

  test("To check if all the files in src/test are listed correctly"){
    val actualResult = Await.result(testClass.findFilesInDirectory("src/test"), 15.seconds)
    actualResult shouldBe List(new File("src/test/scala/com/knoldus/FutureClassSpec.scala"))
  }

}
