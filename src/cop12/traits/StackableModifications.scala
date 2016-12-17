package cop12.traits

/**
  * Created by kumagai on 2016/12/17.
  */
class StackableModifications {

}

/**
  * scala.collection.mutable.ArrayBuffer を使って実装
  */
abstract class IntQueue {
  def get(): Int
  def put(x: Int)
}

/** 1. スーパークラスとしてIntQueueを宣言している => このトレイトをミックスインできるのがIntQueueを拡張するクラスだけ
  * 2. abstract宣言されたメソッドでsuperを呼び出していること => 通常エラー、変更の積み重ねを実装するトレイトではたまにやる
  *     この場合は abstract override をつけなければならない
  */
abstract trait Doubling extends IntQueue { // abstract でsuperがポイント
  abstract override def put(x: Int) = { super.put(x * 2) }
}

abstract trait Incrementing extends IntQueue {
  abstract override def put(x: Int) = {super.put(x + 1)}
}

/** 負数をputさせないフィルタートレイト
  *
  */
abstract trait Filtering extends IntQueue {
  abstract override def put(x: Int) = {
    if (x >= 0) super.put(x) else System.err.println("can't put minus")
  }
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  var arrayBuffer = new ArrayBuffer[Int]

  override def get() =
    arrayBuffer.remove(0)

  override def put(x: Int) =
    arrayBuffer += x
}

/** MyQueueが、新しいコードを定義していない点がポイント => BasicIntQueueでoverride済みのため、Dublingの実装が不要
  *
  */
class MyQueue extends BasicIntQueue with Doubling with Incrementing {
  override def put(x: Int) = {
    super[Incrementing].put(x)  // 多重継承したトレイトの呼び出し順序制御
    super[Doubling].put(x)
  }
}

object StackableModificationsApp extends App {

  // new でインスタンス生成時にトレイトをミックスインする
  // 2倍して、負数を取り除いて１を足す
  var intQueue = new BasicIntQueue with Doubling with Filtering with Incrementing    // new MyQueueと同じ
  try {
    intQueue.put(10)
    intQueue.put(5)
    intQueue.put(-5)
    println(intQueue.get())
    println(intQueue.get())
    println(intQueue.get())
  }catch{
    case e: Exception => println(f"catch error: message= ${e.getClass}")
  }
}