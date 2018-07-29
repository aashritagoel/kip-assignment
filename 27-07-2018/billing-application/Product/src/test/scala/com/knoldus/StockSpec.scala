package com.knoldus

import org.scalatest.{FunSuite, MustMatchers}

//noinspection ScalaStyle
class StockSpec extends FunSuite with MustMatchers {

  val testStock = new Stock
  val saridon = new MedicalItem("Saridon", 23.0)
  val laptop = new GeneralItem("DELL", 40000.0, "laptop")


  test("Check if medical item is added successfully in the stock"){
    val actualResult = testStock.addItem(saridon, 50)
    val expectedResult = Map(saridon -> 50)
    actualResult must equal(expectedResult)
  }

  test("Check if general item is also added successfully in the stock"){
    val actualResult = testStock.addItem(laptop, 50)
    val expectedResult = Map(saridon -> 50, laptop -> 50)
    actualResult must equal(expectedResult)
  }

  test("Check if item quantity can be updated successfully in the stock"){
    val actualResult = testStock.updateStock(laptop, 43)
    val expectedResult = Map(saridon -> 50, laptop -> 43)
    actualResult must equal(expectedResult)
  }

  test("Check if item quantity requested by user is available in the stock"){
    val actualResult = testStock.validateQuantity(saridon, 3)
    val expectedResult = true
    actualResult must equal(expectedResult)
  }

  test("Check if huge item quantity requested by user is available in the stock"){
    val actualResult = testStock.validateQuantity(saridon, 54)
    val expectedResult = false
    actualResult must equal(expectedResult)
  }

  test("Check if user can see the available items and quantity in the stock"){
    val actualResult = testStock.getStock
    val expectedResult = Map(saridon -> 50,laptop -> 43)
    actualResult must equal(expectedResult)
  }

  test("Check if user can see the available items in the stock"){
    val actualResult = testStock.getItems
    val expectedResult = Set(saridon,laptop)
    actualResult must equal(expectedResult)
  }

}
