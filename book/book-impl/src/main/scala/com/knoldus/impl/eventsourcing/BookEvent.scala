package com.knoldus.impl.eventsourcing

import com.knoldus.api.Book
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventShards, AggregateEventTag, AggregateEventTagger}
import play.api.libs.json.{Format, Json}

trait BookEvent extends AggregateEvent[BookEvent] {
  override def aggregateTag: AggregateEventTagger[BookEvent] = BookEvent.Tag
}

object BookEvent {
  val numberOfShards = 4
  val Tag: AggregateEventShards[BookEvent] = AggregateEventTag.sharded[BookEvent](numberOfShards)
}

case class BookCreated(book: Book) extends BookEvent

object BookCreated{
  implicit val format: Format[BookCreated] = Json.format[BookCreated]
}

case class BookUpdated(book: Book) extends BookEvent

object BookUpdated{
  implicit val format: Format[BookUpdated] = Json.format[BookUpdated]
}

case class BookDeleted(isbn: String) extends BookEvent

object BookDeleted{
  implicit val format: Format[BookDeleted] = Json.format[BookDeleted]
}