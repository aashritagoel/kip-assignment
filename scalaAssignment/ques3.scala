object thirdProgram extends App{

	def findProduct(num: Int) : Int = {
		if(num == 1)
			num
		else
			findProduct(num-1)*num
	}

	def findSum(num: Int) : Int = {
		if(num/10 < 1)
			 (num % 10)
		else
			findSum(num/10) + (num % 10)
	}

	println("Enter n: ")
	val n = scala.io.StdIn.readInt()
	val product = findProduct(n)
	val sum=findSum(product)
	println(s"Product = $product")
	println(s"Sum = $sum")

}
