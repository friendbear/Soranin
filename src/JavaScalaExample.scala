import scala.beans.BeanProperty

/**
  * Created by kumagai on 2016/06/25.
  */
object JavaScalaExample extends App {

  // 191
  val file = new java.io.File("sample.txt")

  val flag = if (file.exists) file.delete else false
  if (flag) println("file deleted")

  val temp = java.io.File.createTempFile("temp", "tmp")


  // 194 ScalaとJavaのコレクション型を変換する
  // javaのリストを取得するメソッド
  def getList: java.util.List[String] = {
    val list = new java.util.ArrayList[String]
    list.add("Scala")
    list
  }

  import scala.collection.JavaConversions._
  getList.foreach{ e =>
    println(e)
  }

}



