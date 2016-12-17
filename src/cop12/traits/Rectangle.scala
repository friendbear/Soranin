package cop12.traits

/**
  * Created by kumagai on 2016/12/17.
  */

class Point(val x: Int, val y: Int)

/**
  * リッチインターフェース用トレイトの定義
  */
trait Rectangular {
  def topLeft: Point
  def bottomRight: Point
  def left = topLeft.x
  def right = bottomRight.x
  def width = right - left
}
// cop06のRationalにOrderdトレイトをミックスインする

class RationalWithTrait(n: Int, d: Int) extends cop06.Rational(n, d) with Ordered[RationalWithTrait] {

  /**
    * Orderdトレイトのcompareオーバライドで、 レシーバーのthisと
    * 引数として渡されたオブジェクトを比較。等しければ0、
    * レシーバーの方が引数より小さければ負数、
    * レシーバーの方が引数より大きければ整数を返す。
    */
  override def compare(that: RationalWithTrait): Int = {
    (this.numer * that.denom) - (that.numer * this.denom)
  }
}

abstract class Component extends Rectangular {

}
class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular {

}

object traitApp extends App {

  val rect = new Rectangle(new Point(1, 1), new Point(10, 10))
  printf(f"${rect.left}, ${rect.right}, ${rect.width}")

  // Orderdトレイトは == equalsメソッドは自分で定義する必要がある
  if (new RationalWithTrait(1, 2) == new RationalWithTrait(1, 2)) printf(" 一致する") else printf(" 一致しない")
}
