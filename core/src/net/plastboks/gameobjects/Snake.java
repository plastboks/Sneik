package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/12/14.
 */
public class Snake {
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;

    private Circle boundingCircle;

    private float rotation;
    private int width;
    private int height;

    private boolean isAlive;

    public Snake(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        boundingCircle = new Circle();
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 460);

        isAlive = true;
    }

    public void update(float delta) {
        vel.add(acc.cpy().scl(delta));

        if (vel.y > 200) {vel.y = 200; }

        if (pos.y < -13) { pos.y = -13; vel.y = 0; }

        pos.add(vel.cpy().scl(delta));

        boundingCircle.set(pos.x + 9, pos.y + 6, 6.5f);

        if (vel.y < 0) {
            rotation -= 600 * delta;
            if (rotation < -20) {
                rotation =  -20;
            }
        }

        if (isFalling() || !isAlive) {
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }
        }
    }

    public void onClick() {
        if (isAlive) {
            AssetLoader.flap.play();
            vel.y = -150;
        }
    }

    public void onRestart(int y) {
        rotation = 0;
        pos.y = y;
        vel.x = vel.y = acc.x = 0;
        acc.y = 460;
        isAlive = true;
    }

    public void die() {
        isAlive = false;
        vel.y = 0;
    }

    public void decelerate() {
        acc.y = 0;
    }

    public void moveLeft() { vel.x = 10; }
    public void moveRight() { vel.x = -10; }
    public void moveUp() { vel.y = -10; }
    public void moveDown() { vel.y = 10; }

    public boolean isFalling() { return vel.y > 110; }
    public boolean shouldntFlap() { return vel.y > 70 || !isAlive; }

    public boolean isAlive() { return isAlive; }

    public float getX() { return pos.x; }
    public float getY() { return pos.y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getRotation() { return rotation; }
    public Circle getBoundingCircle() { return boundingCircle; }
}
