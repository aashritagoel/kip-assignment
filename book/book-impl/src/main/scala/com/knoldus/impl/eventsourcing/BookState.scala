package com.knoldus.impl.eventsourcing

import com.knoldus.api.Book
import play.api.libs.json.{Format, Json}

case class BookState(book: Option[Book], timeStamp: String)

object BookState {
  implicit val format: Format[BookState] = Json.format
}