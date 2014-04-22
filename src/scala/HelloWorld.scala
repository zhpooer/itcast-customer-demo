
import java.util.ResourceBundle

object HelloWorld extends App {
  println("Hello World")
  val rb = ResourceBundle.getBundle("jdbccfg");
	    System.out.println(rb.getString("driverClass"));
}

