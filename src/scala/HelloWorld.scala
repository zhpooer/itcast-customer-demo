
import java.util.ResourceBundle
import java.sql.DriverManager
import scala.io.Source
import java.io.File

object HelloWorld extends App {
  println("Hello World")


  /// Class.forName("org.apache.derby.jdbc.EmbeddedDriver")
  println(getClass().getResource("jdbc.conf"))
  Source.fromFile("jdbc.conf").foreach(println);
}






