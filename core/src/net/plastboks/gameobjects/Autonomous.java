package net.plastboks.gameobjects;

import net.plastboks.screens.GameScreen;
import net.plastboks.shared.Directions;

import java.util.Random;

/**
 * Created by alex on 12/17/14.
 */
public abstract class Autonomous extends Creature implements Artificial {

    private static Random r = new Random();

    public Autonomous(int height, int width) {
        super(randX(), randY(), width, height);
        r = new Random();
    }

    private static float randX() { return r.nextInt(GameScreen.GAME_WIDTH); }
    private static float randY() { return r.nextInt(GameScreen.getHeight()); }

    public void changeDir() {
        int factor = 1000;
        int divider = 97;

        switch (r.nextInt(factor) % divider) {
            case 0:
                setDir(Directions.NORTH);
                break;
            case 1:
                setDir(Directions.EAST);
                break;
            case 2:
                setDir(Directions.SOUTH);
                break;
            case 3:
                setDir(Directions.WEST);
                break;

        }
    }
}
