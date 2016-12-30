package cop23.implementing_lists

/**
  * Created by kumagai on 2016/12/29.
  */
class ListLesson {
  case class Person(val name: String)
  def collectPersonNames(persons: List[Person]) = {
    for (
      p <- persons; // ジェネレーター
      n = p.name;   // 定義
      if (n startsWith "To") // フィルター
    ) yield n // pat <- expr
  }

  // N女王問題
  def queens(n: Int): List[List[(Int, Int)]] = {
    def placeQueens(k: Int): List[List[(Int,Int)]] =
      if (k == 0)
        List(List())
      else
        for {
          queens <- placeQueens(k - 1)
          column <- 1 to n
          queen = (k, column)
          if isSafe(queen, queens)
        } yield queen :: queens
     placeQueens(n)
  }
  def isSafe(queen: (Int, Int), queens: List[(Int, Int)]) = {
    queens forall (q => !inCheck(queen, q))
  }

  def inCheck(q1: (Int, Int), q2: (Int, Int)) =
    q1._1 == q2._2 ||
      q1._2 == q2._2 ||
      (q1._1 - q2._1).abs == (q1._2 - q2._2).abs
}
/** for式の変換
  * 全てのfor式はmap,flatMap,withFiterの３つの高階関数によって表現できる
  */
object ListLesson2 extends App{
  // ジェネレーターが１個の時のfor式の変換
  for (x <- 1 to 10 ) yield x
  1 to 10 map(x => x)

  // 1個のジェネレーターと１個のフィルターで始まるfor式の変換
  for (x <- 1 to 10; if x % 2 == 0) yield x
  1 to 10 withFilter(x => x % 2 == 0) map(x => x)

  for (x <- 1 to 10 if true; y <- 1 to 5) yield (x, y)
  for (x <- 1 to 10 withFilter(x =>true); y <-1 to 5) yield(x, y)

  // ２個のジェネレーターで始まるfor式の変換
  // for (x <- expr; y <- expr: seq) yield expr
  //expr.flatMap(x => for (y <- expr; seq) yield expr)

  // データベースに少なくとも２冊の図書を持っているすべての著作者名を探索する
  case class Book(title: String, authors: String*)
  val books: List[Book] =
    List(
      Book(
        "Structure and Interprettaition of Computer Programs",
        "Abelson, Harold", "Sussman Gerald J."
      ),
      Book(
        "Principles of Compiler Design",
        "Aho, Alfred", "Ullman Jeffrey."
      ),
      Book(
        "Programming in Modula-2",
        "Wirth, Niklaus"
      ),
      Book(
        "Elements of ML Programming",
        "Ullman, Jeffrey"
      ),
      Book(
        "The Java Language Specification",
        "Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad"
      )
    )

  // forで書いた場合
  def findAuthers1 =
    for (book1 <- books; book2 <- books; if book1 != book2;
      auther1 <- book1.authors; auther2 <- book2.authors;
      if auther1 == auther2) yield auther1

  // 高階関数で書いた場合
  def findAuthers2 =
    books flatMap (b1 =>
     books withFilter(b2 => b1 != b2) flatMap (b2 =>
      b1.authors flatMap (a1 =>
        b2.authors withFilter (a2 => a1 == a2) map (a2 =>
          a1))))

  println(findAuthers1)
  println(findAuthers2)

  // ジェネレーターに含まれるパターンの変換
  // for((x, ....xn) <- expr) yield expr
  // このように変換される
  /*
  expr.map {case (x, ....xn) => expr }
   */

  val xss = List(List(1, 2, 3), List(4,5,6,7,8,9))
  // 次の式はリストのリストとして表現された行列のすべての要素を合計する。
  var sum = 0
  for (xs <- xss; x <- xs) sum += x
  println(sum)
  // 変換後
  sum = 0
  xss foreach(xs =>
    xs foreach(x =>
      sum += x))

}
