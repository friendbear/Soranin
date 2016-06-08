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

  // 085 抽出子 インスタンスが持つ情報を抽出する場合
  val book = Book("Scalaレシピ", "クマガイ", "翔泳社", 3990) // applyでインスタンス化
  val Book(title, author, publisher, price) = book // 抽出しで取り出す
  println("%s, %s, %s, %d".format(title, author, publisher, price))

  // 086 ケースクラス 幾つかのメソッドやコンパニオンオブジェクトを自動生成するクラス
  val msg = Message086(1, "payload")
  val Message086(id, payload) = msg // unapplyで取り出す
  if (msg.canEqual(isInstanceOf[Message086])) println("ケースクラス.canEqual 型が一致") else println("ケースクラス.canEqual 型が不一致")
  val copyMsg = msg.copy(id = msg.id, payload = msg.payload)
  if (msg.equals(copyMsg)) println("ケースクラス.equals インスタンスが一致") else println("ケースクラス.equals インスタンスが不一致")

  // 087 パッケージオブジェクト
  // TODO: package.scalaに定義するのが慣習らしいがファイル名がエラーになる

  // 088 列挙型
  val name088 = Sex.Man.toString  // 名前取得
  val id088 = Sex.Woman.id        // id取得
  val sex088_1 = Sex.withName("Man")  // 名前から列挙取得
  val sex088_2 = Sex(1)               // idから列挙取得
  println("列挙子 %s, %d, %s, %s".format(name088, id088, sex088_1, sex088_2))
  def printSex = Sex.values foreach println // 列挙型.values.foreach
  printSex

  // 089 ジェネリクス
  // TODO: MapやListを渡す方法
  val helloWorld089_1 = new HelloWorld089[BigInt]
  helloWorld089_1.hello(1000000000)
  val helloWorld089_2 = new HelloWorld089[String]
  helloWorld089_2.hello(("Japanese" +"American"))


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

// 085
class Book private (val title: String, val author: String, val publisher: String, val price: Int)

object Book {

  // 引数にパラメータをとる
  def apply(title: String, author: String, publisher: String , price: Int): Book = new Book(
    title, author, publisher, price )

  // 引数にオブジェクトをとる
  def unapply(arg: Book): Option[(String, String, String, Int)] =
    Some(arg.title, arg.author, arg.publisher, arg.price)

}

// 086 ケースクラスの定義
case class Message086(id: Int, payload: String)

// 088 Enum
object Sex extends Enumeration {
  type Sex = Value
  val Man, Woman = Value
}

// 089 ジェネリクス総称型 パラメータ化された型の例
class HelloWorld089[A] {
  def hello[A](a: A): Unit = {
    println("ジェネリクス総称型Hello" + a)
  }
}
