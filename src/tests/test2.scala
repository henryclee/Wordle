package tests

import Wordle._
import org.scalatest._

class test2 extends FunSuite {

  val test: List[String] = Wordle.readFile("data/test.txt")
  println(test)

  val testWord: Word = new Word("aaaaa", List("aaaaa","aaaab","baaaaa","abcde","caaaaa"))
  println(testWord.returnMap().toString)

}
