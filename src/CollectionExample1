import scala.collection.immutable.{HashMap, TreeMap}

/**
  * Created by kumagai on 2016/06/09.
  */
object CollectionExample1 extends App {

  // 104 コレクションの比較
  val result1 = List[String]("A", "B") == Set[String]("A", "B")
  val result2 = HashMap("key1" -> "value1", "key2" -> "value2") ==
                TreeMap("key1" -> "value1", "key2" -> "value2")

  println("104 result1 is %s result2 is %s".format(result1, result2))

  // 105 コレクション同士の変換
  val list105 = List[String]("A", "B")
  val array105 = list105.toArray
  println(list105, array105)
  val map105 = HashMap("key1" -> "value1", "key2" -> "value2")
  val list105_2 = map105.toList
  println(map105, list105_2(0)._1, list105_2(1)._2)

  // 106 メソッドでネストしたListを作成
  val list106 = ("A" :: "B" :: Nil) :: ("X" :: "Y" :: "Z" :: Nil) :: Nil
  println(list106)

  // 108 リストを取り出す
  println(list106.headOption.getOrElse("None"))
  println(List().headOption.getOrElse("None"))

  // 109 Listに要素を追加
  val list109 = List[Int](1, 2, 3, 4, 5, 6, 7, 8, 9)
  //末尾に追加したリストを作成
  val list109_1 = list109 :+ 4
  val list109_1x = list109 ::: 4 :: Nil
  //先頭に要素を追加したリストを作成 TODO: エラーになる
  //val list109_2 = list109 +: 0
  val list109_2x = 0 :: list109

  // 110 リストの要素を削除
  val list110 = list109.filter(_ % 2 == 0)
  println("偶数フィルタリスト" + list110)

  // 111 Listを連結 TODO: ++ で連結できないんですけど。。。
  val list111 = list105 ::: list110
  println("連結リスト" + list111)

  // 112 Listの要素を繰り返し処理 TODO: なぜcaseがいるのか？
  list111.zipWithIndex.foreach{case(e: AnyRef, i: Int) => {
    println("zipWithIndex.foreach %d = %s".format(i, e))
  }}

  // 113 Listの要素を変換 map collect
  val list113 = List("Java", "Scala", "Clojure", "Py")
  val list113_len = list113.map{ _.length}
  println(list113, list113_len)

  val list113_2 = List(1, "A", "B", "C") // 文字列だけ取り出したリストを作成
  val list113_str = list113_2.collect{case e: String => e}

  val list113_4 = List(1,2,3,4,5,6,7,8)
  val list113_3 = list113_4.collect { case i if i >= 3 => i * 2}
  println(list113_str, list113_3)

  // 114 Listの要素をソート
  val list114 = list113_4.sorted.reverse // 数値の降順でソート
  val list114_2 = list113.sortWith{(s1, s2) => s1.length < s2.length} // 文字列の短いものから

  println(list113_str, list113_3, list114, list114_2)

  // 115 Listの要素を文字列に変換したい
  val list115 = List(Cafe("Starbaks", 1), Cafe("BeksCoffe", 2), Cafe("Droche", 3))
  val list115_1 = list115.map{ case c: Cafe => c.name }
  val list115_2 = list115_1.mkString("Prefix: CafeName = ", " / ", "Suffix")
  println(list115, list115_1, list115_2)

  // 116 Listの一部を切り出す tail init slice take drop takeWhile dropWhile
  // TODO: スキップ
  // 117 Listを分割
  val (split1, split2) = list113_3.splitAt(2)
  val (span1, span2) = list113_3.span(p => p % 2 == 0)
  val (partition1, partition2) = list113_3.partition(p => p < 4)
  // TODO: groupByの使い方はもっと勉強が必要
  val groupBy: Map[Int, List[String]] = list113.groupBy{
    _.length
  }
  println("groupByのexample", groupBy)

  // 118 条件を満たすか調べる contains exists forall
  val contains = list115.contains(Cafe("Starbaks", 1))
  val exists = list115.exists{ _.name.startsWith("D") }
  val forall = list113.forall{ _.length > 4 }
  println(contains, exists, forall)

  // 119 ネストしたListをフラットにする Option型のリストから値を取り出すなど
  val list119 = List(Some(1), Some(null), Some(3), None)
  println(list119.flatten)

  // 120 List要素の集計値を求める
  val reduceLeft = list113_4.reduceLeft {
    (a, b) => a + b
// TODO: クラスのメンバーでreduceは出来るのか    (a: Int, b: Cafe) => a + b.name.length
  }
  println("Cafe name length %s".format(reduceLeft))

  // 121 2つのListの要素のペアを持つ zipとunzip
  val list121 = list115.zip(list119)
  println(list121)
}
case class Cafe(val name: String, val rank) {
  //def apply(val name: String): Cafe = new Cafe(name)
}
