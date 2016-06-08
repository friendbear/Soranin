/**
  * Created by kumagai on 2016/06/08.
  */
object Example4 extends App {

  // トレイトの練習
  val traitWithSample = new TraitWithSample
  val inner = new traitWithSample.TraitInnerClass(62.4f)
  println(traitWithSample.name + traitWithSample.value + "weight %f".format(inner.weight))

  // トレイトのインスタンスを生成
  val sample = new TraitString {
    def string = "トレイトを実装"
  }
  println(sample.string)

  /*
  // トレイトの抽象メソッドの拡張
  val executor = new OutputConsole("トレイトの抽象メソッドの拡張") with DefaultExecutor
  executor.handle
  */

  // 081
  val o081 = new MainClass081
  o081.print()
  // 082
  val o082 = new ClassSample082
  println(o082.hello) // lazyをつけないとnullになる
  // 083 シングルトン
  Format083.format("フォーマットストリング083")

  // 084 コンパニオンオブジェクト シングルトンを定義
  val dBAccess084 = DBAccess084("jdbc://", "root", "test")
}


trait TraitSample {
  val value: String
  def name = "TraitSample"
  class TraitInnerClass(val weight: Float) // トレイトの中でクラス定義できる
}
class TraitWithSample extends TraitSample {

  val value: String = "Trait With Sample" // override は省略できる

  override def name = "Trait With Sample Method"
}

trait TraitString {
  def string: String
}

trait Executor {
  def handle()
}

/**
  * 080 このトレイトをミックスインできるのは
  * Executorトレイトをミックスインするクラスやインスタンスのみ
  */
trait DefaultExecutor extends Executor {
  // Executor実行前後にログを出力する

  abstract override def handle() = {

    println("Executor Start")

    super.handle()

    println("Executor Stop")
  }
}
/*
class OutputConsole(param: String) extends DefaultExecutor {

  // TODO: 以下のエラーが実行時に出て解決できない
  /**
    * Error:(60, 16) overriding method handle in trait DefaultExecutor of type ()Unit;
 method handle needs `abstract override' modifiers
  override def handle() = println(param)
               ^
    */
  override def handle() = println(param)
}
*/

// 081 同じメソッドをもつクラスやトレイトをミックスインした場合どちらのメソッドを呼び出すか指定できる
class SuperClass081 {
  def print () = println("SuperClass081")
}
trait TraitSample081 {
  def print() = println("TraitSample081")
}

class MainClass081 extends SuperClass081 with TraitSample081 {

  override def print() = {
    super.print() // => Trait
    super[SuperClass081].print() // => Super
    super[TraitSample081].print() // => Trait
  }
}

// 082 lazy トレイトのコンストラクタが実行されるまではまだ初期化されない点に注意
trait TraitSample082 {
  // 抽象フィールド
  val value: String

  // 抽象フィールドを利用
  lazy val hello = "Hello 082 " + value // lazy をとるとClassSample082で初期化されないのでnull
}
class ClassSample082 extends TraitSample082 {
  // 抽象フィールドを実装
  val value = "Scala"
}

// 083 シングルトン
object Format083 {
  def format(input: String) = println("Format String is %s".format(input))
}

// 084 コンパニオンオブジェクト apply
// private コンストラクタな為、ファクトリメソッドのみインスタンス生成が可能
class DBAccess084 private (url: String, user: String, password: String) {
  def parameters(): List[String] = List(url, user, password)
}
object DBAccess084 {
  // applyはオブジェクト名(引数)で呼び出すことができる特殊なメソッド
  def apply(url: String, user: String, password: String): DBAccess084 = new DBAccess084(url, user, password)
}

