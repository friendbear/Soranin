package cop09

/**
  * Created by kumagai on 2016/12/15.
  * 名前渡しパラメータ
  */
object ByNameParameters{ // コンパニオンオブジェクト
  def apply(assertionsEnabled: Boolean = true): ByNameParameters = {
    new ByNameParameters(assertionsEnabled)
  }
}
class ByNameParameters(assertionsEnabled: Boolean = true) {

  def myAssert(predicate: () => Boolean) =
    if (assertionsEnabled && predicate() == false)
      throw new AssertionError()

  // 名前渡しパラメータ () => を => に
  def byNameAssert(predicate: => Boolean) =
    if (assertionsEnabled && predicate == false) // predicate呼び出しに()が不要
      throw new AssertionError()
}

object ByNameParametersApp extends App {

  ByNameParameters().myAssert(() => true == false) // predicate呼び出しに()が必要
  ByNameParameters().byNameAssert(true == false) // predicate呼び出しに()が不要

}
