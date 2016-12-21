package cop18.mutableobject

/**
  * Created by kumagai on 2016/12/22.
  */
class MutableObject {

}

class BankAccount(initialDeposit: Int = 0) {
  private var bal: Int = initialDeposit
  def balance: Int = bal
  def deposit(amount: Int) = {
    require(amount > 0)
    bal += amount
  }
  def withdraw(amount: Int): Boolean = {
    if (amount > bal) false
    else {
      bal -= amount
      true
    }
  }
}

object BankAccountApp extends App {
  val account = new BankAccount
  account deposit 100000
  account withdraw 90000 ensuring(_ == false, "引き出し失敗")
  account withdraw 30000 ensuring(_ == false, "引き出し失敗")
}

class Keyed {
  def computeKey: Int = 10
}
class MemoKeyed extends Keyed {
  var keyCache: Option[Int] = None

  override def computeKey: Int = {
    if (!keyCache.isDefined) keyCache = Some(super.computeKey)
    keyCache get
  }
}
