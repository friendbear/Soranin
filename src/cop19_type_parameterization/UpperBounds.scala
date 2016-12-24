package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  * 上限境界
  */
class UpperBounds {

  /** 上限限界を指定しているマージソート関数
    *
    * @param xs
    * @tparam T
    * @return
    */
  def orderdMergeSort[T <: Ordered[T]](xs: List[T]): List[T] = {
    def merge(xs: List[T], ys: List[T]): List[T] = {
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x < y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }
    }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(orderdMergeSort(ys), orderdMergeSort(zs))
    }
  }
}

/** OrderdトレイトをミックスインしているPersonクラス
  *
  */
class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
  override def compare(that: Person) = {
    val lastNameComparison =lastName.compareToIgnoreCase(that.lastName)
    if (lastNameComparison != 0)
      lastNameComparison
    else
      firstName.compareToIgnoreCase(that.firstName)
  }
}

object UpperBoundsApp extends App {
  val people = List(
    new Person("Larry", "Wall"),
    new Person("Anders", "Hejlsberg"),
    new Person("Guido", "van Rossum"),
    new Person("Alan", "Key"),
    new Person("Yukihiro", "Matsumoto"),
    new Person("Tomohiro", "Kumagai")
  )

  val sort = new UpperBounds()
  sort.orderdMergeSort(people).foreach(p => println(f"${p.firstName}, ${p.lastName}"))
}



