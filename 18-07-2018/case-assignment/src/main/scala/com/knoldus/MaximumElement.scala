package com.knoldus

class MaximumElement {

  def findMaximum(list :List[Int]) :Option[Int] ={
    list match {
      case Nil => None
      case List(x: Int) => Some(x)
      case x :: y :: rest => findMaximum( (if (x > y) x else y) :: rest )
    }
  }

}
