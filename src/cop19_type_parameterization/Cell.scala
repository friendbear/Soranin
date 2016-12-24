package cop19_type_parameterization

/**
  * Created by kumagai on 2016/12/23.
  * 変位指定 +T -T 変位指定アノテーション
  */

/** 非変（厳格）宣言されているCellクラス
  *
  * @param init
  * @tparam T
  */
class Cell [T](init: T) {

  private[this] var current = init

  def get = current

  def set(x: T) = current = x
}

object CellApp extends App {

  val c1 = new Cell[String]("abc")
  // val c2: Cell[Any] = c1   <= コンパイルエラー
  // c2.set(1)
  val s: String = c1.get
}
