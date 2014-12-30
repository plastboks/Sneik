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
    private int maxFoodCount = 6;

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

    private LinkedList<Autonomous> getClone() {
        return (LinkedList<Autonomous>)food.clone();
    }

    private void addFood() {
        if (food.size() > maxFoodCount) { return; }

        LinkedList<Autonomous> tmp = getClone();
        if (inc % 5 == 0) { tmp.add(new Bird(15, 15)); }
        if (inc % 7 == 0) { tmp.add(new Mouse(15, 15)); }
        food = tmp;
    }

    private void removeRandomFood() {
        if (food.size() == 1) { return; }

        LinkedList<Autonomous> tmp = getClone();
        for (int i = 0; i < tmp.size(); i++) {
            if ((int)(Math.random() * 20) % 5 == 0) { tmp.remove(i); }
        }
        food = tmp;
    }

    public void increment(Autonomous a) {
        inc++;
        snake.incrementBodySizeBy(4);
        if (++inc % 5 == 0) { snake.incrementSpeed(); }
        addFood();
        removeRandomFood();
    }

    public LinkedList<Autonomous> getFood() { return food; }
}
