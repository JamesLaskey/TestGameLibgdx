package gdx

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture, GL20, OrthographicCamera}
import game_logic.{SolidBlock, OccupiedBlock, GameInstance, Robot}

/**
  * Created by emily on 4/29/16.
  */
class WorldScreen(game: RobotFortress) extends Screen {
  val camera = new OrthographicCamera()
  camera.setToOrtho(false)

  val gameInstance = GameInstance

  override def hide(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {}

  override def dispose(): Unit = {}

  override def pause(): Unit = {}

  override def render(delta: Float): Unit = {

    GameInstance.tick()


    Gdx.gl.glClearColor(0.1f, .8f, .3f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update();
    game.batch.setProjectionMatrix(camera.combined);
    game.batch.begin()
    renderGameInstance(game.batch)
    game.batch.end()
  }

  val solidBlockImage = new Texture(Gdx.files.internal("assets/solid.png"))
  val robotImage = new Texture(Gdx.files.internal("assets/robot.png"))
  val gridSize = 40

  def renderGameInstance(batch: SpriteBatch): Unit = {
    // draw the map
    val map = GameInstance.map.internalMap
    for (((x, y, z), cell) <- map) {
      cell match {
        case OccupiedBlock(mobile, static) => {

        }
        case SolidBlock(block) => {
          batch.draw(solidBlockImage, x * gridSize, z * gridSize)
        }
      }
    }
  }

  override def show(): Unit = {}

  override def resume(): Unit = {}
}