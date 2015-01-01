package net.plastboks.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/15/14.
 */
public class Bird extends Autonomous
{
    private float lvl = 0.2f;
    private int score = 1;
    private float lvlInc = 0.2f;
    private float maxLvl = 1.6f;

    public static final int width = 15;
    public static final int height = 15;

    public Bird() {
        super(width, height);
        setFactor(1000);
        setDivider(97);
    }

    public Bird(int speed) {
        this();
        for (int i = 0; i < speed; i++) { incrementSpeed(); }
    }


    public void update(float delta) {
        changeDir();
        move(delta + lvl);
    }

    public void setLvl(float lvl) { this.lvl = lvl; }

    public TextureRegion getTexture() { return AssetLoader.bird; }

    public void playSound() { AssetLoader.coin.play(); }
    public int getScore() { return score; }

    public void incrementSpeed() {
        if (lvl + lvlInc <= maxLvl) { lvl += lvlInc; }
    }
}
