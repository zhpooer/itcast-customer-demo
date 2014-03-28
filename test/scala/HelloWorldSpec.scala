import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class HelloWorldSpec extends FlatSpec with ShouldMatchers{
  it should "in right" in {
    2 + 2 should be (4)
  }
}
