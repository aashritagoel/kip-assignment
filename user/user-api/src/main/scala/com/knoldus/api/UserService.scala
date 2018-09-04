package com.knoldus.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

import scala.collection.mutable.ListBuffer


trait UserService extends Service {

  def getUser(id: Int): ServiceCall[NotUsed, User]

  def updateUser(id: Int, updatedName: String): ServiceCall[NotUsed, String]

  def deleteUser(id: Int): ServiceCall[NotUsed, String]

  def addUser(): ServiceCall[User, String]

  def viewUsers(): ServiceCall[NotUsed, UserRecord]

  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("user")
      .withCalls(
        restCall(Method.GET, "/api/getUser/:id", getUser _),
        restCall(Method.POST, "/api/addUser/", addUser _),
        restCall(Method.PUT, "/api/updateUser/:id/:name", updateUser _),
        restCall(Method.DELETE, "/api/deleteUser/:id", deleteUser _),
        restCall(Method.GET, "/api/viewUsers/", viewUsers _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}

case class User(id: Int, name: String)

object User {
  implicit val userFormat: Format[User] = Json.format
}


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

