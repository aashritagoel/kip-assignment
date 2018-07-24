package com.knoldus

class PointTransitive(val x: Int, val y: Int) {
  override def hashCode: Int = (x, y).##
  override def equals(other: Any): Boolean = other match {
    case that: Point =>
      this.x == that.x && this.y == that.y &&
        this.getClass == that.getClass
    case _ => false
  }
}