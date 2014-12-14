package net.plastboks.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import net.plastboks.gameobjects.SnakeHead;
import net.plastboks.screens.GameScreen;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/12/14.
 */

public class GameWorld {

    private SnakeHead snakeHead;
    private Rectangle ground;
    private int score = 0;
    private int midPointY;

    public enum GameState { READY, RUNNING, GAMEOVER, HIGHSCORE }

    private GameState currentState;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        currentState = GameState.READY;
        snakeHead = new SnakeHead(33, midPointY - 5, 15, 15, midPointY * 2);
        ground = new Rectangle(0, midPointY + 66, GameScreen.GAME_WIDTH, 11);
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }

    }

    public void updateReady(float delta) {

    }

    public void updateRunning(float delta) {

        if (delta > .15f) { delta = .15f; }

        snakeHead.update(1);
        //sh.update(delta);

        if (snakeHead.isAlive()) {
            snakeHead.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(snakeHead.getBoundingCircle(), ground)) {
            snakeHead.die();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        snakeHead.onRestart(midPointY - 5);
        currentState = GameState.READY;
    }

    public void addScore(int inc) { score += inc; }
    public void start() { currentState = GameState.RUNNING; }

    public SnakeHead getSnakeHead() { return snakeHead; }
    public int getScore() { return score; }
    public boolean isReady() { return currentState == GameState.READY; }
    public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
    public boolean isHighScore() { return currentState == GameState.HIGHSCORE; }
}
