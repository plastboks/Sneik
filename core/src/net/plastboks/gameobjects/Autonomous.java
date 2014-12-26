package net.plastboks.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.plastboks.screens.GameScreen;
import net.plastboks.shared.Directions;

import java.util.Random;

/**
 * Created by alex on 12/17/14.
 */
public abstract class Autonomous extends Creature implements Artificial {

    private static Random r = new Random();
    private int factor;
    private int divider;

    public Autonomous(int height, int width) {
        this(1000, 97, height, width);
    }
    public Autonomous(int factor, int divider, int height, int width) {
        super(randX(), randY(), width, height);
        this.factor = factor;
        this.divider = divider;
        r = new Random();
    }

    private static float randX() { return r.nextInt(GameScreen.GAME_WIDTH); }
    private static float randY() { return r.nextInt(GameScreen.getHeight()); }

    public void setFactor(int factor) { this.factor = factor; }
    public void setDivider(int divider) { this.divider = divider; }

    public void respawn() { setNode(randX(), randY()); }

    public void changeDir() {
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

    public abstract TextureRegion getTexture();
    public abstract void setLvl(float lvl);
    public abstract void playSound();
    public abstract int getScore();
}
