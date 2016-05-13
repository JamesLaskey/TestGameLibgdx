package game_logic

import scala.util.Random

/**
  * Created by jim on 4/26/16.
  */
class Robot(val id : Int, var name : String = "", var posn: (Int, Int, Int) = (0, 0, 0)) extends MobileEntity {

  if (name.isEmpty) name = id.toString

  def randomWalk(): Unit = {
    if (!(Random.nextBoolean() && Random.nextBoolean())) {
      return
    }
    var (x, y, z) = posn
    if (Random.nextBoolean()) {
      if (Random.nextBoolean() && x < GameMap.xSize - 1) {
        x += 1
      } else if (x > 0) {
        x -= 1
      }
    } else {
      if (Random.nextBoolean() && z < GameMap.zSize - 1) {
        z += 1
      } else if (z > 0) {
        z -= 1
      }
    }
    this.posn = (x, y, z)




  }

  override def move(): Unit = randomWalk()

}

object Robot {
  def unapply(robot: Robot): Some[(Int, (Int, Int, Int))] = Some((robot.id, robot.posn))
}

