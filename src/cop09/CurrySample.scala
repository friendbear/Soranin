package cop09


/**
  * Created by kumagai on 2016/12/15.
  */
class CurrySample {

  def plainOldSum(x: Int, y: Int) = x + y

  def curriedSum(x: Int)(y: Int) = x + y

  val onePlus = curriedSum(1)_

  val twoPlus = onePlus(2)
}

object cop09CurryTestApp extends App {
  println(new CurrySample().twoPlus)
}
