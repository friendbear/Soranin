package bears

import scala.beans.BeanProperty

/**
  * Created by kumagai on 2016/06/25.
  */
case class BookJava(@BeanProperty var title: String, @BeanProperty var price: Int)
