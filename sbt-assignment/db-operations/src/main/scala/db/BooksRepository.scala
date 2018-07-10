package db

import dto.Book
import slick.basic.DatabaseConfig
import slick.dbio.DBIOAction
import slick.jdbc.H2Profile

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Slick
  */
class BooksRepository(val config: DatabaseConfig[H2Profile]) extends Db with BookTable {

  import config.profile.api._

  import scala.concurrent.ExecutionContext.Implicits.global

  def init(): Unit =
    Await.result(
      db.run(DBIOAction.seq(books.schema.create)),
      Duration.Inf
    )

  def drop(): Unit =
    Await.result(
      db.run(DBIOAction.seq(books.schema.drop)),
      Duration.Inf
    )

  def insert(book: Book): Book =
    Await.result(
      db.run(books returning books.map(_.id) += book).map(id => book.copy(id = Some(id))),
      Duration.Inf
    )

  def find(id: Int): Option[Book] =
    Await.result(
      db.run((for (book <- books if book.id === id) yield book).result.headOption),
      Duration.Inf
    )

  def findAll: List[Book] = Await.result(
    db.run((for (book <- books) yield book).result),
    Duration.Inf
  ).toList

  def update(id: Int, name:String, publisher: String, rating: Int): Boolean = {
    val query = for (book <- books if book.id === id)
      yield (book.name, book.publisher, book.rating)
    Await.result(
      db.run(query.update(name, publisher, rating)) map { _ > 0 },
      Duration.Inf
    )
  }

  def delete(id: Int): Boolean =
    Await.result(
      db.run(books.filter(_.id === id).delete) map { _ > 0 },
      Duration.Inf
    )

}
