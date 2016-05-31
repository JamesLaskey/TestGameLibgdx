package gdx

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{InputAdapter, InputProcessor, Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture, GL20, OrthographicCamera}
import game_logic._

/**
  * Created by emily on 4/29/16.
  */
class WorldScreen(game: RobotFortress) extends Screen {
  val camera = new OrthographicCamera()
  camera.setToOrtho(false)

  val inputProcessor = new MapController
  Gdx.input.setInputProcessor(inputProcessor)

  val gameInstance = GameInstance

  override def hide(): Unit = {}

  override def resize(width: Int, height: Int): Unit = {}

  override def dispose(): Unit = {}

  override def pause(): Unit = {}

  override def render(delta: Float): Unit = {

    GameInstance.tick()


    Gdx.gl.glClearColor(0.1f, .8f, .3f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    camera.update()
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
          for(mobileEntity <- mobile) {
            batch.draw(robotImage, x * gridSize, z * gridSize)
          }
        }
        case SolidBlock(block) => {
          batch.draw(solidBlockImage, x * gridSize, z * gridSize)
        }
      }
    }
  }

  override def show(): Unit = {}

  override def resume(): Unit = {}

  class MapController extends InputAdapter {

    var dragLastX = -1
    var dragLastY = -1

    override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
      dragLastX = screenX
      dragLastY = screenY
      true
    }

    override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = {
      camera.translate(dragLastX - screenX, screenY - dragLastY)
      dragLastX = screenX
      dragLastY = screenY
      true
    }
  }




}
