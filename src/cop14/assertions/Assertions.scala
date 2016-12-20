package cop14.assertions

import cop10._
/**
  * Created by kumagai on 2016/12/19.
  */
abstract class Assertions extends Element{


  // TODO: cop10が完了していないためWIP
  override def above(that: Element): Element = {
/*    val this1 = this widen that.width
    val that1 = that widen this.width
    assert(this1.width == that1.width)
    elem(this1.contents ++ that1.contents)
    */
    Element.elem(Array("abc"))
  }

  private def widen(w: Int): Element = {

    if (w <= width)
      this
    else {
      val left = Element.elem(' ', (w - width) /2, height)
      val rigit = Element.elem(' ', w - width - left.width, height)
      left beside this beside rigit
    } ensuring(w <= _.width, "算出結果エラー")  // 結果に対してassertをかけ、falseの場合 => AssertionError
  }
}
