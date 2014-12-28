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
    private boolean lock;

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

    private void remove(Autonomous a) { food.remove(a); }
    private void removeRandom() {

    }

    private void addFood()
    {
        lock = true;
        //if (inc % 5 == 0) { food.add(new Bird(15, 15)); }
        //if (inc % 7 == 0) { food.add(new Mouse(15, 15)); }
        lock = false;
    }

    public void increment(Autonomous a) {
        inc++;
        snake.incrementBodySizeBy(4);
        if (++inc % 5 == 0) { snake.incrementSpeed(); }
        addFood();
    }

    public boolean getLock() { return lock; }
    public LinkedList<Autonomous> getFood() { return food; }
}
