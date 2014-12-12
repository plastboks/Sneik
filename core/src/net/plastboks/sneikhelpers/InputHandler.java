package net.plastboks.sneikhelpers;

import com.badlogic.gdx.InputProcessor;
import net.plastboks.gameobjects.Snake;

/**
 * Created by alex on 12/12/14.
 */
public class InputHandler implements InputProcessor {

    private Snake snake;

    public InputHandler(Snake snake) {
        this.snake = snake;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        snake.onClick();
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
