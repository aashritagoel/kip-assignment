package com.knoldus

import scala.annotation.tailrec
import scala.collection.mutable

class BillGenerator(cart :UserCart) {

  def netBillAmount() :Double = {
    @tailrec
    def totalAmount(cartList: mutable.Map[Item, Int], item: List[Item], total: Double = 0.0): Double ={
      item match {
        case first :: rest => totalAmount(cartList, rest, total + cart.totalBillForItem(first))
        case _ => total
      }
    }
    val cartDetails = cart.getCartItems
    totalAmount(cartDetails, cartDetails.keys.toList)
  }
}
