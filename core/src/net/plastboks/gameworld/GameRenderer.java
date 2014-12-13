package net.plastboks.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
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
    private ScrollHandler sh;
    private Grass frontGrass, backGrass;
    private Pipe p1, p2, p3;

    private TextureRegion bg, grass;
    private Animation snakeAnimation;
    private TextureRegion snakeMid, snakeDown, snakeUp;
    private TextureRegion skullUp, skullDown, bar;

    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.world = world;

        this.gameHeight = gameHeight;
        this.midPointY = midPointY;

        this.cam = new OrthographicCamera();
        cam.setToOrtho(true, GameScreen.GAME_WIDTH, 204);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        snake = world.getSnake();
        sh = world.getSh();
        frontGrass = sh.getFrontGrass();
        backGrass = sh.getBackGrass();
        p1 = sh.getPipe1();
        p2 = sh.getPipe2();
        p3 = sh.getPipe3();
    }

    private void initAssets() {
        bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        snakeAnimation = AssetLoader.snakeAnimation;
        snakeMid = AssetLoader.snake;
        snakeDown = AssetLoader.snakeDown;
        snakeUp = AssetLoader.snakeUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
    }

    private void drawGrass() {
        batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
                frontGrass.getWidth(), frontGrass.getHeight());
        batcher.draw(grass, backGrass.getX(), backGrass.getY(),
                backGrass.getWidth(), backGrass.getHeight());
    }

    private void drawSkulls() {
        batcher.draw(skullUp, p1.getX() - 1, p1.getY() + p1.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, p1.getX() - 1, p1.getY() + p1.getHeight() + Pipe.VERTICAL_GAP, 24, 14);
        batcher.draw(skullUp, p2.getX() - 1, p2.getY() + p2.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, p2.getX() - 1, p2.getY() + p2.getHeight() + Pipe.VERTICAL_GAP, 24, 14);
        batcher.draw(skullUp, p3.getX() - 1, p3.getY() + p3.getHeight() - 14, 24, 14);
        batcher.draw(skullDown, p3.getX() - 1, p3.getY() + p3.getHeight() + Pipe.VERTICAL_GAP, 24, 14);
    }

    private void drawPipes() {
        batcher.draw(bar, p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
        batcher.draw(bar, p1.getX(), p1.getY() + p1.getHeight() + Pipe.VERTICAL_GAP,
                p1.getWidth(), midPointY + 66 - (p1.getHeight() + Pipe.VERTICAL_GAP));
        batcher.draw(bar, p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
        batcher.draw(bar, p2.getX(), p2.getY() + p2.getHeight() + Pipe.VERTICAL_GAP,
                p2.getWidth(), midPointY + 66 - (p2.getHeight() + Pipe.VERTICAL_GAP));
        batcher.draw(bar, p3.getX(), p3.getY(), p3.getWidth(), p3.getHeight());
        batcher.draw(bar, p3.getX(), p3.getY() + p3.getHeight() + Pipe.VERTICAL_GAP,
                p3.getWidth(), midPointY + 66 - (p3.getHeight() + Pipe.VERTICAL_GAP));
    }

    public void render(float runTime) {
        //Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(55/255.0f, 80/255.0f, 100/255.0f, 1);
        shapeRenderer.rect(0, 0, GameScreen.GAME_WIDTH, midPointY + 66);

        shapeRenderer.setColor(111/255.0f, 186/255.0f, Pipe.VERTICAL_GAP/255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, GameScreen.GAME_WIDTH, 11);

        shapeRenderer.setColor(147/255.0f, 80/255.0f, 27/255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, GameScreen.GAME_WIDTH, 52);

        shapeRenderer.end();

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, GameScreen.GAME_WIDTH, 43);

        drawGrass();
        drawPipes();
        batcher.enableBlending();
        drawSkulls();

        if (snake.shouldntFlap()) {
            batcher.draw(snakeMid, snake.getX(), snake.getY(),
                    snake.getWidth() / 2.0f, snake.getHeight() / 2.0f,
                    snake.getWidth(), snake.getHeight(), 1, 1, snake.getRotation());
        } else {
            batcher.draw(snakeAnimation.getKeyFrame(runTime), snake.getX(),
                    snake.getY(), snake.getWidth() / 2.0f, snake.getHeight() / 2.0f,
                    snake.getWidth(), snake.getHeight(), 1, 1, snake.getRotation());
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

            String score = world.getScore() + "";
            AssetLoader.shadow.draw(batcher, score,
                    (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 12);
            AssetLoader.font.draw(batcher, score,
                    (GameScreen.GAME_WIDTH / 2) - (3 * score.length()), 11);
        }
        batcher.end();
    }
}
