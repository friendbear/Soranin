package cop08

import scala.io.Source

/**
  * Created by kumagai on 2016/12/12.
  */
class Funtion {

  def processFile(filename: String, width: Int) = {

    def processLine(line: String) = {
      if (line.length >= width)
        println(f"$filename: $line.trim")
    }
    Source.fromFile(filename).getLines.foreach({
      line => processLine(line)
    })
  }

  val data: List[Int] = Range(-100, 100).toList
  val plusData: List[Int] = data.filter(p => p > 0)
  val minusData: List[Int] = data.filter(_ > 0)

  // 関数の格納
  val a = processFile _  // アンダースコアによりa.apply(param...)が呼び出される
}

object Cop08 extends App {

  val func = new Funtion()
  func.processFile(args(0), if (args(1) == null) 255 else args(1).toInt)
}
