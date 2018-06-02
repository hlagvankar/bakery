import com.au.bakery.utils.{Items, Utils}
import org.scalatest.{FlatSpec, FreeSpec, Matchers}

class UtilTest extends FlatSpec with Matchers{
  val util = new Utils

  "validateCodes" should "validate products code" in {
    val productCode = "VS5"
    assert(util.validateCodes(productCode) === true)
  }

  "validateCodes" should "fail to validate code" in {
    val productCode = "ABC11"
    assert(util.validateCodes(productCode) === false)
  }

  "validatePath" should "validate input path" in {
    val path = """E:\test.csv"""
    assert(util.validatePath(path) === true)
  }

  "validatePath" should "fail to validate path" in {
    val path = """E:\abc.text"""
    assert(util.validatePath(path) === false)
  }

  "readFile" should "read file" in {
    val filePath = "./src/test/resources/sample.csv"
    val input = util.readFile(filePath)
    input.toList should be (List(Items(10,"VS5")))
  }
}
