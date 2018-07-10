class BankAccount(var balance: Int){
	
	//private var balance = 0
	
	def this() = this(1000)
	
	def getBalance = println(s"Balance = $balance")
	
	def deposit(amount: Int) = balance += amount
	def withdraw(amount: Int) = if(amount < balance) balance -= amount else println("No sufficient balance. Transaction declined")
}

object Test extends App{
	
	//println("Press 1 to deposit and 2 to withdraw: ")
	//val choice = scala.io.StdIn.readInt()
	println("Enter amount to deposit: ")
	val depositAmount = scala.io.StdIn.readInt()
	val account = new BankAccount(1000)
	account.deposit(depositAmount)
	account.getBalance
	println("Enter amount to withdraw: ")
	val withdrawalAmount = scala.io.StdIn.readInt()
	account.withdraw(withdrawalAmount)
	account.getBalance
	
}
