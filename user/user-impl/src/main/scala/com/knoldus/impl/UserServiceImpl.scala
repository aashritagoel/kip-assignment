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

  private def isUserPresent(id: Int): Boolean = {
    UserRecord.userList.exists(user => user.id == id)
  }

  override def addUser(): ServiceCall[User, String] = ServiceCall { user => {
    if (isUserPresent(user.id)) {
      Future.successful("User already exists. Make sure the id is unique")
    } else {
      UserRecord.userList += user
      Future.successful("User added Successfully")
    }
  }
  }

  override def deleteUser(id: Int): ServiceCall[NotUsed, String] = ServiceCall { _ => {
    if (isUserPresent(id)) {
      UserRecord.userList -= UserRecord.userList.find(user => user.id == id).get
      Future.successful("User deleted Successfully")
    } else {
      Future.successful("User doesn't exist")
    }
  }
  }

  override def updateUser(id: Int, updatedName: String): ServiceCall[NotUsed, String] = ServiceCall { _ => {
    if (isUserPresent(id)) {
      val userToBeUpdated = UserRecord.userList.find(user => user.id == id).get
      UserRecord.userList -= userToBeUpdated
      UserRecord.userList += User(userToBeUpdated.id, updatedName)
      Future.successful("User updated Successfully")
    } else {
      Future.successful("User doesn't exist")
    }
  }
  }

  override def viewUsers(): ServiceCall[NotUsed, UserRecord] = ServiceCall { _ =>
    Future.successful(UserRecord(UserRecord.userList))
  }

}
