package cop20.abstractmembers

/**
  * Created by kumagai on 2016/12/25.
  */

/** 列挙(enumeration)
  * JavaやC#は列挙のための特殊な構文要素をサポートしているが
  * Scalaは列挙のための特別な構文を持っておらず、標準ライブラリーにscala.Enumerationというクラスを持っている
  * 新しい列挙を作るにはscala.Enumerationクラスを拡張するオブジェクトを定義する
  */
object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}
object Direction extends Enumeration {
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}
object EnumLessonApp extends App {
  for (v <- Direction.values) print(f"${v}  ${v.id}  ")
}

