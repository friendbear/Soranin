package cop03

import scala.io.Source

/**
  * Created by kumagai on 2016/12/10.
  */
object Cop03 extends App {

  def main(args: List[String]): Unit = {
    args.foreach(s=> cat(s))
  }

  // catコマンド実装
  def cat(inputPath: String): Unit = {
    if (inputPath.isEmpty()) println("Please enter filename")
    val lines = Source.fromFile(inputPath).getLines().toList

    // ラインの最大幅を計算
    val longestLine = lines.reduceLeft{
      (a, b) => if (a.length > b.length) a else b
    }
    for (line <- Source.fromFile(inputPath).getLines()) {
      println(line.length + " " + line)
    }
  }
}
