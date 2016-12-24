package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  */

/** 反変の出力チャネル
  *
  */
trait OutputChannel[-T] {
  def write(x: T)
}

trait Function1[-S, +T] {
  def apply(x: S): T
}
class Contravariance {

}

/** 関数型パラメータの変位指定のサンプル
  *
  */
class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] = Set(
    new Book("Programming in Scala"),
    new Book("Walden")
  )

  def printBookList(info: Book => AnyRef) = {
    for (book <- books) println(info(book))
  }
}
object Customer extends App {
  def getTitle(p: Publication): String = { p.title }
  Library.printBookList(getTitle)
}
