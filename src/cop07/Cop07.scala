package cop07

import java.io.InputStreamReader

/**
  * Created by kumagai on 2016/12/12.
  */
object Cop07 extends App {

  val filename: String =
    if (!args.isEmpty) args(0)
    else "default.txt"

  for (i <- 1 to 4) println(f"Itelation $i")
  for (i <- 1 until 4) println(f"Itelation $i")

  val filesHere = (new java.io.File("/Users/kumagai")).listFiles()
  for (file <- filesHere if file.getName.endsWith(".scala"))
    println(file)

  for (file <- filesHere
       if file.isFile
       if file.getName.endsWith(".scala")
  )
    println(file)

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  // for式の途中での代入
  def grep(pattern: String) =
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    } println(file + ":" + trimmed)

  grep(".*gcd.*")

  def scalaFiles(): Array[java.io.File] = {
    for {
      file <- filesHere
      if file.getName.endsWith(".scala")
    } yield file
  }

  // try式
  val n = 6
  val half = if (n % 2 == 0) n/2 else throw new RuntimeException("n must be even")

  // var付きのコードの代わりに再起を使ったコード
  def searchFrom(i: Int): Int = {
    if (args.length <= 0) -1
    else if (args(i).startsWith("-")) searchFrom(i + 1)
    else if (args(i).endsWith(".scala")) i
    else searchFrom(i + 1)
  }
  val i = searchFrom(0)

  // break
  import scala.util.control.Breaks._
  import java.io._
  val in = new BufferedReader(new InputStreamReader(System.in))
  breakable {
    while (true) {
      println("?:")
      if (in.readLine() == "") break()
    }
  }

  // 九九命令型スタイル
  def makeRowSeq(row: Int): Seq[String] =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padd = " " * (4 - prod.length)
      padd + prod
    }
  def makeRow(row: Int): String = makeRowSeq(row).mkString
  def multiTable() = {
    val tableSeq: Seq[String] =
      for (row <- 1 to 10) yield makeRow(row)
    tableSeq.mkString("\n")
  }

  println(multiTable()) // 九九テーブル出力

  // TODO: もっとシンプルに
  println(findScalaFile(new java.io.File("/Users/kumagai/IdeaProjects/Soranin/src").listFiles))
  def findScalaFile(files: Array[java.io.File]): List[String] = {
    var fileNames: List[String] = List()
    for (file <- files) {
      if (file.isDirectory) {

        fileNames = fileNames ::: findScalaFile(new java.io.File(file.getPath()).listFiles()) ::: Nil
      }
      else if (file.getName.endsWith(".scala")) {
        fileNames = fileNames :::  List(file.getName) ::: Nil
      }
    }
    fileNames
  }
}
