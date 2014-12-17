package net.plastboks.gameobjects;

import net.plastboks.shared.Directions;

import java.util.Random;

/**
 * Created by alex on 12/15/14.
 */
public class Mouse extends Creature implements Artificial
{

    private Random r;

    public Mouse(float x, float y, int width, int height, int gameHeight) {
        super(x, y, width, height);
        r = new Random();
    }

    public void update(float delta) {
        float lvl = 0.5f;
        move(delta + lvl);
        changeDir();
    }

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
