package edu.knoldus

import db.{ BooksRepository, DbConfiguration }
import dto.Book

//noinspection ScalaStyle
object Operation extends App with DbConfiguration{

  val booksRepository = new BooksRepository(config)

  booksRepository.init()
  booksRepository.insert(Book(Some(1), "b1", "p1", 2))
  println(booksRepository.findAll)


}

