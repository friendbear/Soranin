name := "Scala Exam"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.3",
  "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3"
  )


mainClass in (Compile,run) := Some("")