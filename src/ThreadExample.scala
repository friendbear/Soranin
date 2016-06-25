/**
  * Created by kumagai on 2016/06/25.
  */
object ThreadExample extends App {

  // 189 スレッドで一部の処理を非同期化
  // TODO: scala.concurrent.opsがない
  /*
  import scala.concurrent.ops._
  import java.util.concurrent.TimeUnit
  spawn {
    TimeUnit.SECONDS.sleep(1)
    println("asynchronous...")

  }
  */
  println("main block end")
}
