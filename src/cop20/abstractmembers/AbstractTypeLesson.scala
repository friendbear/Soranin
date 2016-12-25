package cop20.abstractmembers

/**
  * Created by kumagai on 2016/12/25.
  */
object AbstractTypeLesson extends App{

/** 問題点 牛に魚を食べさせることになる
  val bessy: Animal = new Cow
  bessy eat new Fish
  */
  // 抽象型を使って再定義したクラス
  val bessy: Animal = new Cow

  // コンパイルエラー bessy.eat (new SuitableFood)
  // bessyが参照しているオブジェクトのメンバーであるSuitableFood型

  /** パス依存型 bessy.SuitableFoodのような型はパス依存型と呼ばれる。
    *
    */
  bessy.eat (new bessy.SuitableFood)

  val outer = new Outer
  val inner = new outer.Inner // パス依存型
  //val direct = new Outer#Inner // Outer#Intter型はOuterの特定のインスタンスを指定していないのでそのインスタンスを作ることはできない

  /** リファインメント型
    * 構造的サブ型を指定するにはリファインメント型を使う
    *
    * 単純に基底型（この場合はAnimal）の後ろに中華っこで囲んだ形でメンバーのシーケンスを続けるのである。
    * 中括弧内のメンバーは、基底クラスメンバーの型を規定、または改良（リファイン）する
    * 草を食べる動物という方は次のように書く
    * Animal { type SuitableFood = Grass }
    */

}

/** パス依存型
  *
  */
class Outer {
  class Inner
}
/**
class Food
abstract class Animal {
  def eat(food: Food)
}
class Grass extends Food
class Cow extends Animal {
  override def eat(food: Grass) = {}
}
class Fish extends Food
*/
/** 抽象型で適切な餌をモデリングする
  *
  */
class Food
abstract class Animal {
  type SuitableFood <: Food // Food という上限限界を持つ
  def eat(food: SuitableFood)
}
class Grass extends Food
class Cow extends Animal {
  type SuitableFood = Grass
  override def eat(food: Grass) ={}
}
class Fish extends Food


