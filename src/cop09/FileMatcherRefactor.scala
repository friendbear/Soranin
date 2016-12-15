package cop09

/**
  * Created by kumagai on 2016/12/15.
  */
object FileMatcherRefactor { // コンパニオンオブジェクト
  def apply(filePath: String = ".") : FileMatcherRefactor ={
    new FileMatcherRefactor(filePath)
  }
}
class FileMatcherRefactor(filePath: String) {

  val files = (new java.io.File(filePath)).listFiles()

  private def findFiles(query: String, matcher: (String, String) => Boolean): Array[String] = {
    for (file <- files if matcher(file.getName, query)) yield file.getName
  }

  def filesEnding(query: String) = {
    findFiles(query, _.endsWith(_))
  }

  def filesContaining(query: String) = findFiles(query, _.contains(_))

  def filesRegex(query: String) = findFiles(query, _.matches(_))

}

object cop09TestApp extends App {

  FileMatcherRefactor("/tmp").filesContaining("a").foreach{
    printf(_)
  }
}
