import java.util.Calendar

import scala.beans.BeanProperty;
/**
  * オブジェクト指向
  * Created by kumagai on 2016/06/04.
  */
object Example3 extends App {

  val outer1 = new Outer
  val outer2 = new Outer
  // キャスト TODO: うまくキャストできない
  //var inner: Outer#Inner = new outer1.Innner
  var inner = new outer1.Innner

  //inner = new outer2.Inner  // コンパイルエラー
  println(inner.getInnerf())

  /**
    * 呼び出し方の練習
    */
  val instance = new HelloWorld
  instance.greet1("kumagai")
  instance greet2 "sorataka" // ピリオドは省略可能
  instance greet1 "name" // 引数が１つの場合、丸括弧も省略できる
  instance.greet1{ if (true) "kanji" else "kana"} // 引数が１つの場合、浪括弧{}を利用しても良い
  instance.printHello() // 引数なしの場合、丸括弧は指定・省略のどちらでもいい
  instance.printHello
  instance.hello // メソッド定義で丸括弧を省略した場合は、必ず丸括弧を省略

  /**
    * メソッド
    */
  // デフォルト値の設定
  def price(total: Int, discount: Int = 500) = total - discount
  def priceDay(total: Int, discount: Int =
              if (Calendar.getInstance.get(Calendar.DATE) % 2 == 0) 500 else 100) = {
    total - discount
  }
  println(priceDay(5000, 1))
  println(priceDay(5000, 2))
  println(priceDay(5000))

  // 暗黙の引数について(implicit parameter)
  val vat = new VAT
  vat.calc

  // オブジェクトが暗黙の値
  implicit object VAT extends Tax1 {
    def rate = 50
  }

  val c = new Calculation
  println(c.tax)

  // 複数のコンストラクタ（補助コンストラクタ）
  val w1 = new World(5, 5, 5)
  var w2 = new World(5)
  println("1 Instance World get %d, %d, %d".format(w1.x, w1.y, w1.z))
  //w2.z_= 1 // : TODO: Setter がvarで定義したパラメータで渡せない w2.y_=
  w2.z = 1
  println("2 Instance World get %d, %d, %d".format(w2.x, w2.y, w2.z))

  // シールドクラス、caseクラス
  @unchecked
  def message(m: SuperClass) = {
    m match {
      case ByteMessage() => println(ByteMessage.getClass())
      case ObjectMessage() => println(ObjectMessage.getClass())
      case TextMessage() => println(TextMessage.getClass())
      case _ => throw new IllegalStateException()
    }
  }
  println(message(new ObjectMessage()))

  // オーバーライド
  val o = new Sub2Class()
  println(o.getKitchen("kitchenList"))
}

// アウタークラス
class Outer {
  //def outerd = (new Inner).innerf  //コンパイルエラー

  // インナークラス
  class Innner {
    private val innerf = "Inner"

    def getInnerf() = innerf
  }
}

// メソッド定義の練習
class HelloWorld {
  def hello(arg: String): Unit = {
    println("Hello")
  }
  // 式が一つの場合１行でかける
  def greet1(arg: String): String = "Hello World " + arg

  // 通常、戻り値の型は推論できるので省略可能
  def greet2(arg: String) = "Hello World " + arg

  // 引数なしの場合
  def printHello() = println("Hello World")

  // 引数なし、かつt副作用がない場合は丸括弧を省略することが慣習
  def hello = "Hello World"

  // 可変長引数
  def args(values: String*) = for (s <- values) println(s)
}

class Tax {
  // 引数rateが暗黙の引数
  def tax(implicit rate: Int) = rate * 0.01
}
class VAT {
  // 暗黙の引数に実際に渡す値
  implicit val vat = 5

  def calc = {
    val tax = new Tax
    println(tax.tax)    // 引数が同じスコープの中から型に応じて実際に渡す値が検索される
    println(tax.tax(10)) // 引数を指定した場合
  }
}

trait Tax1 {
  def rate: Int
}
class Calculation {
  // 暗黙の引数
  def tax(implicit tax: Tax1) = tax.rate * 0.01
}

class World(val x: Int, var y: Int, var z: Int) {
  println(x + y + z) // 基本コンストラクタ

  def this(z2: Int) = {
    this(0, 0, z2) // 基本コンストラクタを呼び出す
    println("補助コンストラクタ %d".format(x + y + z))
  }
}

final class finalClass  // 継承できないクラス

/**
  * クラスの派生
  * @param i
  */
sealed class SuperClass(val i: Int)  // シールドクラス
case class TextMessage() extends SuperClass(0)
case class ObjectMessage() extends SuperClass(0)
case class ByteMessage() extends SuperClass(0)

@BeanProperty
class SubClass(i: Int, val h: Int, name: String) extends SuperClass(i) {
  def this(name: String) = this(1, 5, name)
  val iname: String = "SubClass"
  def getKitchen(value: String): List[String] = {
    List(iname, "kyoto", "osaka", value)
  }
}

/**
  * オーバーライド
  */
class Sub2Class extends SubClass(1,1, "kumagai") {
  override val iname = "Sub2Class"
  override def getKitchen(value: String): List[String] = {
    super.getKitchen("chiba") ::: List("hokaidou", iname)
  }
}

abstract class AbstractClass {

  // 抽象フィールド
  val value: String

  // 抽象メソッド
  def hello(arg: String): String
  //def hello(arg: String) 戻り値を指定しない場合はUnitになる
}
