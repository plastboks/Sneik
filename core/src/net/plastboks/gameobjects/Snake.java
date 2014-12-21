package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import net.plastboks.shared.Directions;

import java.util.LinkedList;

/**
 * Created by alex on 12/12/14.
 */
public class Snake extends Creature {

    private LinkedList<Node> body;

    public Snake(float x, float y, int width, int height, int gameHeight) {
        super(x, y, width, height);
        setGameHeight(gameHeight);
        setAlive(true);

        this.body = new LinkedList<Node>();
    }

    private void pushToBody(int max) {
        if (body.size() >= max) { body.removeFirst(); }
        body.add(new Node(new Vector2(getX(), getY()), getDir()));
    }

    public void changeDir(Directions d) {
        switch (d) {
            case NORTH:
                if (getDir() != Directions.SOUTH) {
                    setDir(d);
                    setRotation(Directions.NORTH);
                }
                break;
            case SOUTH:
                if (getDir() != Directions.NORTH) {
                    setDir(d);
                    setRotation(Directions.SOUTH);
                }
                break;
            case WEST:
                if (getDir() != Directions.EAST) {
                    setDir(d);
                    setRotation(Directions.WEST);
                }
                break;
            case EAST:
                if (getDir() != Directions.WEST) {
                    setDir(d);
                    setRotation(Directions.EAST);
                }
                break;
        }
    }

    public void update(float delta) {
        float lvl = 1.5f;
        pushToBody(30);
        move(delta + lvl);
    }

    public void onClick(float x, float y) {
        if (isAlive()) {
            switch (getDir()) {
                case EAST:
                case WEST:
                    if (y > getY()) {
                        changeDir(Directions.SOUTH);
                    } else {
                        changeDir(Directions.NORTH);
                    }
                    break;
                case NORTH:
                case SOUTH:
                    if (x > getX()) {
                        changeDir(Directions.EAST);
                    } else {
                        changeDir(Directions.WEST);
                    }
                    break;
            }
        }
    }

    public void onRestart(int y) {
        setRotation(Directions.WEST);
        setY(y);
        setAlive(true);
    }

    public LinkedList<Node> getBody() { return body; }

    public boolean collides(Creature c) {
        return Intersector.overlaps(getBoundingCircle(), c.getBoundingCircle());
    }
}
