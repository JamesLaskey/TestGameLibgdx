package game_logic

/**
  * Created by jim on 4/26/16.
  */
object GameMap {

  val xSize = 10
  val ySize = 10
  val zSize = 10

  def generateRandomMap() : Map[(Int, Int, Int), MapCell] = {
    var map : Map[(Int, Int, Int), MapCell] = Map()
    for (i <- 0 until xSize) {
      for (j <- 0 until zSize) {
        map = map + ((i, 50, j) -> SolidBlock(new TestBlock()))
      }
    }
    map
  }
  var internalMap : Map[(Int, Int, Int), MapCell] = generateRandomMap()

  def removeMobileEntityFromMap(posn: Entity.Posn, entity: MobileEntity): Unit = {
    GameMap.internalMap(posn) match {
      case OccupiedBlock(mobile, static) => {
        val newBlock = OccupiedBlock(mobile.filterNot(x => x == entity), static)
        if (newBlock.mobileEntities.isEmpty && newBlock.staticEntities.isEmpty) {
          GameMap.internalMap = GameMap.internalMap - posn
        } else {
          GameMap.internalMap = GameMap.internalMap updated (posn, newBlock)
        }
      }
      case _ => throw new Exception("Entity was not in  map")
    }

  }


  def addMobileEntityToMap(posn: Entity.Posn, entity: MobileEntity): Unit = {
    internalMap get posn match {
      case Some(OccupiedBlock(mobile, static)) => {
        internalMap = internalMap updated (posn, OccupiedBlock(entity :: mobile, static))
      }
      case None => {
        internalMap = internalMap + (posn -> OccupiedBlock(entity :: List(), List()))
      }
      case Some(x) => {
        throw new RuntimeException("Tried to put entity in invalid location")
      }
    }

  }

}
