package edu.knoldus

import org.scalatest.WordSpec

class SortingTest extends WordSpec {

  val sorting: Sorting = new Sorting

  val sortedList: List[Int] = List(1, 2, 3, 4, 5, 9, 11)
  val unSortedList: List[Int] = List(2, 3, 1, 5, 4, 58, 23,11)
  val reversedList: List[Int] = List(5, 4, 3, 2, 11, 3, 80)
  val listWithNegativeIntegers: List[Int] = List(1, -1, 2, -2)

  "Insertion Sort" should {

    "sort sorted list" in {
      assert(sorting.insertionSort(sortedList) == sortedList.sortBy(identity))
    }

    "sort unsorted list" in {
      assert(sorting.insertionSort(unSortedList) == unSortedList.sortBy(identity))
    }

    "sort reversed list" in {
      assert(sorting.insertionSort(reversedList) == reversedList.sortBy(identity))
    }

    "sort list with negative integers" in {
      assert(sorting.insertionSort(listWithNegativeIntegers) == listWithNegativeIntegers.sortBy(identity))
    }

  }

  "Selection Sort" should {

    "sort sorted list" in {
      assert(sorting.selectionSort(sortedList) == sortedList.sortBy(identity))
    }

    "sort unsorted list" in {
      assert(sorting.selectionSort(unSortedList) == unSortedList.sortBy(identity))
    }

    "sort reversed list" in {
      assert(sorting.selectionSort(reversedList) == reversedList.sortBy(identity))
    }

    "sort list with negative integers" in {
      assert(sorting.selectionSort(listWithNegativeIntegers) == listWithNegativeIntegers.sortBy(identity))
    }

  }

  "Bubble Sort" should {

    "sort sorted list" in {
      assert(sorting.bubbleSort(sortedList) == sortedList.sortBy(identity))
    }

    "sort unsorted list" in {
      assert(sorting.bubbleSort(unSortedList) == unSortedList.sortBy(identity))
    }

    "sort reversed list" in {
      assert(sorting.bubbleSort(reversedList) == reversedList.sortBy(identity))
    }

    "sort list with negative integers" in {
      assert(sorting.bubbleSort(listWithNegativeIntegers) == listWithNegativeIntegers.sortBy(identity))
    }

  }

}
