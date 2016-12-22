package cop17.othercollection

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer, Map}

/**
  * Created by kumagai on 2016/12/21.
  */
class Collections {

  var array5 = new Array[Int](5)

  def mainLogicListBuffer() = {

    val buf = new ListBuffer[Int]
    buf += 1
    buf += 2
    3 +=: buf // 先頭に追加
    println(buf)
  }
  def mainLogicArrayBuffer() = {
    val buf = new ArrayBuffer[Int]()
    buf += 12
    buf += 15
    buf.length
    buf(0) // 添え字でアクセス
  }

  /** PredefがStringからStringOpsへの暗黙の型変換を行うので
   * 全ての文字列はシーケンスのように扱うことができる
   */
  def hasUpperCase(s: String) = s.exists( _.isUpper)
}
class Sets {

  def mainLogic() = {
    val text = "See Sport run. Run, Spot. Run!"
    val wordsArray = text.split("[ .,!]+")
    println(wordsArray.toList)

    val words = mutable.Set.empty[String]
    for (word <- wordsArray)
      words += word.toUpperCase

    // wordsのSetを元にkv -> word, word.length をMapにした
    val wordsSet = words.map(word => (word -> word.length)).toMap

    println(wordsSet)

    val nums = mutable.Set(1, 2, 3)
    nums + 5
    nums - 3
    nums ++ List(5, 6)
    nums -- List(1, 2)
    nums & Set(1, 3, 5, 7)
    nums.size
    nums.contains(3)
    val ws = mutable.Set.empty[String]
    ws += "the"
    ws -= "the"
    ws ++= List("do", "re", "mi")
    ws --= List("do", "re")
    ws.clear()
  }
}

class Maps {

  def mainLogic() = {
    val map = mutable.Map.empty[String,Int]
    map("hello") = 1
    map("there") = 2
    val i = map("hello")

    val nums = Map("i"-> 1, "ii"-> 2)
    nums + ("vi" -> 6)
    nums - "ii"
    nums ++ List("iii"->3, "v" ->5)
    nums -- List("i", "ii")
    nums.size
    nums.contains("ii")
    nums("ii")
    nums.keys
    nums.keySet
    nums.values
    nums.isEmpty
    import scala.collection.mutable
    val words = mutable.Map.empty[String, Int]
    words += ("one" -> 1)
    words -= "one"
    words ++= List("one" -> 1, "two" -> 2, "three" -> 3)
    words --= List("one", "two")
  }

  /** 文字を、指定した区切り文字で分割し、文字の出現回数をカウント
    *
    * @param text
    * @param regexp
    * @return
    */
  def countWords(text: String, regexp: String): mutable.Map[String, Int] = {
    val texts: List[String] = text.split(regexp).toList // 文字列分解
    val counts = mutable.Map.empty[String, Int] // 文字列登場回数のMap
    for (rawWord <- texts) {
      val oldCount =
        if (counts.contains(rawWord)) counts(rawWord)
        else 0
      counts += (rawWord -> (oldCount + 1))
    }
    counts
  }
}

object CollectionsExample extends App {
  val collections = new Collections
  assert(collections.hasUpperCase("Robert Frost") == true)
  assert(collections.hasUpperCase("e e cummings") == false)

  val sets = new Sets
  sets.mainLogic()

  val maps = new Maps
  maps.countWords("See Sport run! Run, Sport. run!", "[ !,.]+").foreach(println)

  // TreeSet
  import scala.collection.immutable.TreeSet
  val ts = TreeSet(9 , 3, 1, 8, 0, 2, 7, 4, 6, 5)
  ts.foreach(println)
  // TreeMap
  import scala.collection.immutable.TreeMap
  val tm = TreeMap(3 -> 'x', 1->'x', 4->'x')
  tm.foreach(println)

  // ミュータブルとイミュータブルの相互変換
  val treeSet = scala.collection.immutable.Set(1,2,3)
  val mutaSet = mutable.Set.empty ++ treeSet

  val immutaSet = Set.empty ++ mutaSet

  val muta = mutable.Map("i"->1, "ii"->2)
  val immu = scala.collection.immutable.Map.empty ++ muta

}
