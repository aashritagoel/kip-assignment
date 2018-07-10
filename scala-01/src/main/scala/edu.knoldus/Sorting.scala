package edu.knoldus

class Sorting {

  def insertionSort(list: List[Int]): List[Int] = {

    val listArray = list.toArray
    var j = 0;
    for(i <- (1 until listArray.length)){
      val key = listArray(i)
      j = i - 1;
      while(j >= 0 && listArray(j) > key) {
        listArray(j + 1) = listArray(j)
        j = j - 1
      }
      listArray(j + 1) = key
    }
    val sortedList = listArray.toList
    sortedList
  }

  def selectionSort(list: List[Int]): List[Int] = {
    val listArray = list.toArray

    def swap(lst: Array[Int], i: Int, j: Int) {
      val temp = lst(i)
      lst(i) = lst(j)
      lst(j) = temp
    }

   for(i <- 0 until listArray.length - 1){
     val minimum = listArray.slice(i, listArray.length).reduceLeft(_ min _)
     val minIndex = listArray.indexWhere( _ == minimum)
     swap(listArray, i, minIndex)
   }
    val sortedList = listArray.toList
    sortedList
  }

  def bubbleSort(list: List[Int]): List[Int] = {
    val listArray = list.toArray
      for (i <- 0 until listArray.length - 1; j <- 0 until listArray.length - 1 - i) {
        if (listArray(j) > listArray(j + 1)) {
          val temp = listArray(j)
          listArray(j) = listArray(j + 1)
          listArray(j + 1) = temp
        }
      }
    val sortedList = listArray.toList
    sortedList
  }

}
