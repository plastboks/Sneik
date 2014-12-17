package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alex on 12/16/14.
 */
public abstract class Creature {

    private Vector2 pos;
    private int width;
    private int height;
    private int gameHeight;
    private boolean isAlive;

    public Creature(float x, float y, int width, int height) {
        this.pos = new Vector2(x, y);
        this.width = width;
        this.height = height;

        isAlive = true;
    }

    public void incrementX() { pos.x++; }
    public void incrementY() { pos.y++; }
    public float getX() { return pos.x; }
    public float getY() { return pos.y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getRotation() { return 0; }

    public abstract void update(float delta);
}
