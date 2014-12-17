package net.plastboks.sneikhelpers;

import com.badlogic.gdx.InputProcessor;
import net.plastboks.shared.Directions;
import net.plastboks.gameobjects.Snake;
import net.plastboks.gameworld.GameWorld;

/**
 * Created by alex on 12/12/14.
 */
public class InputHandler implements InputProcessor {

    private GameWorld world;
    private Snake snake;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.snake = world.getSnake();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isReady()) {
            world.start();
        }
        snake.onClick(screenX, screenY);
        if (world.isGameOver() || world.isHighScore()) {
            world.restart();
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 19:
                world.getSnake().changeDir(Directions.NORTH);
                break;
            case 20:
                world.getSnake().changeDir(Directions.SOUTH);
                break;
            case 21:
                world.getSnake().changeDir(Directions.WEST);
                break;
            case 22:
                world.getSnake().changeDir(Directions.EAST);
                break;

        }
        return false;
    }
    @Override
    public boolean keyUp(int keycode) { return false; }
    @Override
    public boolean keyTyped(char character) { return false; }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override
    public boolean scrolled(int amount) { return false; }
}
