package com.knoldus

import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatest.FunSuite

//noinspection ScalaStyle
class UserCartSpec extends FunSuite with MockitoSugar {

  val mockValidator: Stock = mock[Stock]
  val testCart = new UserCart("Aashrita", mockValidator)
  val saridon = new MedicalItem("saridon", 23.0)
  val laptop = new GeneralItem("DELL", 40000.0, "laptop")

  test("Check if user can add item successfully in the cart"){
    when(mockValidator.validateQuantity(saridon, 23)).thenReturn(true)
    val actualResult = testCart.addItemInCart(saridon, 23)
    val expectedResult = Map(saridon -> 23)
    assert(actualResult === expectedResult)
  }

  test("Check if request to add quantity of an item greater the available stock is denied"){
    val testCart = new UserCart("Aashrita", mockValidator)
    when(mockValidator.validateQuantity(saridon, 51)).thenReturn(false)
    assertThrows[Exception]{
      testCart.addItemInCart(saridon, 51)
    }
  }

  test("Check if item quantity can be updated successfully in the cart"){
    val actualResult = testCart.editItemInCart(saridon, 12)
    val expectedResult = Map(saridon -> 12)
    assert(actualResult === expectedResult)
  }

  test("Check if item quantity can be removed successfully from the cart"){
    val actualResult = testCart.deleteItemFromCart(saridon)
    val expectedResult = Map()
    assert(actualResult === expectedResult)
  }

  test("Check if bill for a medical item is generated correctly"){
    when(mockValidator.validateQuantity(saridon, 15)).thenReturn(true)
    testCart.addItemInCart(saridon, 15)
    val actualResult = testCart.totalBillForItem(saridon)
    val expectedResult = 345.0
    assert(actualResult === expectedResult)
  }

  test("Check if bill for a general item is generated correctly"){
    when(mockValidator.validateQuantity(laptop, 2)).thenReturn(true)
    when(mockValidator.validateQuantity(saridon, 12)).thenReturn(true)
    testCart.addItemInCart(laptop, 2)
    testCart.addItemInCart(saridon, 12)
    val actualResult = testCart.totalBillForItem(laptop)
    val expectedResult = 82000.0
    assert(actualResult === expectedResult)
  }

  test("Check if items in the cart are displayed correctly"){
    val actualResult = testCart.getCartItems
    val expectedResult = Map(laptop -> 2, saridon -> 12)
    assert(actualResult === expectedResult)
  }

}
