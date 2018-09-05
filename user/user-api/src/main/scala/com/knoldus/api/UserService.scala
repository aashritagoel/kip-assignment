package com.knoldus.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}


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
        restCall(Method.GET, "/user/details/:id", getUser _),
        restCall(Method.POST, "/user/add/", addUser _),
        restCall(Method.PUT, "/user/update/:id/:name", updateUser _),
        restCall(Method.DELETE, "/user/delete/:id", deleteUser _),
        restCall(Method.GET, "/user/view/", viewUsers _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}





