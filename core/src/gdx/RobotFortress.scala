package gdx

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
  * Created by emily on 4/29/16.
  */
class RobotFortress extends Game {

  var batch: SpriteBatch = null

  override def create(): Unit = {
    this.batch = new SpriteBatch()
    this.setScreen(new WorldScreen(this))
  }

  override def render(): Unit = {
    super.render()
  }

  override def dispose(): Unit = {
    batch.dispose()
  }
}
