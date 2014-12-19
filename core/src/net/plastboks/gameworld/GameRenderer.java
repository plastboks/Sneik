package net.plastboks.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.plastboks.gameobjects.*;
import net.plastboks.screens.GameScreen;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/12/14.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private Snake snake;

    private int midPointY;

    public GameRenderer(GameWorld world, int midPointY) {
        this.world = world;

        this.midPointY = midPointY;

        this.cam = new OrthographicCamera();
        cam.setToOrtho(true, GameScreen.GAME_WIDTH, 204);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
    }

    private void initGameObjects() {
        snake = world.getSnake();
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();

        batcher.draw(AssetLoader.bg, 0, 0, GameScreen.GAME_WIDTH, GameScreen.getHeight());

        batcher.enableBlending();

        for (Node n : snake.getBody()) {
            batcher.draw(AssetLoader.snakeBody, n.v.x, n.v.y,
                    snake.getWidth() / 4.0f, snake.getHeight() / 4.0f,
                    snake.getWidth() / 2.0f, snake.getHeight() / 2.0f, 1, 1, Snake.getRotation(n.dir));
        }

        /* draw snake */
        batcher.draw(AssetLoader.snakeHead, snake.getX(),
                snake.getY(), snake.getWidth() / 4.0f, snake.getHeight() / 4.0f,
                snake.getWidth() / 2.0f, snake.getHeight() / 2.0f, 1, 1,
                snake.getRotation(snake.getDir()));

        for (Autonomous a : world.getFood()) {
            TextureRegion tr;
            if (a instanceof Bird) {
                tr = AssetLoader.bird;
            } else if (a instanceof Mouse) {
                tr = AssetLoader.mouse;
            } else {
                tr = AssetLoader.bird;
            }

            batcher.draw(tr, a.getX(), a.getY(),
                    a.getWidth() / 4.0f, a.getHeight() / 4.0f,
                    a.getWidth() / 2.0f, a.getHeight() / 2.0f,
                    1, 1, a.getRotation(a.getDir()));
        }

        if (world.isReady()) {
            AssetLoader.shadow.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 76);
            AssetLoader.font.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 75);
        } else {
            if (world.isGameOver() || world.isHighScore()) {
                if (world.isGameOver()) {
                    AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
                    AssetLoader.font.draw(batcher, "Game Over", 24, 55);
                    AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
                    AssetLoader.font.draw(batcher, "High Score:", 22, 105);

                    String highScore = AssetLoader.getHighScore() + "";

                    AssetLoader.shadow.draw(batcher, highScore,
                            (GameScreen.GAME_WIDTH / 2) - (3 * highScore.length()), 128);
                    AssetLoader.font.draw(batcher, highScore,
                            (GameScreen.GAME_WIDTH / 2) - (3 * highScore.length() - 1), 127);
                } else {
                    AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
                    AssetLoader.font.draw(batcher, "High Score!", 18, 55);
                }
                AssetLoader.shadow.draw(batcher, "Try again ?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again ?", 22, 75);
            }
            /*
            String score = world.getScore() + "";
            AssetLoader.shadow.draw(batcher, score,
                    (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, score,
                    (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 11);
            */
        }
        batcher.end();
    }
}
