package net.plastboks.gameworld;

import net.plastboks.gameobjects.*;

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

        gp = new GamePlay();
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
                a.respawn();
                snake.eat();
                a.playSound();
            }
            for (Node n : snake.getBody()) {
                if (a.collides(n)) { a.changeDir(); }
                if (snake.collides(n)) {
                    //snake.die();
                    //AssetLoader.dead.play();
                }

            }

        }
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        snake.onRestart(midPointY - 5);
        currentState = GameState.READY;
    }

    public void addScore(int inc) { score += inc; }
    public void start() { currentState = GameState.RUNNING; }

    public Snake getSnake() { return snake; }
    public LinkedList<Autonomous> getFood() { return gp.getFood(); }

    public int getScore() { return score; }
    public boolean isReady() { return currentState == GameState.READY; }
    public boolean isGameOver() { return currentState == GameState.GAMEOVER; }
    public boolean isHighScore() { return currentState == GameState.HIGHSCORE; }
}
