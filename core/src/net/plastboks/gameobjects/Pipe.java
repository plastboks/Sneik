package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.Random;

/**
 * Created by alex on 12/13/14.
 */
public class Pipe extends Scrollable {

    private Random r;
    private Rectangle skullUp, skullDown, barUp, barDown;
    private boolean isScored = false;

    public static final int VERTICAL_GAP = 85;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private float groundY;

    public Pipe(float x,
                float y,
                int width,
                int height,
                float scrollSpeed,
                float groundY)
    {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        barUp.set(pos.x, pos.y, width, height);
        barDown.set(pos.x, pos.y + height + VERTICAL_GAP, width,
                groundY - (pos.y + height + VERTICAL_GAP));

        skullUp.set(pos.x - (SKULL_WIDTH - width) / 2,
                pos.y + height - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(pos.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
    }
    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = r.nextInt(90) + 15;
        isScored = false;
    }

    public boolean collides(Snake snake) {
        if (pos.x < snake.getX() + snake.getWidth()) {
            return (Intersector.overlaps(snake.getBoundingCircle(), barUp)
                 || Intersector.overlaps(snake.getBoundingCircle(), barDown)
                 || Intersector.overlaps(snake.getBoundingCircle(), skullUp)
                 || Intersector.overlaps(snake.getBoundingCircle(), skullDown));
        }
        return false;
    }

    public void onRestart(float x, float scrollSpeed) {
        vel.x = scrollSpeed;
        reset(x);
    }

    public boolean isScored() { return isScored; }
    public Rectangle getSkullUp() { return skullUp; }
    public Rectangle getSkullDown() { return skullDown; }
    public Rectangle getBarUp() { return barUp; }
    public Rectangle getBarDown() { return barDown; }

    public void setScored(boolean b) { isScored = b; }
}
