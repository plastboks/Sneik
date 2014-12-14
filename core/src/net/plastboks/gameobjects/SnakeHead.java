package net.plastboks.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import net.plastboks.screens.GameScreen;

import java.util.LinkedList;

/**
 * Created by alex on 12/12/14.
 */
public class SnakeHead {
    private Node head;
    private LinkedList<Node> body;
    private Circle boundingCircle;

    private float rotation;
    private int width;
    private int height;
    private int gameHeight;

    private boolean isAlive;

    public static enum Dirs {NORTH, SOUTH, EAST, WEST}

    public class Node {
        public Vector2 v;
        public Dirs dir;

        public Node(Vector2 v, Dirs d) {
            this.v = v;
            this.dir = d;
        }
    }

    public SnakeHead(float x, float y, int width, int height, int gameHeight) {
        this.width = width;
        this.height = height;
        this.gameHeight = gameHeight;

        this.body = new LinkedList<Node>();

        head = new Node(new Vector2(x,y), Dirs.NORTH);

        boundingCircle = new Circle();
        isAlive = true;
    }

    private void moveEast(int inc) {
        head.v.x += inc;
        if (head.v.x > GameScreen.GAME_WIDTH) { head.v.x = 0; }
    }

    private void moveWest(int inc) {
        head.v.x -= inc;
        if (head.v.x < 0) { head.v.x = GameScreen.GAME_WIDTH; }
    }

    private void moveNorth(int inc) {
        head.v.y -= inc;
        if (head.v.y < 0) { head.v.y = gameHeight; }
    }

    private void moveSouth(int inc) {
        head.v.y += inc;
        if (head.v.y > gameHeight) { head.v.y = 0; }
    }

    public void changeDir(Dirs d) {
        switch (d) {
            case NORTH:
                if (head.dir != Dirs.SOUTH) {
                    head.dir = d;
                    rotation = getRotation(d);
                }
                break;
            case SOUTH:
                if (head.dir != Dirs.NORTH) {
                    head.dir = d;
                    rotation = getRotation(d);
                }
                break;
            case WEST:
                if (head.dir != Dirs.EAST) {
                    head.dir = d;
                    rotation = getRotation(d);
                }
                break;
            case EAST:
                if (head.dir != Dirs.WEST) {
                    head.dir = d;
                    rotation = getRotation(d);
                }
                break;
        }
    }

    private void pushToBody(int max) {
        if (body.size() >= max) { body.removeFirst(); }
        body.add(new Node(new Vector2(head.v.x, head.v.y), head.dir));
    }

    public void update(int inc) {
        pushToBody(300);
        switch (head.dir) {
            case EAST:
                moveEast(inc);
                break;
            case WEST:
                moveWest(inc);
                break;
            case NORTH:
                moveNorth(inc);
                break;
            case SOUTH:
                moveSouth(inc);
                break;
        }
    }

    public void onClick(int x, int y) {
        if (isAlive) {
            switch (head.dir) {
                case EAST:
                case WEST:
                    if (y < head.v.y) {
                        changeDir(Dirs.NORTH);
                    } else {
                        changeDir(Dirs.SOUTH);
                    }
                    break;
                case NORTH:
                case SOUTH:
                    if (x < head.v.x) {
                        changeDir(Dirs.EAST);
                    } else {
                        changeDir(Dirs.WEST);
                    }
                    break;
            }
        }
    }

    public void onRestart(int y) {
        rotation = 0;
        head.v.y = y;
        isAlive = true;
    }

    public void die() { isAlive = false; }
    public boolean isAlive() { return isAlive; }

    public float getX() { return head.v.x; }
    public float getY() { return head.v.y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getRotation() { return rotation; }
    public LinkedList<Node> getBody() { return body; }
    public Circle getBoundingCircle() { return boundingCircle; }

    public static float getRotation(Dirs d) {
        switch(d) {
            case NORTH: return -90;
            case SOUTH: return 90;
            case WEST: return 180;
            case EAST: return 0;
            default: return 0;
        }
    }
}
