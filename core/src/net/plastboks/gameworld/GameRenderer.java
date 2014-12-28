package net.plastboks.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GameRenderer(GameWorld world) {
        this.world = world;

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

    public void drawSnakeHead() {
        batcher.draw(AssetLoader.snakeHead, snake.getX(),
                snake.getY(), snake.getWidth() / 4.0f, snake.getHeight() / 4.0f,
                snake.getWidth() / 2.0f, snake.getHeight() / 2.0f, 1, 1,
                snake.getRotation(snake.getDir()));

    }

    public void drawSnakeBody() {
        for (Node n : snake.getBody()) {
            batcher.draw(AssetLoader.snakeBody, n.v.x, n.v.y,
                    snake.getWidth() / 4.0f, snake.getHeight() / 4.0f,
                    snake.getWidth() / 2.0f, snake.getHeight() / 2.0f, 1, 1, Snake.getRotation(n.dir));
        }
    }

    public void drawFood() {
        for (Autonomous a : world.getFood()) {
            batcher.draw(a.getTexture(), a.getX(), a.getY(),
                    a.getWidth() / 4.0f, a.getHeight() / 4.0f,
                    a.getWidth() / 2.0f, a.getHeight() / 2.0f,
                    1, 1, a.getRotation(a.getDir()));
        }
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, 0, GameScreen.GAME_WIDTH, GameScreen.getHeight());
        batcher.enableBlending();

        /* draw snake body */
        drawSnakeBody();
        /* draw snake */
        drawSnakeHead();
        /* draw food */
        if (!world.getGamePlay().getLock()) { drawFood(); }

        if (world.isReady()) {
            AssetLoader.shadow.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 76);
            AssetLoader.font.draw(batcher, "Touch me", (GameScreen.GAME_WIDTH / 2) - 42, 75);
        } else {
            if (world.isGameOver() || world.isHighScore()) {
                String score = world.getScore() + "";
                AssetLoader.shadow.draw(batcher, score,
                        (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 12);
                AssetLoader.font.draw(batcher, score,
                        (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 11);
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
                    AssetLoader.shadow.draw(batcher, "New high Score!", 19, 56);
                    AssetLoader.font.draw(batcher, "New high Score!", 18, 55);
                }
                AssetLoader.shadow.draw(batcher, "Try again ?", 23, 76);
                AssetLoader.font.draw(batcher, "Try again ?", 22, 75);
            }
        }
        batcher.end();
    }

    public OrthographicCamera getCam() { return cam; }
}
