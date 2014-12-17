package net.plastboks.gameobjects;

/**
 * Created by alex on 12/15/14.
 */
public class Bird extends Autonomous
{

    public Bird(int width, int height) {
        super(width, height);
    }

    public void update(float delta) {
        float lvl = 0.4f;
        changeDir();
        move(delta + lvl);
    }
}
