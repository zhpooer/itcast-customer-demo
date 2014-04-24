
import java.util.ResourceBundle
import java.sql.DriverManager
import scala.io.Source
import java.io.File
import io.zhpooer.util.ConnManager

object HelloWorld extends App {
  println("Hello World")

  /// Class.forName("org.apache.derby.jdbc.EmbeddedDriver")
  val conn = ConnManager.getInstance().getConnection()
  val stmt = conn.createStatement()
  val i = stmt.executeUpdate("create table if not exists test(id int primary key) ");
  println(i)
  val rs = stmt.executeQuery("show tables")

  rs.close()
  stmt.close()
  conn.close()

  def test() {
    val conn = ConnManager.getInstance().getConnection()
    val stmt = conn.createStatement()
    val rs = stmt.executeQuery("show tables")
    while(rs.next()){
      println(rs.getString(1))
    }
      
  }
  test();

}






