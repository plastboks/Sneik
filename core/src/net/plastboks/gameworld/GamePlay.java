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
        add(new Bird(15, 15));
        add(new Mouse(15, 15));
    }

    private void remove(Autonomous a) { food.remove(a); }
    private void removeRandom() {

    }
    private void add(Autonomous a) { food.add(a); }

    public void increment() {
        inc++;
        snake.eat();
        //if (inc % 5 == 0) { add(new Bird(15, 15)); }
        //if (inc % 7 == 0) { add(new Mouse(15, 15)); }
    }

    public LinkedList<Autonomous> getFood() { return food; }
}
