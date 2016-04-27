package Map

/**
  * Created by jim on 4/26/16.
  */
abstract class MapCell() {

}

case class SolidBlock(block : Block) extends MapCell {

}

case class EntityList(entities : List[Entity]) extends MapCell {

}


