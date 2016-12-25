package cop20.abstractmembers

/**
  * Created by kumagai on 2016/12/25.
  */

/** Currencyのインスタンスは、ドル、ユーロ、円などの通貨を
  * 単位とする金額を表している。
  * この金額は計算に使えるものでなければならない
  */

/** 不完全な実装、Dollar + Euro ができてはまずい
  *
  */
package case1 {
  class CurrencyA {
    val amount: Long
    def designation: String

    override def toString: String = amount + " " + designation
    def + (that: CurrencyA): CurrencyA
    def * (x: Double): CurrencyA
  }

  abstract class Dollar extends CurrencyA {
    override def designation = "USD"
  }
  abstract class Euro extends CurrencyA {
    override def designation = "ECD"
  }
}

