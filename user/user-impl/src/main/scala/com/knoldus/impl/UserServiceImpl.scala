package com.knoldus.impl

import akka.NotUsed
import com.knoldus.api.{User, UserRecord, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def getUser(id: Int): ServiceCall[NotUsed, User] = ServiceCall { _ => {
    val requiredUser = UserRecord.userList.find(user => user.id == id)
    val user = requiredUser.getOrElse(User(-1, ""))
    Future.successful(user)
  }
  }

  private def getUserById(id: Int): Option[User] = {
    UserRecord.userList.find(user => user.id == id)
  }

  override def addUser(): ServiceCall[User, String] = ServiceCall { user => {
    val isUserPresent = getUserById(user.id)
    val addStatus = isUserPresent match {
      case Some(_) => "User already exists. Make sure the id is unique"
      case None =>
        UserRecord.userList += user
        s"User(${user.id}, ${user.name}) added Successfully"
    }
    Future.successful(addStatus)
  }
  }

  override def deleteUser(id: Int): ServiceCall[NotUsed, String] = ServiceCall { _ => {
    val isUserPresent = getUserById(id)
    val deleteStatus = isUserPresent match {
      case Some(user) => UserRecord.userList -= user
        s"User(${user.id}, ${user.name}) deleted successfully"
      case None => "User doesn't exist"
    }
    Future.successful(deleteStatus)
  }
  }

  override def updateUser(id: Int, updatedName: String): ServiceCall[NotUsed, String] = ServiceCall { _ => {
    val isUserPresent = getUserById(id)
    val updateStatus = isUserPresent match {
      case Some(user) => UserRecord.userList -= user
        UserRecord.userList += User(user.id, updatedName)
        s"User(${user.id}, $updatedName) updated successfully"
      case None => "User doesn't exist"
    }
    Future.successful(updateStatus)
  }
  }

  override def viewUsers(): ServiceCall[NotUsed, UserRecord] = ServiceCall { _ =>
    Future.successful(UserRecord(UserRecord.userList))
  }

}
