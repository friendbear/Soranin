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
  val cl = cloger(10)
  println(cl(2))


}
