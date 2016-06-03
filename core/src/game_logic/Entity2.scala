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
  type NAME_t = String
  val MOVE = "move"
  type MOVE_t = Movement.Movement
}

object RobotFactory3 {
  def makeRobot(): Entity3 = {
    val robot = new Entity3(Random.nextInt)
    robot.add(Entity3.NAME, "robo-" + robot.id)
    robot.add(Entity3.MOVE, Movement.randomWalk)
    robot
  }
}

class Entity4 (val id: Int) {
  var stringComponents: Map[String,String] = Map()
  var intComponents: Map[String,Int] = Map()
  var floatComponents: Map[String,Float] = Map()

  def apply(component:String): Any = {
    if (stringComponents contains component) return stringComponents(component)
    if (floatComponents contains component) return floatComponents(component)
    if (intComponents contains component) return intComponents(component)
    return null
  }

  def getString(component:String): Option[String] = {
    stringComponents get component
  }
  def getFloat(component:String): Option[Float] = {
    floatComponents get component
  }
  def getInt(component:String): Option[Int] = {
    intComponents get component
  }
  def add(name: String, component: String): Unit = {
    stringComponents = stringComponents + (name -> component)
  }
  def add(name: String, component: Float): Unit = {
    floatComponents = floatComponents + (name -> component)
  }
  def add(name: String, component: Int): Unit = {
    intComponents = intComponents + (name -> component)
  }
  def remove(name: String): Unit = {
    if (stringComponents contains name) stringComponents = stringComponents - name
    if (intComponents contains name) intComponents = intComponents - name
    if (floatComponents contains name) floatComponents = floatComponents - name
  }

}
