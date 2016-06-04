/**
  * 関数
  * Created by kumagai on 2016/06/01.
  */
object Example2 extends App{
  println("関数の練習")

  val f1 = (x: Int, y: Int) => {
    x + y
  }
  val f2 = (x: Int) => Math.PI * 2

  val f3 = (x: Int) =>  {
    Math.PI * x
  }

  val f4 = () => println("f3 called")

  println(f1(2,3))
  println(f2(2))
  println(f3(3))
  println(f4())

  // 関数を引数に取る
  def execute(f: () => Unit): Unit = {
    f()
  }
  execute(f4)

  // 関数を返す
  def double: (Float, Boolean) => Float = {
    (i: Float, b: Boolean) => {
      if ( b ) i * 2 else i * 4
    }
  }
  println(double(double(10, true), false)) // 2倍をさらに４倍に

  def pi: () => Double = {
    () => 3.14d
  }

  // クロージャ
  def cloger(max: Int) = (default: Int) => max + default
  val cl = cloger(10) // 後からdefaultの値を設定して変数化
  println(cl(2))

  // 部分関数(partialFunctionトレイト)

  val base: PartialFunction[Throwable, String] = {
    case _ : IllegalArgumentException => "Parameter is invalid"
    case _ : IllegalStateException => "State is invalid"
  }
  val ex: PartialFunction[Throwable, String] = {
    case _ : RuntimeException => "Other Exception"
  }
  def checkMinus(i: Int): String = {
    try {
      if (i < 0) throw new IllegalArgumentException
      "success"
    } catch base orElse ex // orElseで部分関数を連結できる
  }
  println(checkMinus(-1))
  println(checkMinus(1))
  println(base.isDefinedAt(new IllegalAccessError))

  // カリー化について 関数のチェーン
  val greetFunc = (title: String) => (name: String) => title + name
  println(greetFunc("Hello")("kumagai"))
  def greetDef(title: String)(name: String): String = title + name
  println(greetDef("Hello")("sorataka"))

  val nameFunc = greetFunc("Goodby")
  println(nameFunc("kumagai"))

  def executeGreet(data: List[String], f: List[String] => String) = {
    f(data)
  }
  // 上記をカリー化した場合
  def executeGreetCurry(data: List[String])(f: List[String] => String) = {
    f(data)
  }
  val nameList = List("kumagai", "sorataka", "nami")
  println(executeGreetCurry(nameList){data: List[String] =>
      data.toString()
  })

  //関数をカリー化
  //val curryFunc = greetFunc.curried // // TODO: curried がみつからない

  //メソッドをカリー化
  //val curryDef = (greetDef _).curried // TODO: curried がみつからない

}
