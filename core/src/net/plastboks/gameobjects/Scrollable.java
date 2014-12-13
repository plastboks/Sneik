package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alex on 12/13/14.
 */
public abstract class Scrollable {
    protected Vector2 pos;
    protected Vector2 vel;
    protected int width, height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        pos = new Vector2(x, y);
        vel = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        pos.add(vel.cpy().scl(delta));
        if (pos.x + width < 0) { isScrolledLeft = true; }
    }

    public void reset(float newX) {
        pos.x = newX;
        isScrolledLeft = false;
    }

    public void stop() { vel.x = 0; }

    public boolean isScrolledLeft() { return isScrolledLeft; }

    public float getTailX() { return pos.x + width; }

    public float getX() { return pos.x; }

    public float getY() { return pos.y; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
