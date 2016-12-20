package cop15.patternmatch


/**
  * Created by kumagai on 2016/12/19.
  */

sealed abstract class Expr // シールドクラスにすることによりcaseクラスのサブクラスは定義できなくする
// valプレフィックスをつける。
// toString,hashCode,equalsの自然な実装を追加してくれる。
// copyメソッドを追加してくれる
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object PatternMatch {

  /** 定数パターン
    * ==で比較して定数と等しい値にマッチ
    */
  /** 変数パターン
    * eは全ての値にマッチする。
    */
  /** コンストラクタパターン
    *
    * @param expr
    * @return
    */
  def simplifyTop(expr: Expr): Expr = {
    val e = expr match {
      case UnOp("-", UnOp("-", e)) => e
      case BinOp("+", e, Number(0)) => e
      case BinOp("*", e, Number(1)) => e
      case _ => expr
    }
    e.ensuring(e.isInstanceOf[Expr], "型エラー")
  }

  /** 定数パターン */
  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }
  /** コンストラクタパターン
    * MatchErrorが発生する
    */
  def noMatch(expr: Expr) = {
    expr match {
      case BinOp(op, left, right) =>
        println(expr + " is a binary operation")
      case _  // 全てにマッチ
        => //何もしない処理
        // このケースがなければMatchErrorが発生する
    }
  }

  /** シーケンスパターン
    * 先頭0 の３つのリストにマッチ
    */
  def seqMatch(seq: Any) = {
    seq match {
      case List(0, _, _) => true
      case _ => false
    }
  }
  // _* 任意個の要素
  def anySeqMatch(seq: Any) = {
    seq match {
      case List(_*) => true
      case _ => false
    }
  }

  /** タプルパターン
    *
    */
  def tupleDemo(expr: Any) = {
    expr match {
      case (_,_,_) => true
      case _ => false
    }
  }

  /** 型付パターン
    * 方テストとかたキャストの便利な代用品として使える
    */
  def generalSize(x: Any) = x match {
    case s: String => s.length
    case m: Map[_,_] => m.size
    case _ => -1
  }

  /** 型消去
    *
    */
  def isStringArray(x: Any) ={
    x match {
      case a: Array[String] => true
      case _ => false
    }
  }

  /** 型束縛
    *
    */
  def typeMatch(expr: Expr) = expr match {
    case UnOp("abs", e @ UnOp("abs", _)) => e
    case e @ Var(_) => e  // 型束縛したい型の前に@をつける
  }

  /** パターンガード
    *
    */
  def simplifyAdd(expr: Any) = expr match {
    // case BinOp("+", x, x) => BinOp("*", x, Number(2)) <== x,xを２つ（パターン変数）以上登場させることはできない
    case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) // パターンガードを使うとエラーは起きない
    case n: Int if 0 < n => -n // パターンガード例 整数値のみ一致する
    case s: String if s.startsWith("s") => s // 先頭の文字がsになっている文字列のみにマッチする
    case _ =>
  }

  /** パターンのオーバーラップ
    * パターンは書かれた順序でテストされる
    */
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) => simplifyAll(e)
    case BinOp("+", e, Number(0)) => simplifyAll(e)
    case BinOp("*", e, Number(1)) => simplifyAll(e)
    case UnOp(op, e) => UnOp(op, simplifyAll(e))
    case BinOp(op,l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
    case _ => expr
  }

  /** コンパイラにsealdクラスもれを黙らせる
    *
    */
  def describe(e: Expr): String = {
    (e: @unchecked) match {  // @uncheckedアノテーションを付与
      case Number(_) => "a number"
      case Var(_) => "a variable"
    }
  }

  /** オプション型
    *
    */
  def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
  }

  /** 部分関数としてのケースシーケンス
    * match句がないことがポイント
    */
  val withDefault: (Option[Int]) => Int = {
    case Some(x) => x
    case None => 0
  }

  val secound: (List[Int]) => Int = {
    case x :: y :: _ => y
    case List(x, _*) => x // TODO: Intの場合なに返せばいいのかなSome(Int)?
  }

  val secoundPF: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }
}

object PatternMatchExampleApp extends App {

  val v = Var("x") // ケースクラスは同じ名前のファクトリーメソッドを追加する new を省略可能
  val op = BinOp("+", Number(1), v)
  val v2 = v.copy()

  PatternMatch.noMatch(op)
  println(PatternMatch.describe(true))
  println(PatternMatch.seqMatch(List(0, "2", Predef)))
  println(PatternMatch.anySeqMatch(List(0, "2", Predef)))
  println(PatternMatch.tupleDemo((0, "2", Predef)))
  println(PatternMatch.generalSize("abcdefg"))
  println(PatternMatch.generalSize(Map(1 -> "b",2 -> "a" )))
  println(PatternMatch.isStringArray(Array("aaaa"), Array("bbbbb")))
  println(PatternMatch.isStringArray(Array("bbbbb")))
  println(PatternMatch.typeMatch(Var("1")))

  // Option型
  val capitals =
    Map("France" -> "Paris", "Japan" -> "Tokyo")
  println(PatternMatch.show(capitals get "Japan"))
  println(PatternMatch.show(capitals get "France"))
  println(PatternMatch.show(capitals get "North Pole")) //case None => にヒット

  /* TODO: 解決策を探す
  val ccList = for(
    (countory, city) <- capitals
  ) yield List(countory, city)
  println(ccList)
    */

  val countries_ = for(countory <- capitals.keys) yield countory
  val cities_ = for(city <- capitals.values) yield city
  println(countries_)
  println(cities_)

  // 変数定義におけるパターン
  val myTuple = (123, "abc")
  val (number, string) = myTuple

  val exp = new BinOp("*", Number(5), Number(1))
  val BinOp(opr, left, light) = exp    // 引数を変数で取り出せる

  // 部分関数としてのケースシーケンス
  PatternMatch.withDefault(Some(10)) // => 10が帰る
  PatternMatch.withDefault(None)     // => 0が帰る
  println(PatternMatch.secound(List(22,11)))
  println(PatternMatch.secoundPF(List(22,11))) //PartialFunction
}
