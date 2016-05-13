package game_logic

/**
  * Created by jim on 4/26/16.
  */
object GameMap {

  val xSize = 100
  val ySize = 100
  val zSize = 100

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

  def main(args: Array[String]): Unit = {
    println(GameMap.internalMap)
  }

  def addMobileEntityToMap(posn: (Int, Int, Int), entity: MobileEntity): Unit = {
    internalMap get posn match {
      case Some(OccupiedBlock(mobile, static)) => {
        internalMap = internalMap updated (posn, OccupiedBlock(entity :: mobile, static))
      }
      case None => {
        internalMap = internalMap + (posn -> OccupiedBlock(entity :: List(), List()))
      }
      case Some(x) => {
        throw new RuntimeException("Tried to create entity in invalid location")
      }
    }

  }

}
