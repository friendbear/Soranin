/**
  * Created by kumagai on 2016/06/15.
  */
object CollectionExampleMap extends App {

  // 123 Option型
  val emptyMap: Map[Int, String] = Map()
  val map123 = Map("key1" -> "value1", "key2" -> "value2")
  val get1 = map123.get("key1")
  val get2 = map123.get("key2")
  val get3 = map123.get("key3")
    println(get1, get2, get3)
  val getOrElse1 = map123.getOrElse("key1", "")
  val getOrElse2 = map123.getOrElse("key2", "")
  val getOrElse3 = map123.getOrElse("key3", "")
  println(getOrElse1, getOrElse2, getOrElse3)
  try {
    val value1 = map123("key1")
    val value2 = map123("key2")
    val value3 = map123("key3")
//
    println(value1, value2, value3)
  } catch {
    case e : Throwable  => println(e.printStackTrace())
  }
  if (map123.contains("key")) println("あり")

  // 124 Mapの要素を繰り返し処理
  map123.foreach{ e =>
    println("Key = %s, Value = %s".format(e._1, e._2))
  }
  // タプルからキーと値を抽出
  map123.foreach { case(key, value) =>
    println(key, value)
  }
  // keyを繰り返し処理
  val keyList = List()
  val itr1 = map123.keys.foreach { key =>
    // TODO: ERRORになる println("values = %s".foreach(map123.getOrElse(key, "")))
    //key :: List(key)
    // キーの一覧を作りたい場合 TODO: 再帰処理が必要？
  }
  println(map123.keys.toList) // これで済んじゃう
  println("itr1 = ", itr1) // これで済んじゃう

  // 125 Mapに要素を追加
  val map125 = map123 + ("key3" -> "value2" )

  // 126 Mapの連結 Listとの連結はタプルを持っている必要あり
  val tup: (String, String) = ("1", "1番バッター")
  // tuppleと結合
  val map126 = List(tup) ++: map125

  // 127 マップから要素を削除
  val removeMap1 = map126 - "key"
  val removeMap2 = map126 - ("key1", "key2")
  val removeMap3 = map126 -- List("key1", "key2")

  // 128 Mapの要素変換
  val books = Map(
    1 -> "Seasar2徹底入門",
    2 -> "現場で使えるJavaライブラリ",
    3 -> "Scala逆引きレシピ"
  )

  println(books)
  // 編集した新しいMapを作成
  val result128 = books.map { case(no, name) =>
    (no, "技術梵名 %s".format(name))
  }
  println(result128)
  case class Book(id: Int, name: String)
  // クラスのIterableを作成
  val classIterator = books.map { case( id, name) =>
    Book(id, name)
  }
  println(classIterator)
}
