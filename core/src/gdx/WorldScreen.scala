package gdx

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.input.GestureDetector.{GestureAdapter, GestureListener}
import com.badlogic.gdx.{InputAdapter, InputProcessor, Gdx, Screen}
import com.badlogic.gdx.graphics.{Texture, GL20, OrthographicCamera}
import game_logic._

/**
  * Created by emily on 4/29/16.
  */
class WorldScreen(game: RobotFortress) extends Screen {
  val camera = new OrthographicCamera()
  camera.setToOrtho(false)

  val inputProcessor = new MapGestures
  Gdx.input.setInputProcessor(new GestureDetector(inputProcessor))

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

  val solidBlockImage = new Texture(Gdx.files.internal("solid.png"))
  val robotImage = new Texture(Gdx.files.internal("robot.png"))
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

  class MapGestures extends GestureAdapter {

    var dragLastX = -1
    var dragLastY = -1

    var initialZoom = 1f

    override def touchDown(x : Float, y : Float, pointer : Int, button : Int) : Boolean = {
      initialZoom = camera.zoom
      false
    }

    override def pan(x : Float, y : Float, deltaX : Float, deltaY : Float) : Boolean = {
      camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom)
      true
    }

    override def zoom(originalDistance : Float, newDistance : Float): Boolean = {
      val ratio = originalDistance / newDistance
      camera.zoom = initialZoom * ratio
      true
    }
  }




}
