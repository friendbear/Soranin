package cop21.implict_conversions_and_parameters

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton

/**
  * Created by kumagai on 2016/12/27.
  */
class ImplicitConversionsLesson {

}

/** 暗黙の型変換
  *
  */
class ImplicitConversionLesson1 {
  // Scalaの暗黙の型変換を使わない場合、Javaと同じように内部クラスを使わなければならない
  val button = new JButton()
  button.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent) = println("pressed!")
  })
  // 引数として関数であった場合
//  button.addChangeListener(
//    (_: ActionEvent) => println("pressed")
//  )
  // 上のコードのaddChangeListenerメソッドはアクションリスナーを求めているので関数を渡しても動作しない
  /*
    * 暗黙の型変換を使えば上記が動かせるようになる
    * 2つの方で暗黙の型変換を書く。次に示すのは関数からアクションリスナーへの暗黙の型変換である
    *
    * これは引数が１のみのメソッドをであり、メソッドは関数をとってアクションリスナーを返す。
    * このメソッドは、１個の引数をとる他のメソッドと同様に直接呼び出してその結果を他の式に渡すことができる
    */
  implicit def function2ActionListener(f: ActionEvent => Unit) =
    new ActionListener{
    def actionPerformed(event: ActionEvent) = f(event)
  }
  button.addActionListener(
    function2ActionListener(
      (e: ActionEvent) => println("pressed")
    )
  )
  // コンパイラが型の不一致を見つけると、問題を修復できる暗黙の型変換を探す。（function2ActionListener)
  // コンパイラは、型変換メソッドを試し、うまく動作することを確かめ、実際に動作させる。以下コード
  button.addActionListener(
    (e: ActionEvent) => println("pressed")
  )
}

/** implicitの規則
  * 暗黙の型変換は次の一般原則によって管理されている。
  * # マーキングルール:implicitによって修飾された定義だけが暗黙の型変換に使われる
  *   implicitは任意の変数、関数、オブジェクト定義につけられる。
  *
  * # スコープルール:挿入される暗黙の型変換は、単一の識別子としてスコープ内にあるか、
  *   変換のソース型やターゲット型として対応づけられていなければならない
  *   > 暗黙の型変換は単一の識別子としてスコープ内に入っていなければならない
  *     つまり、コンパイラーは、someVariable.convertという形式の変換を挿入することはない
  *     importしてしまえば適用できる。
  *     役に立つ暗黙の型変換をいくつか含んだPreambleオブジェクトを用意することが多い。
  *
  * # １度に１回ルール:暗黙の型変換は１度しか挿入されない
  *   コンパイラが X + y を convert1(convert2(2)) + y に書き換えることは決してない。
  *
  * # 明示的変換優先ルール:書かれたままの状態でコードが型チェックをパスするときは暗黙の型変換は行われない
  *   コンパイラーはすでに動作するコードを書き換えたりはしない。言い換えれば、暗黙の識別子を明示的な識別子に
  *   置き換えて、コードを少し長くするとともに、明らかに曖昧な箇所を減らすことはいつでも可能
  *   コンパイラーに挿入させる暗黙の型変換の量をどの程度にするかは、究極的にはスタイルの問題である。
  *
  */
class ImplicitConversionLesson2 {

  // 暗黙の型変換の名前の付け方
  /** ２つの型変換を持つオブジェクトがあったとして、片方だけを使いたいが、もう片方をつかわっれては困る場合は
    * 片方の変換だけをインポートする。
    */
  object MyConversions {
    implicit def stringWrapper(s: String): Seq[Char] = {
      Seq('a','b')
    }
    implicit def intToString(x: Int): String = {"abc"}
  }

  // implicit関数定義の例
  implicit def intToString(x: Int) = x.toString

}

/** 要求された型への暗黙の型変換
  *
  */
class ImplicitConversionLesson3{
  implicit def doubleToInt(v: Double) = v.toInt
  val i: Int = 3.5 // <== 上の暗黙の型変換がなければコンパイルエラー

  // Predefでは小さな整数型から大きな整数型への暗黙の型変換を定義している。
  // implicit def int2double(x: Int): Double = x.toDouble
}

/** レシーバーの変換
  * 暗黙の型変換は、メソッド呼び出しのレシーバー、すなわちメソッド呼び出しで使われるオブジェクトにも
  * 適用される。
  * ２つの用途があり、既存のクラス階層に新しいクラスを円滑に組み込むこと、円滑に組み込むこと
  * そしてScala言語の枠内でドメイン固有言語（DSL）を書くためのサポートである。
  */
class ImplicitConversionLesson4 {
  /*
  　新しい型の同時使用
   */
  class Rational(n: Int, d: Int){
    implicit def intToRational(x: Int) = new Rational(x, 1)
  }
  /*
  scala> 1 + oneHalf -> intToRational(1) + oneHalf = Int + Rational -> intToRational(Int) + Rational
   */
  /*
    新しい構文のシミュレーション
    Map(1 -> "one") の -> はscala.Predefの中で定義されているArrowAssocクラスのメソッド
    構文拡張的な機能を提供するライブラリーでは、この「リッチラッパー」パターンがよく見られる。
    そういったものを見たら、すぐにこのパターンだと気付けるようにしたいところだ。
    レシーバークラスにはなさそうなメソッドを呼び出しているコードがあれば、おそらく暗黙の型変換を使っているのである。
    RichInt、RichBooleanなどRichSomethingという名前のクラスを見かけたら、そのクラスは、構文的にSomething型に見えるメソッドを
    追加していると考えて良い
   */

  /** 暗黙のクラス
    * リッチラッパークラスを描きやすくするために、暗黙のクラスが追加された。
    * 暗黙のクラスとは、implicitキーワードが先頭につけられたクラスのことである。
    * リッチラッパーパターンのクラスを使おうと思っているときに必要なのはまさにこの変換だ。
    *
    * implict class はケースクラスになれないし、コンストラクターは１個の引数を取るものでなければならない
    *
    */
  case class Rectangle(width: Int, height: Int)
  // リッチラッパークラス
  implicit class RectangleMaker(width: Int) {
    def x(height: Int) = Rectangle(width, height)

    //次のような変換コードも自動生成される
    //implicit def RectangleMaker(width: Int) =
    //  new RectangleMaker(width)
  }
  val lesson4: Rectangle = 4 x 3  // <== Intに xメソッドなし-> implicit class RectangleMakerを見つける、x関数を見つける、実行される。
}
object ImplicitConversionLesson extends App {

}
