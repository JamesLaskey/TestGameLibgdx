package game_logic

import game_logic.Entity.Posn

import scala.util.Random

/**
  * Created by emily on 6/2/16.
  */
object Movement {
  type Movement = Entity.Posn => Entity.Posn
  val randomWalk: Movement = (start: Posn) => {
    var (x, y, z) = start
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
    (x, y, z)
  }
}
