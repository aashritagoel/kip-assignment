package com.knoldus

class Person(var firstName: String, var lastName: String) {

  //override def hashCode: Int = (firstName, lastName).##
  def equalContents(other: Any): Boolean = other match {
    case that: Person => this.firstName == that.firstName && this.lastName == that.lastName
    case _ => false
  }

  override def equals(other: Any): Boolean = other match {
    case that: Person => this.firstName == that.firstName && this.lastName == that.lastName
    case _ => false
  }
}