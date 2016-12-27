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
  *
  */
class ImplicitConversionLesson2 {

}
object ImplicitConversionLesson1 extends App {

}
