import java.io.{FileInputStream, OutputStream}

import scala.annotation.implicitNotFound


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
  val book = Book("Scalaレシピ", "クマガイ", "翔泳社", 3990)
  // applyでインスタンス化
  val Book(title, author, publisher, price) = book // 抽出しで取り出す
  println("%s, %s, %s, %d".format(title, author, publisher, price))

  // 086 ケースクラス 幾つかのメソッドやコンパニオンオブジェクトを自動生成するクラス
  val msg = Message086(1, "payload")
  val Message086(id, payload) = msg // unapplyで取り出す
  if (msg.canEqual(classOf[Message086])) println("ケースクラス.canEqual 型が一致") else println("ケースクラス.canEqual 型が不一致")
  val copyMsg = msg.copy(id = msg.id, payload = msg.payload)
  if (msg.equals(copyMsg)) println("ケースクラス.equals インスタンスが一致") else println("ケースクラス.equals インスタンスが不一致")

  // 087 パッケージオブジェクト
  // TODO: package.scalaに定義するのが慣習らしいがファイル名がエラーになる

  // 088 列挙型
  val name088 = Sex.Man.toString
  // 名前取得
  val id088 = Sex.Woman.id
  // id取得
  val sex088_1 = Sex.withName("Man")
  // 名前から列挙取得
  val sex088_2 = Sex(1) // idから列挙取得
  println("列挙子 %s, %d, %s, %s".format(name088, id088, sex088_1, sex088_2))

  def printSex = Sex.values foreach println // 列挙型.values.foreach
  printSex

  // 089 ジェネリクス
  // TODO: MapやListを渡す方法
  val helloWorld089_1 = new HelloWorld089[BigInt]
  helloWorld089_1.hello(1000000000)
  val helloWorld089_2 = new HelloWorld089[String]
  helloWorld089_2.hello(("Japanese" + "American"))

  //Format083.hello(helloWorld089_1)

  // 090 型パラメータに変位を指定したい
  class NonVariant[A] {
    // 非変 TODO: ここの代入方法がわからない
    //def head: A

    def put(arg: A) = println(arg)
  }

  val v090: NonVariant[String] = new NonVariant[String]

  // +Aの場合サブクラス
  class Covariant[+A] {
    // 共変 戻り値（出力）のみ方として使用できる
    //def head: A = new A
  }

  val v090_2: Covariant[AnyRef] = new Covariant[AnyRef]
  val v090_3: Covariant[AnyRef] = new Covariant[String]

  // -Aの場合スーパークラス
  class Contravariant[-A] {
    // 反変 引数（入力）のみ型として利用できる
    def put(arg: A) = println(arg)
  }

  val v090_4: Contravariant[String] = new Contravariant[String]
  val v090_5: Contravariant[String] = new Contravariant[AnyRef]


  // 092 型パラメータにより呼び出すメソッドを定義する場合
  class HelloWorld092[A](param: A) {
    implicit val evidence = param
    // 型パラメータがStringの場合よびだせる
    def print(implicit evidence: A =:= String) = println("print method called %s".format(param))

    // Longに型変換できる場合よびだせる
    def double(implicit evidence: A => Long) = println("double method called %d".format(evidence))
  }

  val helloWorld092 = new HelloWorld092[String]("String Sample")
  // TODO:呼び出せないんですけど。。。。
  //helloWorld092.print("AAA")

  // 093 TODO: 093スキップ
  // 094 TODO: 094スキップ
  // 095 型キャスト
  val xmlString =
    """<head>
      |</head>
      |<body>
      |  <div>abc</div>
      |</body>
    """.stripMargin
/* // TODO: scala.xmlパッケージが見つからない
  val xml = scala.xml.XML.loadString(xmlString)
  if (xml.isInstanceOf[scala.xml.Elem]) {
    xml.asInstanceOf[scala.xml.Elem]
  }
  */
  // 096 Classオブジェクトを取得 classOfメソッド
  val class096 = classOf[Exception]

  // 097 型に別名を付ける
  type M[A, B] = scala.collection.mutable.Map[A, B]

  // 098 自分型アノテーション
  class Food { self =>
    val all = List("apple", "orange")
    def print = self.all foreach println
  }
  // DIのトレーニング
  Main098.main098Execute

  // 099 ダックタイピング TODO: スキップ ダックタイピング

}
//  091 制限付き型パラメータを定義
class FileStore[A <: OutputStream]

class FileStore2[A >: FileInputStream]


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

  // TODO: ジェネリクスを引数に取るメソッドでどう引数を渡せばいいかわからない
  //def hello(a: HelloWorld089[_]) = { a.hello(_)}
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

// 098
trait UserDao {
  def byId(id: Long): String
}
trait DivisionDao {
  def allData: List[String]
}
trait SampleLogic { // trait SampleLogic extends UserDao with DivisionDaoと同じ
  self: UserDao with DivisionDao =>
  def show(id: Long) = {
    // UserDaoやDivisionDaoのメソッドをよびだせる
    val user = byId(id)
    val all = allData
    println("User = %s,".format(user) + all)
  }
}
// UserDaoを実装
trait UserDaoImpl extends UserDao  {
  def byId(id: Long) = "ID: %d".format(id)
}
// DivisionDaoを実装
trait DivisionDaoImpl extends DivisionDao {
  def allData = List("Development", "Salse")
}
object Main098 extends SampleLogic with UserDaoImpl with DivisionDaoImpl {
  def main098Execute = this.show(100)
}
