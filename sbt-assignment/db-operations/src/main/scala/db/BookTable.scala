package db

import dto.Book
import slick.lifted.ProvenShape

/**
  * Slick
  */
trait BookTable { this: Db =>

  import config.profile.api._

  class BookOperation(tag: Tag) extends Table[Book](tag, "BOOKS") {
    def id: Rep[Int] = column[Int]("ID", O.PrimaryKey,  O.AutoInc)
    def name: Rep[String] = column[String]("NAME")
    def publisher: Rep[String] = column[String]("PUBLISHER")
    def rating: Rep[Int] = column[Int]("RATING")
    def * : ProvenShape[Book] = (id.?, name, publisher, rating) <> (Book.tupled, Book.unapply)
  }

  val books = TableQuery[BookOperation]

}
