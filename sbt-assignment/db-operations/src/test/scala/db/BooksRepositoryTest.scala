package db

import org.scalatest.{ BeforeAndAfterEach, FunSuite }

/**
  * Scala Test
  */
class BooksRepositoryTest extends FunSuite with DbConfiguration  with BeforeAndAfterEach {

  val books = new BooksRepository(config)

  override def beforeEach(){
    books.init()
  }

  override def afterEach() {
    books.drop()
  }

  test("Get all books"){
    assert(books.findAll === Nil)
  }


}
