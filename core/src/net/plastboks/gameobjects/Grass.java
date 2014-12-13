package net.plastboks.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.io.StreamCorruptedException;

/**
 * Created by alex on 12/13/14.
 */
public class Grass extends Scrollable {
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    public void onRestart(float x, float scrollSpeed) {
        pos.x = x;
        vel.x = scrollSpeed;
    }
}
