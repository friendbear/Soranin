package AccountAndMoney

/**
  * Created by kumagai on 2016/05/23.
  */
class Money(value:Int, currency:String) {
  val v = value
  val c = currency

  override def toString: String = {
    "Money (value:" + v + " currnecy: " + c + ")"
  }
}


class Account{
  var balance:List[Money] = List()
  def deposit(money: Money){
    balance = balance ::: List(money)
  }

  def getBalance(currency:String) :List[Money] = {
    balance.filter(p => p.c eq currency)
  }
  def sum(): Int ={
    getBalance("JPY").foreach(x => println(x.v))
    0
  }
}
object Main {
    def main(args: Array[String]) {
      val sorataka = new Account()
      val tomohiro = new Account()

      sorataka deposit(new Money(100, "JPY"))
      sorataka deposit(new Money(100, "JPY"))
      sorataka deposit(new Money(100, "USD"))
      sorataka deposit(new Money(100, "JPY"))
      sorataka deposit(new Money(100, "JPY"))
      tomohiro deposit(new Money(100000000, "JPY"))

      println(sorataka sum())
    }
  }
