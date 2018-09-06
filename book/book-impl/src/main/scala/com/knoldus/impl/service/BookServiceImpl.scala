package com.knoldus.impl.service

import akka.{Done, NotUsed}
import com.knoldus.api.{Book, BookService}
import com.knoldus.impl.eventsourcing._
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{PersistentEntityRef, PersistentEntityRegistry}

import scala.concurrent.ExecutionContext

class BookServiceImpl(persistentEntityRegistry: PersistentEntityRegistry, bookRepository: BookRepository)
                     (implicit ec: ExecutionContext) extends BookService {

  override def createBook(isbn: String, name: String, author: String, genre: String): ServiceCall[NotUsed, String] = {
    ServiceCall{_ =>
      val book = Book(isbn, name, author, genre)
      ref(isbn).ask(CreateBookCommand(book)).map {
        case Done => s"${book.name} has been added."
      }
    }
  }

  override def updateBook(isbn: String, name: String, author: String, genre: String): ServiceCall[NotUsed, String] = {
    ServiceCall{_ =>
      val book = Book(isbn, name, author, genre)
      ref(isbn).ask(UpdateBookCommand(book)).map {
        case Done => s"Book with isbn ${book.isbn} has been updated."
      }
    }
  }

  override def getBookById(isbn: String): ServiceCall[NotUsed, String] =  ServiceCall { _ =>
    ref(isbn).ask(GetBookCommand(isbn)).map(book =>
      s"Book for isbn:$isbn is ${book.name}")
  }

  override def getBookByName(name: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    bookRepository.getBookByName(name).map(book =>
      s"Book for name:$name has id: ${book.get.isbn} and author: ${book.get.author}"
    )
  }

  override def getBookByAuthor(author: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    bookRepository.getBookByAuthor(author).map(book =>
      s"Book for name:$author has id: ${book.get.isbn} and name: ${book.get.name}"
    )
  }

  override def deleteBook(isbn: String): ServiceCall[NotUsed, String] = ServiceCall{_ =>
    ref(isbn).ask(DeleteBookCommand(isbn)).map(_ =>
      s"Book with isbn:$isbn has been deleted")
  }

  def ref(isbn: String): PersistentEntityRef[BookCommand[_]] = {
    persistentEntityRegistry
      .refFor[BookEntity](isbn)
  }

}