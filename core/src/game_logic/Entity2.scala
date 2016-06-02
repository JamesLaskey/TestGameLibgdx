package game_logic

import scala.util.Random

/**
  * Created by emily on 6/2/16.
  */
class Entity2(val id: Int) {
  var name: Option[String] = None
  var move: Option[Movement.Movement] = None
}

object RobotFactory {
  def makeRobot(): Entity2 = {
    val robot = new Entity2(Random.nextInt())
    robot.name = Some("Robo-" + robot.id)
    robot.move = Some(Movement.randomWalk)
    robot
  }
}

class Entity3(val id: Int) {

  var components: Map[String, Any] = Map()
  def apply(component:String): Any = {
    components(component)
  }
  def contains(component:String): Boolean = {
    components contains component
  }
  def add(component:String, data:Any) = {
    components = components + (component -> data)
  }
  def remove(component:String) = {
    components = components - component
  }
}

object Entity3 {
  val NAME = "name"
  val MOVE = "move"
}

object RobotFactory3 {
  def makeRobot(): Entity3 = {
    val robot = new Entity3(Random.nextInt)
    robot.add(Entity3.NAME, "robo-" + robot.id)
    robot.add(Entity3.MOVE, Movement.randomWalk)
    robot
  }
}
