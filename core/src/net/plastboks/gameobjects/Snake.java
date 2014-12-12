package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alex on 12/12/14.
 */
public class Snake {
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;

    private float rotation;
    private int width;
    private int height;

    public Snake(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 460);
    }

    public void update(float delta) {
        vel.add(acc.cpy().scl(delta));

        if (vel.y > 200) {vel.y = 200; }

        pos.add(vel.cpy().scl(delta));
    }

    public void onClick() {
        vel.y = -140;
    }

    public float getX() {
        return pos.x;
    }

    public float getY() {
        return pos.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

}
