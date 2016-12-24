package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  */
/** クラスの公開インターフェースだけを締めストレイトだけをエクスポートする
  * Queueにenqueueするだけのクラス
  */
trait Queue2[T] {
  def head: T
  def tail: Queue2[T]
  def enqueue(x: T): Queue2[T]
}

object Queue2 {
  def apply[T](xs: T*): Queue2[T] =
    new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](
    private val leading: List[T],
    private val trailing: List[T]) extends Queue2[T] {

    private def mirror =
      if (leading.isEmpty)
        new QueueImpl(trailing.reverse, Nil)
      else this

    override def head: T = {
      mirror.leading.head
    }

    override def tail: Queue2[T] = {
      val q = mirror
      new QueueImpl[T](q.leading.tail, q.trailing)
    }

    override def enqueue(x: T): Queue2[T] = new QueueImpl(leading, x :: trailing)
  }
}

object Queue2App extends App {
  val q = Queue2[Int](1, 2, 3, 4)
  println(f"head:${q.head}")
  println(f"head:${q.head}")
  println(f"head:${q.head}")
  println(f"head:${q.head}")
  q enqueue 10

}
