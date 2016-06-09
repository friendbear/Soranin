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


}
