package com.knoldus

abstract class Item(val name :String, val costPrice :Double, val category :String){

  def taxRate :Double
  def getPrice :Double = this.costPrice
}


/*
object Laptop extends Item("Dell", "Electronics", 40000)
object Apple extends Item("Apple", "Fruit", 400)
object Saridon extends Item("Saridon", "Medicine", 40)
object Crocin extends Item("Crocin", "Medicine", 70)
object Nike extends Item("Nike", "Footware", 8000)
*/




