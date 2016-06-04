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
