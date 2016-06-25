import java.io.{FileNotFoundException, IOException}

import scala.io.Source

/**
  * Created by kumagai on 2016/06/16.
  */
object ScalaIOReadFile extends App {

  // 140 ファイルを読み込みたい
  val source = Source.fromFile("/tmp/readme.txt", "UTF-8")

  try {
    val linesWithIndex: Iterator[String] =
    source.getLines().zipWithIndex.map { case(l: String, i: Int) =>
      "%d: %s".format(i, l)
    }
    linesWithIndex.foreach{println(_)}

    // 1行ずつ処理する
    source.getLines().filter{_ != ""}.foreach({ // filterで空行を除く
      line: String => println(line)
    })
  } catch {
    case e: FileNotFoundException => println(e.getMessage())
    case _: Throwable => println("Error ")
  } finally {
    source.close()
  }

  val source1 = Source.fromURL("http://bears.plala.jp/")
  try {
    //source1.getLines().foreach(println(_))
    println(source1.mkString)
  } finally {
    source1.close()
  }
}
