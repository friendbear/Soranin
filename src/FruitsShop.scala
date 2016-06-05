/**
  * Created by kumagai on 2016/05/25.
  */
class FruitsShop {
  def niceSale(day:Day): Unit ={
    day match {
      case MySale(salePrice) => printf("Price: %s\n".format(salePrice))
    }
  }
}

abstract case class Day(price: Double) {
  def salePrice(): Double = {
    price * 0.9
  }
}

 class Friday(override val price: Double) extends Day(price)
 class Monday(override val price: Double) extends Day(price)

object SaleValidator {
  def unapply(day: Day): Boolean = {
    day match {
      case friday: Friday => true
      case monday: Monday => true
      case _ => false
    }
  }
}

object MySale {
  def unapply(day: Day): Option[Double] = {
    day match {
      case SaleValidator() => Some(day.salePrice())
      case _ => Some(day.price)
    }
  }
}
object Example16 {
  def main(args: Array[String]): Unit ={
    new FruitsShop niceSale new Monday(500)
    new FruitsShop niceSale new Friday(500)
  }
}
