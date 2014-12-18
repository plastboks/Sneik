package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Vector2;
import net.plastboks.screens.GameScreen;
import net.plastboks.shared.Directions;

/**
 * Created by alex on 12/17/14.
 */

public class Node {
    public Vector2 v;
    public Directions dir;

    public Node(Vector2 v, Directions d) {
        this.v = v;
        this.dir = d;
    }

    /* x, y getter and setters */
    public float getX() { return v.x; }
    public float getY() { return v.y; }
    public void setX(float x) {
        v.x = x;
        if (v.x > GameScreen.GAME_WIDTH) { v.x = 0; }
        if (v.x < 0) { v.x = GameScreen.GAME_WIDTH; }
    }
    public void setY(float y) {
        v.y = y;
        if (v.y > GameScreen.getHeight()) { v.y = 0; }
        if (v.y < 0) { v.y = GameScreen.getHeight(); }
    }

    /* Direction getter and setter */
    public Directions getDir() { return dir; }
    public void setDir(Directions d) { dir = d; }
}

