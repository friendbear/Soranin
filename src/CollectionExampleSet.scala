import scala.collection.immutable.SortedSet

/**
  * Created by kumagai on 2016/06/15.
  */
object CollectionExampleSet extends App {

  // 130 Setを使う
  val set: Set[String] = Set("A", "B", "C", "D")
  //要素が含まれているか確認
  if (set.contains("B")){
    set.foreach { s =>
      println(s)
    }
  }
  //要素を変換
  val lengthSet = set.map { _.length}
  println (lengthSet)

  // 要素の追加
  val setJoin: Set[String] = set ++ Set("E", "F", "D") + "F"

  // 131 要素の論理演算
  val set1: Set[Int] = Set(1, 2, 3)
  val set2: Set[Int] = Set(2, 3, 4)
  println(set1 & set2) // 積集合
  println(set1 | set2) // 和集合
  println(set1 &~ set2) // 差集合

  // 132 ソートされたSet
  val sortedSet = SortedSet(2,5,3,8, 0)
  sortedSet.foreach{ println _ }

  // 133 配列を使う
  val array1 = Array("A", "B")

  // コンストラクタを利用して長さを指定して配列を宣言し後から要素をセット
  val array2 = new Array[String](2)
  array2(0) = "1番めの配列"
  array2(1) = "2番めの配列"
  val list = array2.toList
  val array22 = new Array[String](2)(2) // ２次元配列

  // 136 Stream Listと同じように使用できる
  val stream = 0 to 1000000000 toStream

  def fromResultSet(rs: java.sql.ResultSet): Stream[(Int, String)] = {
    rs.next match {
      case false => Stream.empty
      case true => (rs.getInt("USER_ID"), rs.getString("USER_NAME")) #:: fromResultSet(rs)
    }
  }
}
