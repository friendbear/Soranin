package cop12.traits

/**
  * Created by kumagai on 2016/12/17.
  *
  */
class Animal
trait Furry extends Animal // 毛で覆われている
trait HasLegs extends Animal // 足がある
trait FourLegged extends HasLegs // ４本足
class Cat extends Animal with Furry with FourLegged // 猫
