package bears

import scala.annotation.meta.getter

//import javax.persistence._

/**
  * Created by kumagai on 2016/06/25.
  */
/* 197 ScalaのプロパティにJavaから読み取り可能なアノテーションをつけたい
 うまくインポートできない
case class Message(
  // idメソッドに@id, payloadメソッドに@Columnが付与される
                  @(Id @getter) id: Int,
                  @(Column @getter)(length = 120, nullable = false) payload: String )
*/
