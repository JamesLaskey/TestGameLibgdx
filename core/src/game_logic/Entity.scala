package game_logic



/**
  * Created by jim on 4/26/16.
  */
trait Entity {
  def tick(): Unit

}

trait StaticEntity extends Entity


trait MobileEntity extends Entity {
  protected var currentPosition: Entity.Posn

  def getCurrentPosition(): Entity.Posn = currentPosition

  //GameMap.addMobileEntityToMap(currentPosition, this)

  def move(start : Entity.Posn) : Entity.Posn

  def tick(): Unit = {
    val oldPosn = currentPosition
    val newPosn = move(this.currentPosition)

    if (oldPosn == newPosn) return

    this.currentPosition = newPosn

    GameMap.removeMobileEntityFromMap(oldPosn, this)
    GameMap.addMobileEntityToMap(newPosn, this)
  }
}

object Entity {
  type Posn = (Int, Int, Int)
}
