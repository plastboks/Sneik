package net.plastboks.gameworld;

import net.plastboks.gameobjects.Autonomous;
import net.plastboks.gameobjects.Bird;
import net.plastboks.gameobjects.Mouse;
import net.plastboks.gameobjects.Snake;

import java.util.LinkedList;

/**
 * Created by alex on 12/22/14.
 */
public class GamePlay {

    private LinkedList<Autonomous> food;
    private Snake snake;
    private int inc;

    public GamePlay(Snake snake) {
        food = new LinkedList<Autonomous>();
        this.snake = snake;
        inc = 0;
        initFood();
    }

    private void initFood() {
        food.add(new Bird(15, 15));
        food.add(new Mouse(15, 15));
    }

    public void increment() {
        inc++;
        snake.eat();
    }

    public LinkedList<Autonomous> getFood() { return food; }
}
