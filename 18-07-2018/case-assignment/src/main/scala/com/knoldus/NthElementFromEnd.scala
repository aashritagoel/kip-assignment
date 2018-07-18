package com.knoldus

class NthElementFromEnd {

  def findNthElement(n :Int, list :List[Int]) :Option[Int] ={
    list match {
      case x :List[Int] if x.length == n => Some(list.head)
      case x :: tail => findNthElement(n, tail)
      case _ => None
    }

  }

}
