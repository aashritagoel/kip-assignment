package com.knoldus

trait BillCalculation {

  def priceExcludingTax(costPrice :Double, quantity :Int): Double = costPrice * quantity

  def totalBillForItem(item: Item) :Double

}

