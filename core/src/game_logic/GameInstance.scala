package game_logic

/**
  * Created by jim on 4/26/16.
  */
object GameInstance {
  var entities: List[MobileEntity] = List()
  val map = GameMap

  entities =  new Robot(1, "robo", (0, 0, 0)) :: entities

  for (entity <- entities) {
    GameMap.addMobileEntityToMap(entity.getCurrentPosition(), entity)
  }

  def tick(): Unit = {
    for (entity <- entities) {
      entity.tick()
    }

  }


}
