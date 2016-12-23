package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  */

/** オブジェクト非公開データ
  * 最適化された関数型待ち行列
  * @param leading
  * @param trailing
  * @tparam T
  */
class OptimizeQueue[+T] (
  private[this] var leading: List[T],
  private[this] var trailing: List[T]
  ) {

  private def mirror() = {
    if (leading.isEmpty) {
      while (!trailing.isEmpty) {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }
  }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: OptimizeQueue[T] = {
    mirror()
    new OptimizeQueue(leading.tail, trailing)
  }

  def enqueue[U >: T](x: U) =
    new OptimizeQueue[U](leading, x :: trailing)

}
