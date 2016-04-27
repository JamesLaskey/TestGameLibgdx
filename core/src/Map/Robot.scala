package Map

/**
  * Created by jim on 4/26/16.
  */
class Robot(id : Int, var name : String = "") {

  if (name.isEmpty) name = id.toString


}

object RobotFactory {
  def newInstance() : Robot = {
    new Robot()
  }

  def
}
