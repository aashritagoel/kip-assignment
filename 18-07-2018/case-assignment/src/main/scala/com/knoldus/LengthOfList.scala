package com.knoldus

class LengthOfList {
  def findLength(list :List[Int]) :Int ={
    list match {
      case Nil => 0
      case x :: rest => findLength( rest ) + 1
    }
  }
}
