name := "scala-sbt-seed"

version := "1.0.0"

scalaVersion := "2.10.3"

scalaSource in Compile := baseDirectory.value / "src/scala"

javaSource in Compile := baseDirectory.value / "src/java"

scalaSource in Test := baseDirectory.value / "test/scala"

javaSource in Test := baseDirectory.value / "test/java"

resourceDirectory in Compile := baseDirectory.value / "conf"

resourceDirectory in Test := baseDirectory.value / "test/conf"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.2" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
