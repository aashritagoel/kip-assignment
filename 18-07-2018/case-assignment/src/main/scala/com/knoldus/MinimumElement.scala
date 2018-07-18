package com.knoldus

class MinimumElement {
  def findMinimum(list :List[Int]) :Option[Int] ={
    list match {
      case Nil => None
      case List(x: Int) => Some(x)
      case x :: y :: rest => findMinimum( (if (x > y) y else x) :: rest )
    }
  }
}
