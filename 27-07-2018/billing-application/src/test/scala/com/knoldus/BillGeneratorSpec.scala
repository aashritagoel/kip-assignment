package com.knoldus

import org.mockito.Mockito.when
import org.scalatest.mockito.MockitoSugar
import org.scalatest.FunSuite
import scala.collection.mutable

class BillGeneratorSpec extends FunSuite with MockitoSugar {

  val mockValidator: UserCart = mock[UserCart]
  val testBill: BillGenerator = new BillGenerator(mockValidator)
  val saridon = new MedicalItem("saridon", 23.0)
  val laptop = new GeneralItem("DELL", 40000.0, "laptop")


  test("Check if bill for the items in the cart is computed correctly") {
    when(mockValidator.totalBillForItem(saridon)).thenReturn(345.0)
    when(mockValidator.totalBillForItem(laptop)).thenReturn(82000.0)
    when(mockValidator.getCartItems).thenReturn(mutable.Map(saridon -> 12, laptop -> 2))
    val actualResult = testBill.netBillAmount()
    val expectedResult = 82345.0
    assert(actualResult === expectedResult)
  }
}