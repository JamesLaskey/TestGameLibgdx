package game_logic

/**
  * Created by jim on 4/26/16.
  */
abstract class MapCell()

case class SolidBlock(block : Block) extends MapCell

case class OccupiedBlock(var mobileEntities: List[MobileEntity], var staticEntities: List[StaticEntity]) extends MapCell

