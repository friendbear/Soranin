package cop13.pkgs

/**
  * Created by kumagai on 2016/12/18.
  */
abstract class Fruit(val name: String, val color: String)

object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")

  val menu = List(Apple, Orange, Pear)
}

import cop13.pkgs.Fruit // Fruitへのアクセスを簡単にするインポート文
import cop13.pkgs._  // 全てのメンバーへのアクセスを簡単にするインポート文
import cop13.pkgs.Fruits // Fruitsの全てのメンバーへのアクセスを簡単にするインポート文

// パッケージ名のインポート
import java.util.regex
class AstarB {
  val pat = regex.Pattern.compile("a*b")
}

//インポートセレクタ節
import Fruits.{Apple, Orange}
import Fruits.{Apple => McIntosh, Orange}


// 暗黙のインポート 拡張子が.scala の場合にインポートされる
import java.lang._
import scala._
import Predef._ //Predefオブジェクトの中の全て


/** 限定子付きアクセス修飾子に夜柔軟なアクセス保護スコープの指定
  *
  */
package bobsrockets {
  package navigation {
    // TODO:定義できん
  }
}
