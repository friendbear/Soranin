package cop09

import java.io.PrintWriter
import java.io.File

/**
  * Created by kumagai on 2016/12/15.
  */
object StructSample {

  def withPrintWriter(file: File, op: (PrintWriter) => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}
object StructLoanSample {

  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object StructSampleApp extends App {

  StructSample.withPrintWriter(
    new File("/tmp/struct.txt"),
    op => {
      op.print(
        """
          |StructSample.scala
          |これは
          |色々な
          |構造のテストです
        """.stripMargin)
    }
  )
  StructLoanSample.withPrintWriter(
    new java.io.File("/tmp/structloan.txt")){
    writer => {
      writer.println(
        """
          |ローンタイプの構造化
        """.stripMargin)
    }
  }
}
