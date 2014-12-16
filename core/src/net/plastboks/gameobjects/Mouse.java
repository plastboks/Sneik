package net.plastboks.gameobjects;

/**
 * Created by alex on 12/15/14.
 */
public class Mouse extends Base {

    public Mouse(float x, float y, int width, int height, int gameHeight) {
        super(x, y, width, height);
    }

    public void update(float delta) {
        incrementX();
    }
}
