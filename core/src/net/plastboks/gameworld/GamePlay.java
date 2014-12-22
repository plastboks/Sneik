package net.plastboks.gameworld;

import net.plastboks.gameobjects.Autonomous;
import net.plastboks.gameobjects.Bird;
import net.plastboks.gameobjects.Mouse;

import java.util.LinkedList;

/**
 * Created by alex on 12/22/14.
 */
public class GamePlay {

    private LinkedList<Autonomous> food;

    public GamePlay() {
        food = new LinkedList<Autonomous>();
        initFood();
    }

    private void initFood() {
        food.add(new Bird(15, 15));
        food.add(new Mouse(15, 15));
    }

    public LinkedList<Autonomous> getFood() { return food; }
}
