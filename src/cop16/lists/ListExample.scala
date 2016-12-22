package cop16.lists

/**
  * Created by kumagai on 2016/12/20.
  */
object ListExample {
  def apply() = new ListExample
}
class ListExample {

  def isort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

  }
  private def insert(x: Int, xs: List[Int]): List[Int] = {
    if (x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)
  }
  def i2sort(xs: List[Int]): List[Int] = {
    xs match {
      case List() => List()
      case x :: xs1 => insert2(x, i2sort(xs1))
    }
  }
  private def insert2(x: Int, xs: List[Int]): List[Int] = {
    xs match {
      case List() => List()
      case y :: ys => if(x <= y) x :: xs
      else y :: insert2(x, ys)
    }
  }
}

object ListExampleApp extends App {

  val nums = 1 :: 2 :: 4 :: 5 :: 3 :: Nil
  val ans = ListExample().i2sort(nums)
  println(ans)
  nums.reverse
  // ついになっている
  nums.head
  nums.last
  // ついになっている
  nums.tail
  nums.init

  nums take 2 //リスト先頭のnまで要素を返す
  nums drop 2 //リスト先頭のn以降の要素を返す
  nums splitAt 2 //リストをn個目で分割したタプルを返す

  // リストのリストから単走のリストへ
  val numl = nums :: List(1, 2, 3) :: List(5, 4) :: Nil
  println(numl.flatten)
  val fluits = List("Apple", "Orange")
  println(fluits.map( _.toCharArray).flatten)

  println(numl.mkString("="))

  println(numl.addString(new StringBuilder("aaaaaaaaaa")))

  // Listクラスの高階メソッド(１つ以上の関数を引数に取るメソッド）
  List(1, 2, 3).map(_ * 2)
  val words = List("the", "quick", "brown", "fox")
  words map (_.length)
  words map (_.toList.reverse.mkString)
  words flatMap(_.toList)

  // 1 <= j < i < 5 になる全てのついのリストを作る
  List.range(1, 5).flatMap(i => List.range(1, i) map (j => (i, j)))
  for (i <- List.range(1, 5); j <- List.range(1, i)) yield (i, j)

  var sum = 0
  List(1,2,3,4,5).foreach(sum += _)

  List(1,2,3,4,5).filter(_%2 == 0)

  List(1,2,3,4,5) partition(_ % 2 ==0)

  List(1,2,3,4,5) find (_ % 2 == 0) // Some(2)
  List(1,2,3,4,5) find (_ <= 0) // Option[Int] = None

  List(1,2,3,-4,5).takeWhile(_ > 0)
  words dropWhile (_ .startsWith("t"))

  // Listの畳み込み
  ("" /: words)(_ + " " + _)
  (words.head /: words.tail)(_ + " " + _)


}
