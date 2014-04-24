name := "servlet-demo2"

version := "1.0.0"

scalaVersion := "2.10.3"

seq(webSettings :_*)

scalaSource in Compile := baseDirectory.value / "src/scala"

javaSource in Compile := baseDirectory.value / "src/java"

scalaSource in Test := baseDirectory.value / "test/scala"

javaSource in Test := baseDirectory.value / "test/java"

resourceDirectory in Compile := baseDirectory.value / "conf"

resourceDirectory in Test := baseDirectory.value / "test/conf"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container"

libraryDependencies += "org.eclipse.jetty" % "jetty-plus" % "9.1.0.v20131115" % "container"

libraryDependencies += "org.eclipse.jetty" % "jetty-jsp" % "9.1.0.v20131115" % "container"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.5" % "provided"

libraryDependencies += "javax.servlet.jsp" % "javax.servlet.jsp-api" % "2.3.1" % "provided"

libraryDependencies += "javax.servlet" % "jstl" % "1.2"

libraryDependencies += "org.apache.tomcat" % "el-api" % "6.0.39" % "provided"

libraryDependencies += "dom4j" % "dom4j" % "1.6.1"

libraryDependencies += "jaxen" % "jaxen" % "1.1.6"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.28"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.2" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test"

libraryDependencies += "commons-beanutils" % "commons-beanutils" % "1.9.1"

libraryDependencies += "org.apache.derby" % "derby" % "10.10.1.1"

libraryDependencies += "org.apache.ant" % "ant" % "1.9.3"
