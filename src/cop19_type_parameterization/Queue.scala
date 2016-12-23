package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/22.
  */

/** 処理効率の良いQueue
  *
  * @param leading
  * @param trailing
  * @tparam T
  */
class Queue[T](private val leading: List[T], private val trailing: List[T]) {

  private def mirror = {
    if (leading.isEmpty)
      new Queue(trailing.reverse, Nil)
    else
      this
  }
  def head = mirror.leading.head
  def tail = {
    val q = mirror
    new Queue(q.leading.tail, q.trailing)
  }
  def enqueue(x: T): Queue[T] = {
    new Queue[T](leading, x :: trailing)
  }
}

/** 関数型待ち行列
  * head: 待ち行列の先頭要素を返す
  * tail: 先頭要素を取り除いた形で待ち行列を返す
  * enqueue: 指定した要素を末尾に追加した新しい要素を返す
  */
class SlowAppendQueue[T](elems: List[T]) { // 効率が悪い
  def head = elems.head
  def tail = new SlowAppendQueue[T](elems.tail)
  def enqueue(x: T) = new SlowAppendQueue[T](elems ::: List(x))
}
class SlowHeadQueue[T](elems: List[T]) {
  def head = elems.last
  def tail = new SlowHeadQueue[T](elems.init)
  def enqueue(x: T) = new SlowHeadQueue[T](x :: elems)
}
