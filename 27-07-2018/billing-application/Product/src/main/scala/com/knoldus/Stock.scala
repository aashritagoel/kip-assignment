package com.knoldus

import scala.collection.mutable

class Stock() {

  val itemList:mutable.Map[Item, Int] = mutable.Map()
  def addItem(item :Item, quantity :Int) :itemList.type = {
    itemList += (item -> quantity)
  }

  def getItems :Set[Item] = itemList.keys.toSet

  def updateStock(item :Item, quantity :Int) :mutable.Map[Item, Int] = {
    itemList(item) = quantity
    itemList
  }

  def getStock :mutable.Map[Item, Int] = itemList

  def validateQuantity(item :Item, quantity :Int) :Boolean= {
    if(itemList(item) >= quantity) true else false
  }
}
