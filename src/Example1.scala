import scala.util.control.Breaks
import scala.util.matching.Regex

/**
  * Created by kumagai on 2016/05/31.
  */
object Example1 extends App{
  if (args.length > 0) println(args(1))

  val regex = """(\d{3}).*(\d{3})""".r("number1", "number2")
  val regex2 = """\d{3}""".r

  val source = """123 to 456"""

  val sb :StringBuilder = new StringBuilder

  regex2.findAllIn(source).foreach({
    s: String => {
      printf("Match is %s\n".format(s))
      sb.append(s)
    }
  })

  println(sb.toString())

  val matches: List[String] = regex2.findAllIn(source).toList

  // TODO: 2番目を置換する方法がわからない
  val result = regex.replaceAllIn(source, {
    m: Regex.Match => {
      "*" * m.group("number1").length
//      "+" * m.group("number2").length
    }
  })

  println(result)

  val str = "a,b,c,d,e,f"
  //val str :String = null
  val strList: List[String] = if (str != null) {
    str.split(",").toList
  }else{
    Nil
  }
  strList.foreach(s => println(s))

  for(s <- strList if !s.startsWith("b")){

    println("strLen=%d, str=%s".format(getLength(s), s))
  }

  // break の練習
  val mybreaks = new Breaks()
  import mybreaks.{break, breakable}

  breakable {
    for (i <- Range(1, 10); j <- Range(10, 30)) {
      if (j == 15) break() else print(i)

    }
  }

  def getLength(value: AnyRef): Int = {
    value match {
      case s: String => s.length
      case s: List[_] => s.length
      case s: Iterable[_] => s.size
      case _ => throw new IllegalAccessException()
    }
  }

  def regexMatchSample(s: String): Unit = {
    val pattern = """(\d{3})-(\d{4})""".r

    s match {
      case pattern(a, b) => {
        println("caseRegex %d, %d".format(a.toInt, b.toInt))
      }
      case _ => println("caseRegex noMatch")
    }
  }
  regexMatchSample("273-0021")

  def listMatchSample(list: List[Option[String]]): Unit = {
    list match {
      case List(Some("1"), Some("2"), Some("3")) => {print("先頭から1,2,3の３つのリスト")}
      case List(a, b, c) => {print("３つのリスト")}
      case List(_, Some("format sora"), _*) => {print("先頭1であとはなんでものリスト")}
      case _ => print("どれにもマッチしないリスト")
    }
  }

  val lin: List[String] = List("kuma", "sora", "nami", "tutomu")
  val l: List[Option[String]] = printElements(lin)
  println(l)
  listMatchSample(l)
  def printElements(list: List[String]): List[Option[String]] = {
    list match {
      case x::xs => {
        List(Some("format %s".format(x))) ::: printElements(xs) // 再帰呼び出し
      }
      case Nil => {
        List(Some("List End"))
      }
    }
  }
  // Optionの使い方
  for(fs <- l) {
    val str = if(fs.isDefined) fs.get else ""
    val str2 = fs.getOrElse("default")
  }
}
