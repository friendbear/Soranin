/**
  * Created by kumagai on 2016/06/16.
  */
object ParCollectionExample extends App {
  // 139 並列コレクション
  val range: Range = 1 to 100
  // 通常のコレクションを利用する場合
  val result1 = range.map{ _ + 1 } reduceLeft {_ + _}
  val result2 = range.par.map{ _ + 1 } reduceLeft{ _ + _ }

  import scala.collection.parallel.immutable._
  val parSet = ParSet(1, 2, 3, 4)
  // setに変換
  val set = parSet.seq

  // ParMapを直接生成
  val parMap = ParMap("key1" -> "value1")
  // Mapに変換
  val map = parMap.seq


}
