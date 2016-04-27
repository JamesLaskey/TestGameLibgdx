package Map

/**
  * Created by jim on 4/26/16.
  */
object GameMap {
  var internalMap : Map[(Int, Int, Int), MapCell] = generateRandomMap

  val xSize = 100
  val ySize = 100
  val zSize = 100

  def generateRandomMap : Map[(Int, Int, Int), MapCell] = {
    var map : Map[(Int, Int, Int), MapCell] = Map()
    for (i <- 1 to xSize) {
      for (j <- 1 to zSize) {
        map = map + ((i, 50, j) -> SolidBlock(new TestBlock()))
      }
    }
    map
  }

}
