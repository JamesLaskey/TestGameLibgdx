package Map

import scala.util.Random

/**
  * Created by jim on 4/26/16.
  */
class Robot(id : Int, var name : String = "", var posn: (Int, Int, Int) = (0, 0, 0)) extends MobileEntity {

  if (name.isEmpty) name = id.toString

  def randomWalk(): Unit = {
    var (x, y, z) = posn
    if (Random.nextBoolean()) {
      if (Random.nextBoolean()) {
        x += 1
      } else {
        x -= 1
      }
    } else {
      if (Random.nextBoolean()) {
        z += 1
      } else {
        z -= 1
      }
    }
    this.posn = (x, y, z)

  }

  override def move(): Unit = randomWalk()
}

