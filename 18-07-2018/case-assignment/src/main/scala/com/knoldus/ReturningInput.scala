package com.knoldus

case class Pet(name :String, animal :String)

class ReturningInput {

  def returnInputBack(input :Any) :String ={
    input match{
      case s :String => s"You gave me this string: $s"
      case i:Int => s"Thanks for the int: $i"
      case f:Float => s"Thanks for the float: $f"
      case arrInt:Array[Int] => s"Thanks for the array of integers: ${arrInt.mkString(",")}"
      case arrString:Array[String] => s"Thanks for the array of strings: ${arrString.mkString(",")}"
      case caseObj:Pet => s"${caseObj.name} is a good ${caseObj.animal}"
      case listAny:List[Any] => s"Thanks for the list of any: ${listAny.toString}"
      case m:Map[_, _] => s"Thanks for the map: ${m.toString}"
    }
  }
}

