/*package GUI

import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle

object GUI extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Wordle Solver"
      width = 800
      height = 600
      scene = new Scene {
        fill = White
        content = new Rectangle {
          x = 25
          y = 40
          width = 100
          height = 100
          fill <== when(hover) choose Green otherwise Red
        },
        new Rectangle {
          x = 300
          y = 400
          width = 100
          height = 100
          fill <== when(hover) choose Green otherwise Red
        }
      }
    }
  }
}

 */
