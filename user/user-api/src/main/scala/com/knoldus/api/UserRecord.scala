package com.knoldus.api

import play.api.libs.json.{Format, Json}

import scala.collection.mutable.ListBuffer

case class UserRecord(userList: ListBuffer[User])

object UserRecord {
  implicit val userRecordFormat: Format[UserRecord] = Json.format
  val userList: ListBuffer[User] = ListBuffer(User(1, "Aashrita"),
    User(2, "Mark"),
    User(3, "John"),
    User(4, "Rose"),
    User(5, "Merry")
  )
}