import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by kumagai on 2016/06/16.
  */
object MutableExample extends App {

  import scala.collection.mutable.ListBuffer

  // 137 ミュータブルなListを生成
  val list137 = ListBuffer("A", "B")
  // 先頭に要素を追加
  "Z" +=: list137
  // 末尾に要素を追加
  list137 +: "C"
  // 先頭に複数の要素を一度に追加
  List("X", "Y") ++=: list137
  //末尾に複数の要素を一度に追加
  list137 ++= List("D", "E")
  // インデックスを指定して要素を変更
  list137(4) = "B'"
  //インデックスを指定して要素を削除
  list137.remove(1)
  println(list137)
  val list137Imutable = list137.toList
  println(list137Imutable)
  //リストをクリアする
  //list137.clear()
  // set
  import scala.collection.mutable.Set

  val set137 = Set("A", "B")
  // Map
  import scala.collection.mutable.Map
  val map137 = Map("key1" -> "value1")

  // 138 ミュータブルなコレクションを同期化したい（ミュータブルなコレクションはスレッドセーフではない）
  // TODO: 推奨されないらしい
  import scala.collection.mutable.SynchronizedBuffer
  val arrayBuffer138 = new ArrayBuffer[String]() with mutable.SynchronizedBuffer[String] // トレイトをミックスイン

}
