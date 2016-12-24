package cop20.abstractmembers

/**
  * Created by kumagai on 2016/12/24.
  */
class AbstractLesson {

}

trait Abstract {
  type T // Scalaの抽象型
  def transform(x: T): T
  val initial: T // 抽象val
  var current: T
}

class Concrete extends Abstract {

  type T = String // Tという型名にStringという別の型名を定義し、具体的な意味を与えている。

  def transform(x: String) = x.toUpperCase

  val initial: String = "hi!"

  var current = initial
}

/** 抽象var
  * クラスのメンバーとして宣言されたvarには自動的にゲッター・セッターメソッドが与えられる
  */
trait AbstractTime {
  var hour: Int
  var minute: Int
}

/** 抽象valの初期化
  * パラメータと似た役割を果たすことがある。
  * 特にパラメータを渡せるコンストラクタを持っていないトレイトでは
  * トレイトにパラメータを与えるという考え方は抽象valで実現できる
  */
trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
  private val g = gcd(numerArg, denomArg)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
  val numer = numerArg / g
  val denom = denomArg / g

  override def toString: String = numer + "/" + denom
}
object RationalTraitLessonApp extends App{
/*
  // java.lang.IllegalArgumentException: requirement failed が発生する
  new RationalTrait {
    override val denomArg: Int = 1
    override val numerArg: Int = 2
  } //.toString
  // 上記の対処方法として、事前初期化済みフィールドと遅延評価(lazy)val の２つがある
  */
  /*
  事前定義済みフィールド
   */
  new { // 無名クラス
    val numerArg = 2
    val denomArg = 3
  } with RationalTrait = 1/2
  object twoThirds extends { // オブジェクト定義に含まれている事前初期化済みフィールド
    val numerArg = 2
    val denomArg = 3
  } with RationalTrait

  class RationalClass(n: Int, d: Int) extends {
    val numerArg = 2
    val denomArg = 3
  } with RationalTrait {
    def + (that: RationalClass) = new RationalClass(
      numer + that.denom + that.numer * denom,
      denom * that.denom
    )
  }
}
