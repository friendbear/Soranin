package cop09

/**
  * Created by kumagai on 2016/12/15.
  */
object FileMatcher {

  private def filesHere: Array[java.io.File] = (new java.io.File(".")).listFiles

  def fileEnding(query: String): Array[java.io.File] =
    for (file <- filesHere if file.getName.endsWith(query)) yield file

  def fileContaining(query: String) =
    for (file <- filesHere if file.getName.contains(query)) yield file

  def fileRegex(query: String) =
    for (file <- filesHere if file.getName.matches(query)) yield file

  def filePattern = """.(*)\.scala""".r.regex

  fileRegex(filePattern)

  def findFiles(query: String, pattern: (String, String) => Boolean) = {
    for (file <- filesHere if pattern(file.getName, query)) yield file
  }

  val matcher = (fileName: String,query: String) => {
    fileName.contains(query)
  }

  findFiles(".scala", matcher)


}
