package net.plastboks.gameworld;

import net.plastboks.gameobjects.*;
import net.plastboks.sneikhelpers.AssetLoader;

import java.util.LinkedList;

/**
 * Created by alex on 12/12/14.
 */

public class GameWorld {

    private Snake snake;
    public enum GameState { READY, RUNNING, GAMEOVER, HIGHSCORE }
    private GameState currentState;
    private GamePlay gp;

    private int score = 0;
    private int midPointY;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;
        currentState = GameState.READY;

        snake = new Snake(33, midPointY - 5, 15, 15, midPointY * 2);

        gp = new GamePlay(snake);
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

    public void updateReady(float delta) { }

    public void updateRunning(float delta) {
        snake.update(delta);
        for(Autonomous a : gp.getFood()) {
            a.update(delta);
            if (snake.collides(a)) {
                gp.increment(a);
                a.respawn();
                addScore(a.getScore());
                a.playSound();
            }
            int i = snake.getBody().size();
            for (Node n : snake.getBody()) {
                /* prey collides with snake body */
                if (a.collides(n)) { a.changeDir(); }
                /* snake eats itself - game over - */
                if (i > snake.getWidth() && snake.collides(n)) {
                    gameOver();
                    return;
                }
                i--;
            }
        }
    }

    private void gameOver() {
        snake.die();
        AssetLoader.dead.play();
        if (getScore() > AssetLoader.getHighScore()) {
            AssetLoader.setHighScore(score);
            currentState = GameState.HIGHSCORE;
        } else {
            currentState = GameState.GAMEOVER;
        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        snake.onRestart(midPointY - 5);
        currentState = GameState.READY;
    }

    public void start() { currentState = GameState.RUNNING; }

    public void addScore(int inc) { score += inc; }
    public int getScore() { return score; }

    public Snake getSnake() { return snake; }
    public LinkedList<Autonomous> getFood() { return gp.getFood(); }
    public GamePlay getGamePlay() { return gp; }

    public boolean isReady() { return currentState == GameState.READY; }
    public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
    public boolean isHighScore() { return currentState == GameState.HIGHSCORE; }
}
