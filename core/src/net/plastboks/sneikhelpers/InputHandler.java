package net.plastboks.sneikhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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

        snake.onClick();

        if (world.isGameOver() || world.isHighScore()) {
            world.restart();
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}