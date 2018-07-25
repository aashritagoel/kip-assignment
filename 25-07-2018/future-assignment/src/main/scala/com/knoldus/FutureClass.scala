package com.knoldus

import java.io.File
import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FutureClass {

  def findFilesInDirectory(dir: String): Future[List[File]] = Future {
    val directory = List(new File(dir))
    getListOfFiles(directory)

  }

  @tailrec
  private def getListOfFiles(dir: List[File], resultFile: List[File]=Nil): List[File] = {

    dir match {
      case head :: tail => getListOfFiles(tail ::: head.listFiles.filter(_.isDirectory).toList,
                           resultFile ::: head.listFiles.filter(_.isFile).toList)
      case _ => resultFile

    }
  }
}



