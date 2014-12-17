package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Vector2;
import net.plastboks.shared.Directions;

/**
 * Created by alex on 12/16/14.
 */
public abstract class Creature {

    private Node head;
    private int width;
    private int height;
    private int gameHeight;
    private boolean isAlive;
    private float rotation;


    public Creature(float x, float y, int width, int height) {
        head = new Node(new Vector2(x, y), Directions.NORTH);
        this.width = width;
        this.height = height;

        isAlive = true;
    }

    public void move(float delta) {
        switch (head.getDir()) {
            case NORTH:
                head.setY(head.getY() - delta);
                break;
            case EAST:
                head.setX(head.getX() + delta);
                break;
            case SOUTH:
                head.setY(head.getY() + delta);
                break;
            case WEST:
                head.setX(head.getX() - delta);
                break;
        }
    }

    public float getX() { return head.getX(); }
    public float getY() { return head.getY(); }
    public Directions getDir() { return head.getDir(); }
    public void setX(float x) { head.setX(x); }
    public void setY(float y) { head.setY(y); }
    public void setDir(Directions d) { head.setDir(d); }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setGameHeight(int height) { this.gameHeight = height; }

    public void setAlive(boolean b) { isAlive = b; }
    public boolean isAlive()  { return isAlive; }
    public void die() { setAlive(false);}

    public void setRotation(Directions d) { rotation = getRotation(d); }
    public static float getRotation(Directions d) {
        switch(d) {
            case NORTH: return -90;
            case SOUTH: return 90;
            case WEST: return 180;
            case EAST: return 0;
            default: return 0;
        }
    }

    public abstract void update(float delta);
}
