package com.knoldus

class GeneralItem(
                   override val name :String,
                   override val costPrice :Double,
                   override val category: String
                 ) extends Item(name, costPrice, category){

  val taxRate :Double = 2.5

}
