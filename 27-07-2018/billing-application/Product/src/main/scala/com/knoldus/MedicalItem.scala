package com.knoldus

class MedicalItem(
                   override val name :String,
                   override val costPrice :Double,
                   override val category: String = "medicines"
                 ) extends Item(name, costPrice, category){

  val taxRate :Double = 0
}
