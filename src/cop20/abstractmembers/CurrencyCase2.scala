package cop20.abstractmembers

/**
  * Created by kumagai on 2016/12/25.
  */
// --------------------
package case2 {

  /**
    * レート変換対応表オブジェクト
    */
  object Converter {
    var exchangeRate = Map(
      "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596,
        "JPY" -> 1.211),
      "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0,
        "JPY" -> 1.211),
      "JPY" -> Map("USD" -> 0.826, "EUR" -> 0.6272,
        "JPY" -> 1.0)
    )
  }
  /** 抽象型のインスタンスを作れない問題をファクトリーメソッドで
    * 対応したコード
    * 抽象型とファクトリーメソッドをAbstractCurrencyクラスの外に出す
    * AbstractCurrencyクラス、Currency型makeファクトリーメソッドを含む別のクラスを作る(CurrencyZone)
    */
  abstract class CurrencyZone {
    // 上限限界を持つ
    type Currency <: AbstractCurrency
    val CurrencyUnit: Currency
    // ファクトリーメソッド
    def make(amount: Long): Currency

    // 通貨抽象クラス
    abstract class AbstractCurrency {
      // 量
      val amount: Long
      // 通貨文字
      def designation: String
      def +(that: Currency): Currency = {
        make(that.amount + this.amount) // ファクトリメソッドを呼び出す
      }
      def *(x: Double): Currency = {
        make((this.amount * x).toLong) // ファクトリーメソッドを呼び出す
      }
      // 換算メソッド
      def from(other: CurrencyZone#AbstractCurrency): Currency =
        make(math.round(
          other.amount.toDouble * Converter.exchangeRate(other.designation)(designation)
        ))

      override def toString: String = ((amount.toDouble / CurrencyUnit.amount.toDouble)
        formatted("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation)

      /**
        * 10進数の桁数から１を引いた値を返す
        * @param n
        * @return
        */
      private def decimals(n: Long): Int = if (n == 1) 0 else 1 + decimals(n/10)
    }
  }

  /** CurrencyZoneの具像クラスの例 USクラス
    * 米国通貨圏オブジェクト
    * US.Currencyのamauntフィールドをドルではなくセントを単位として表現
    */
  object US extends CurrencyZone {
    abstract class Dollar extends AbstractCurrency {
      override def designation = "USD" // def で定義されているのでオーバライド
    }
    override type Currency = Dollar //
    override def make(cents: Long) = new Dollar {
      val amount = cents // オブジェクト定義に含まれている事前初期化済みフィールド
    }
    // セントの桁
    val Cent = make(1)
    // ドルの桁
    val Dollar = make(100)
    // 表記の桁
    val CurrencyUnit = Dollar
  }

  /** EU の通貨圏
    *
    */
  object Europe extends CurrencyZone {
    abstract class Euro extends AbstractCurrency {
      // 通貨文字
      override def designation = "EUR"
    }
    override type Currency = Euro

    val Cent = make(1)
    val Euro = make(100)
    override val CurrencyUnit: Euro = Euro

    // ファクトリーメソッド
    override def make(cents: Long) = new Euro {
      val amount = cents
    }
  }

  /** Asia の通貨圏
    *
    */
  object Japan extends CurrencyZone {
    abstract class Yen extends AbstractCurrency {
      // 通貨文字
      override def designation = "JPY"
    }
    val Yen = make(1)
    override type Currency = Yen
    override val CurrencyUnit: Currency = Yen

    // ファクトリーメソッド
    override def make(yen: Long) = new Yen {
      val amount: Long = yen
    }
  }

}
