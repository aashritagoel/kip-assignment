package com.knoldus

class Point(val x: Int, val y: Int) {

  override def hashCode: Int = (x, y).##
  override def equals(other: Any): Boolean = other match {
    case that: Point => this.x == that.x && this.y == that.y &&
      (this canEqual that)
    case _ => false
  }
  def canEqual(other: Any): Boolean = other.isInstanceOf[Point]
}
