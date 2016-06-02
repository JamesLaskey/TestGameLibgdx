package game_logic

/**
  * Created by emily on 5/31/16.
  */
class Workstation extends StaticEntity {

  var active = false

  def activate(): Unit = {
    this.active = true
  }


  override def tick(): Unit = ???
}
