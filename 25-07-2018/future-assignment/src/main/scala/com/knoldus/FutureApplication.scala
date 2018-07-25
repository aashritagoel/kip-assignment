package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FutureApplication extends App {

  val futureObject = new FutureClass
  val result = futureObject.findFilesInDirectory("src/main/Folder1")
  result onComplete {
    case Success(ans) => println(ans)
    case Failure(e) => println(e.getMessage)
  }
  val sleepingTime = 10000
  Thread.sleep(sleepingTime)
}

