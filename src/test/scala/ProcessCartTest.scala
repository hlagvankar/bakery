import com.au.bakery.service.ProcessCart
import com.au.bakery.utils.Items
import org.scalatest.{FlatSpec, Matchers}

class ProcessCartTest extends FlatSpec with Matchers {
  val pc = new ProcessCart

  "filterRecords" should "filter record" in {
    val items = Seq(Items(10, "VS5"), Items(14, "ABC"))
    val filter = pc.filterRecords(items)
    filter should be (Seq(Items(10, "VS5")))
  }

  "processOrders" should "calculate breakdown of order" in {
    val items = Seq(Items(10, "VS5"), Items(14, "MB11"), Items(13, "CF"))
    val po = pc.processOrders(items)
  }

}
