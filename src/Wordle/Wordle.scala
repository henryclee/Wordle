package Wordle

import scala.io.{BufferedSource, Source}

object Wordle {

  val helper: helpers = new helpers

  def readFile (filename: String) : List[String] = {
    var answerList: List[String] = List()
    val file: BufferedSource = Source.fromFile(filename)
    for (line <- file.getLines()) {
      answerList = line :: answerList
    }
    answerList
  }

  def main (args: Array[String]): Unit = {

    val dictionary: List[String] = readFile("data/dictionary.txt")
    val answerList: List[String] = readFile("data/answers.txt")

 // The first word is always the same given a static dictionary and answers, so we just use it
 // to save time on finding the best word
 /*
    var bestWord: Word = new Word("", List())
    var bestEntropy: Double = 0.0
    var bestWordString: String = ""

    for (guess <- dictionary) {
      val guessWord: Word = new Word(guess, answerList)
      val guessEntropy: Double = guessWord.entropy()
      //println ("word: " + guess + " entropy: " + guessEntropy)
      if (guessEntropy >= bestEntropy) {
        bestWordString = guess
        bestEntropy = guessEntropy
        bestWord = guessWord
      }
    }
*/

    var bestWord: Word = new Word("trace", answerList)
    var bestWordString: String = "trace"

    println ("The best first guess is: " + bestWordString)

    var response: String = helper.getResponse()

    var nextWord: Word = bestWord

    while (response != "ggggg") {

      val newAnswerList: List[String] = nextWord.returnMap().getOrElse(response,List("zzzzz"))
      //println("Possible answers: " + newAnswerList)
      println("Wordle solver has narrowed the possible solutions down to " + newAnswerList.length)

      //println("response: " + response)
      //println("newAnswerList: " + newAnswerList)
      //println("returnMap: " + nextWord.returnMap)

      //var nextWord: Word = new Word("",List())
      var bestEntropy: Double = 0.0
      var nextWordString: String = ""

      //if the AnswerList.length is 1, we've found our solution, and can skip the entropy check
      if (newAnswerList.length == 1) {
        nextWordString = newAnswerList.head
      }
      else {
        for (guess <- dictionary) {
          val guessWord: Word = new Word(guess, newAnswerList)
          val guessEntropy: Double = guessWord.entropy()
          if (guessEntropy >= bestEntropy) {
            nextWordString = guess
            bestEntropy = guessEntropy
            nextWord = guessWord
          }
        }
      }

      if (newAnswerList.length == 1) {
        println ("The solution to today's Wordle is: " + nextWordString)
        response = "ggggg"
      }
      else {
        println("The next best guess is: " + nextWordString)
        //println("Possible answers: " + nextWord.returnMap)
        response = helper.getResponse()
      }
    }

    println("Congratulations!")

  }
}
