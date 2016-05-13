package game_logic

/**
  * Created by jim on 4/26/16.
  */
trait Entity {
  def tick(): Unit

}

trait StaticEntity extends Entity {

}

trait MobileEntity extends Entity {
  var posn: (Int, Int, Int)
  def move(): Unit
  def tick(): Unit = {
    val oldPosn = posn
    this.move()
    val newPosn = posn

    if (oldPosn == newPosn) return

    // remove from map
    GameMap.internalMap(oldPosn) match {
      case OccupiedBlock(mobile, static) => {
        val newBlock = OccupiedBlock(mobile.filterNot(x => x == this), static)
        if (newBlock.mobileEntities.isEmpty && newBlock.staticEntities.isEmpty) {
          GameMap.internalMap = GameMap.internalMap - posn
        } else {
          GameMap.internalMap = GameMap.internalMap updated (oldPosn, newBlock)
        }
      }
    }

    // put in map
    GameMap.internalMap get newPosn match {
      case Some(OccupiedBlock(mobile, static)) => {
        GameMap.internalMap = GameMap.internalMap + (newPosn -> OccupiedBlock(this :: mobile, static))
      }
      case Some(x) => {
        throw new Exception("trying to move entity into invalid block")
      }
      case None => {
        GameMap.internalMap = GameMap.internalMap + (newPosn -> OccupiedBlock(this :: List(), List()))
      }
    }
  }
}
