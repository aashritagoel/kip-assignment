package com.knoldus.api

import play.api.libs.json.{Format, Json}

case class User(id: Int, name: String)

object User {
  implicit val userFormat: Format[User] = Json.format
}
