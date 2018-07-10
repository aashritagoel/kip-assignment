object programFirst extends App{
	val numbers = List(10, 20, 30, 40, 50)
	for(i <- 0 until numbers.size) println(s"$i = ${numbers(i)}")
}
