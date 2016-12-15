package cop06

/**
  * Created by kumagai on 2016/12/10.
  */
class Rational(n: Int, d: Int) {
  require(d != 0)
  val numer: Int = n
  val denom: Int = d
  // 補助コンストラクタ
  def this(n: Int) = this(n, 1)

  println(f"Created $n / $d")

  override def toString: String = f"$n / $d"

  def add(that: Rational): Rational = {
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom);
  }

  def lessThan(that: Rational): Boolean = {
    this.numer * this.denom < that.numer * that.denom
  }
  // 大きい方を返す
  def max(that: Rational): Rational = {
    if (this.lessThan(that)) that else this
  }
}


object Cop06App extends App {
  val oneHalf = new Rational(1, 2)
  val twoThirds = new Rational(2, 3)
  oneHalf add twoThirds

}