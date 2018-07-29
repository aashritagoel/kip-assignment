package com.knoldus

import scala.collection.mutable

class UserCart(val user :String, stock :Stock) extends BillCalculation {

  val cartDetails :mutable.Map[Item, Int] = mutable.Map.empty

  def addItemInCart(item :Item, quantity :Int) :mutable.Map[Item, Int]= {
    if (stock.validateQuantity(item, quantity)) {
      cartDetails += (item -> quantity)
    }
    else{
      throw new Exception("Not enough stock")
    }
  }

  def deleteItemFromCart(item: Item) :mutable.Map[Item, Int] = {
    cartDetails -= item
  }

  def editItemInCart(item :Item, quantity :Int) :mutable.Map[Item, Int] = {
    cartDetails(item) = quantity
    cartDetails
  }

  def getCartItems :mutable.Map[Item, Int] = cartDetails

  override def totalBillForItem(item: Item): Double = {
    val quantity = cartDetails(item)
    priceExcludingTax(item.getPrice, quantity) * (1 + item.taxRate / 100)
  }

}
