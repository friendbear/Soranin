package cop10

import Element.elem // Element.elemをインポートしておき単純名のelemでファクトリーメソッドを呼び出す

/**
  * Created by kumagai on 2016/12/16.
  */
abstract class Element {

  // パラメータ無しメソッドの定義 空カッコメソッド def height(): Int
  // ミュータブルな状態へのアクセスがオブジェクトのフィールドの読み出しだけなら
  // パラメータ無しメソッドを使うべきとされている
  /*
  副作用を持つ関数を呼び出すときには、忘れずにからかっっこをつけるようにする。
  この習慣は呼び出そうとしている関数が操作を実行するならカッコを使い
  プロパティへのアクセスを提供するだけならカッコを省略すると覚えておく。
   */
  def contents: Array[String]
  def height: Int = contents.length
  def width: Int = if (height == 0) 0 else contents(0).length
  def above(that: Element): Element = {
    elem(this.contents ++ that.contents)
  }
  // beside改良 配列からペアを作成、配列を作ってyieldでArrayに詰め直す
  def beside(that: Element): Element = {
    elem(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2
    )
  }
  // mkString
  override def toString: String = contents mkString "\n"
}

// ファクトリオブジェクトを定義
object Element {
  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  def elem(line: String): Element =
    new LineElement(line)

  /* ファクトリメソッド導入によりシングルトンオブジェクトにサブクラス群を隠蔽
   */
  private class ArrayElement(conts: Array[String]) extends Element{
    // パラメータ無しメソッドをフィールドでオーバーライドする
    def contents: Array[String]  = conts
    def besideZ(that: Element): Element = {
      val contents = new Array[String](this.contents.length)
      for (i <- 0 until this.contents.length)
        contents(i) = this.contents(i) + that.contents(i)
      new ArrayElement(contents)
    }
  }
  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width: Int = s.length
    override def height: Int = 1
  }
  private class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }
}


// パラメータフィールドとしてcontentsを定義する（パラメータとフィールドを結合）
class ArrayElement2(val contents: Array[String]) extends Element

// スーパークラスコンストラクターの呼び出し
//class LineElement2(s: String) extends ArrayElement(Array(s))


object ElementApp extends App {
  val elem = new ArrayElement2(Array("abc", "def"))
  println("%d %d".format(elem.height, elem.width))
//  val lineElem = new LineElement("ghij")
//  println("%d %d".format(lineElem.height, lineElem.width))
//  val lineElem2 = new LineElement2("ghij")
//  println("%d %d".format(lineElem2.height, lineElem2.width))

}
