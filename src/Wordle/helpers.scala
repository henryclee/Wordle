package Wordle

import scala.io.StdIn.readLine

class helpers {

  //response assumes the guess and the answer are the same length
  def response (guess: String, answer: String): String = {
    var retList: List[String] = List()
    var retVal: String = ""
    //checks if strings match
    if(guess.equalsIgnoreCase(answer)){
      retList = populateListWithString("g", guess.length)
    }

    else{
      //check for greens and update strings
      var newGuess:String = guess
      var newAnswer:String = answer
      for(i <- 0 until guess.length){
        if(guess.substring(i, i+1).equals(answer.substring(i, i+1))){
          retList = retList :+ "g"
          newGuess = newGuess.drop(1)+"*"
          newAnswer = newAnswer.drop(1)+"*"
        }
        else{
          retList = retList :+ "*"
          newGuess = newGuess.drop(1)+newGuess.head
          newAnswer = newAnswer.drop(1)+newAnswer.head
        }
      }


      //check for yellows(not entirely sure why this part works)
      //loop through all letters in new guess
      for(i <- 0 until newGuess.length){
        //if new answer contains the letter, change corresponding value in retList to the y, remove the letter from new answer
        val letter: String = newGuess.substring(i,i+1)
        if(letter!="*") {
          if (newAnswer.contains(letter)) {
            retList = retList.updated(i, "y")
          }
          else {
            retList = retList.updated(i, "w")
          }
          newAnswer = newAnswer.replaceFirst(letter, "-")
        }
      }
    }
    for(i <- retList) {
      retVal+=i
    }
    retVal

  }
  def gwy_checker(response: String): Boolean = {
    for(i <- response){
      if(!"gwy".contains(i)) {
        return false
      }
    }
    true

  }

  def getResponse(): String = {

    var valid: Boolean = false
    var response: String = ""

    println("Now we need the response, in the form of a 5 letter string of g (green), w (white/gray), and y (yellow)")
    println("For example: ggwwg, or wyggw")

    while (!valid) {
      println("What was the response? ")
      response= readLine()
      if ((response.length == 5) && gwy_checker(response)) {
        valid = true
      }
    }
    response
  }


  //generates a list of string str of size num
  def populateListWithString(str: String, num: Int): List[String] ={
    var retVal:List[String] = List()
    for(i <- 0 until num){
      retVal = retVal:+str
    }
    retVal
  }

}
