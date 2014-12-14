package net.plastboks.sneikhelpers;

import com.badlogic.gdx.InputProcessor;
import net.plastboks.gameobjects.SnakeHead;
import net.plastboks.gameworld.GameWorld;

/**
 * Created by alex on 12/12/14.
 */
public class InputHandler implements InputProcessor {

    private GameWorld world;
    private SnakeHead snakeHead;

    public InputHandler(GameWorld world) {
        this.world = world;
        this.snakeHead = world.getSnakeHead();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (world.isReady()) {
            world.start();
        }
        snakeHead.onClick(screenX, screenY);
        if (world.isGameOver() || world.isHighScore()) {
            world.restart();
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 19:
                world.getSnakeHead().changeDir(SnakeHead.Dirs.NORTH);
                break;
            case 20:
                world.getSnakeHead().changeDir(SnakeHead.Dirs.SOUTH);
                break;
            case 21:
                world.getSnakeHead().changeDir(SnakeHead.Dirs.WEST);
                break;
            case 22:
                world.getSnakeHead().changeDir(SnakeHead.Dirs.EAST);
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
