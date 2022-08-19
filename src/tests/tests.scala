package tests

import Wordle.helpers
import org.scalatest._

class tests extends FunSuite {

  test("Testing response method"){
    val helper: helpers = new helpers()

    assert(helper.response("hello", "hello").equals("ggggg"))
    assert(helper.populateListWithString("*", 5)==List("*","*","*","*","*"))
    assert(helper.response("hreee","house")=="gwwwg")
    assert(helper.response("hroee", "house")=="gwywg")
    assert(helper.response("hhhhh", "hhhhh")=="ggggg")
    assert(helper.response("y", "y")=="g")


    assert(helper.response("abcce","acbbb") == "gyyww")
    assert(helper.response("abbcd","ggggg") == "wwwww")
    assert(helper.response("trace","xtxxx") == "ywwww")


  }

}
