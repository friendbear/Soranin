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

class Time {
  var hour = 12
  var minute = 0
}
// 上の実装は以下の実装と同じ
class TimeDef {
  private[this] var h = 12
  private[this] var m = 0
  def hour: Int = h
  def hour_ = (x: Int) => {
    require(0 <= x && x < 24)
    h = x
  }
  def minite: Int = m
  def minite_ =(x: Int) => {
    require(0 <= x && x < 60)
    m = x
  }
}

/** 摂氏と華氏 を表すクラス
  *
  */
class Thermometer {
  var celsius: Float = _
  def fahrenheit = celsius * 9/5 + 32
  def fahrenheit_ =(f: Float) => {
    celsius = (f - 32) * 5/9
  }
}
