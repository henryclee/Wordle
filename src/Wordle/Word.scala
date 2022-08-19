package Wordle

import Math.log

class Word (guess: String, answerList: List[String]) {

  val helper: helpers = new helpers()

  //Returns a map of response -> the answers.txt that map to that response
  def returnMap () : Map[String, List[String]] = {

    var answerMap: Map[String, List[String]] = Map()

    for (answer <- this.answerList) {
      val responseString = helper.response(guess,answer)
      if (answerMap.contains(responseString)) {
        val newList: List[String] = answer :: answerMap(responseString)
        answerMap = answerMap + (responseString -> newList)
      }
      else {
        answerMap = answerMap + (responseString -> List(answer))
      }
    }
    answerMap
  }

  //returns an entropy value for a given word, by iterating through the map of responses to potential answers.txt, and
  //summing their individual entropies, defined as -log(base2)(probability)
  def entropy() : Double = {
    val answerMap: Map [String, List[String]] = this.returnMap()
    var entropy: Double = 0.0
    val denom: Int = answerList.size
    val LOG2 = log(2)
    //denominator will be the size of the answer list
    for ((response,answerSublist) <- answerMap) {
      val probability: Double = answerSublist.length.toDouble/denom
      entropy += -log(probability)/LOG2
      //println (entropy)
      //the probability is the length of the answer sublist divided by the total number of answers.txt
    }
    entropy
  }

}
