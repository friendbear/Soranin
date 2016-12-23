package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  */
package LowerBoundsQueue {

/** 下限限界が設定された型パラメータ
  *
  * @param leading
  * @param trailing
  * @tparam T
  */
class Queue[+T] (private val leading: List[T],
                 private val trailing: List[T]) {
  def enqueue[U >: T](x: U) = {
    new Queue[U](leading, x :: trailing)
  }
}
}
