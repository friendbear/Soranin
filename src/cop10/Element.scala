package cop10

/**
  * Created by kumagai on 2016/12/16.
  */
abstract class Element {

  // パラメータ無しメソッドの定義 空カッコメソッド def height(): Int
  // ミュータブルな状態へのアクセスがオブジェクトのフィールドの読み出しだけなら
  // パラメータ無しメソッドを使うべきとされている
  /*
  副作用を持つ関数を呼び出すときには、忘れずにからかっっこをつけるようにする。
  この習慣は呼び出そうとしている関数が捜査を実行するならカッコを使い
  プロパティへのアクセスを提供するだけならカッコを省略すると覚えておく。
   */
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length

}

class ArrayElement(conts: Array[String]) extends Element{
  // パラメータ無しメソッドをフィールドでオーバーライドする
  def contents: Array[String]  = conts
}

// パラメータフィールドとしてcontentsを定義する（パラメータとフィールドを結合）
class ArrayElement2(val contents: Array[String]) extends Element

// スーパークラスコンストラクターの呼び出し
class LineElement(s: String) extends ArrayElement(Array(s))
class LineElement2(s: String) extends ArrayElement(Array(s)) {
  override def width: Int = s.length
  override def height: Int = 1
}

object ElementApp extends App {
  val elem = new ArrayElement2(Array("abc", "def"))
  println("%d %d".format(elem.height, elem.width))
  val lineElem = new LineElement("ghij")
  println("%d %d".format(lineElem.height, lineElem.width))
  val lineElem2 = new LineElement2("ghij")
  println("%d %d".format(lineElem2.height, lineElem2.width))

}
