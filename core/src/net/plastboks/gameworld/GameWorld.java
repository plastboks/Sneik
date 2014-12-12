package net.plastboks.gameworld;

import com.badlogic.gdx.Gdx;
import net.plastboks.gameobjects.Snake;

/**
 * Created by alex on 12/12/14.
 */
public class GameWorld {

    private Snake snake;

    public GameWorld(int midPointY) {
        snake = new Snake(33, midPointY - 5, 17, 12);
    }

    public void update(float delta) {
        Gdx.app.log("GameWorld", "update");
        snake.update(delta);
    }

    public Snake getSnake() {
        return snake;
    }

}
