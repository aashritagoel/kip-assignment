package com.knoldus.impl.service

import com.knoldus.api.Book
import com.knoldus.impl.eventsourcing._
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

import scala.collection.immutable

object BookSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: immutable.Seq[JsonSerializer[_]] = immutable.Seq(
    JsonSerializer[Book],
    JsonSerializer[CreateBookCommand],
    JsonSerializer[GetBookCommand],
    JsonSerializer[UpdateBookCommand],
    JsonSerializer[DeleteBookCommand],
    JsonSerializer[BookCreated],
    JsonSerializer[BookUpdated],
    JsonSerializer[BookDeleted],
    JsonSerializer[BookState]
  )
}