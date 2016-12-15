package cop08

/**
  * Created by kumagai on 2016/12/15.
  */
object Clojer extends App {

  var more = 1000

  val addMore = (x: Int) => x + more

  addMore(10)

  val someNumbers = List(-11, -10, -5, 0, 5, 10)

  var sum = 0
  someNumbers.foreach{
    sum += _
  }
  println(sum)

  def addIncreaser(more: Int) = (x: Int) => x + more

  val more100 = addIncreaser(100)
  println(more100(50))

  def echo(args: String*) = for (arg <- args) println(arg)

  val arr = Array("What 's", "up", "doc?")
  echo(arr: _*) // Array -> String*に変換指示

  def printTime(out: java.io.PrintStream = Console.out): Unit = {
    val time = System.currentTimeMillis()
    out.println(f"time = $time")
  }
  printTime()
  def printTime2(out: java.io.PrintStream = Console.err, divisor: Int = 1) = {
    out.println("time2 = " + System.currentTimeMillis() / divisor)
  }
  printTime2(out =Console.out)
  printTime2(divisor = 2)
}
