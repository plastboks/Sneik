package net.plastboks.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/15/14.
 */
public class Mouse extends Autonomous
{
    private float lvl = 0.3f;
    private int score = 2;
    private float lvlInc = 0.2f;
    private float maxLvl = 1.6f;

    public static final int width = 15;
    public static final int height = 15;

    public Mouse() {
        super(width, height);
        setFactor(2000);
        setDivider(543);
    }

    public Mouse(int speed) {
        this();
        for (int i = 0; i < speed; i++) { incrementSpeed(); }
    }

    public void update(float delta) {
        changeDir();
        move(delta + lvl);
    }

    public void setLvl(float lvl) { this.lvl = lvl; }

    public TextureRegion getTexture() { return AssetLoader.mouse; }

    public void playSound() { AssetLoader.coin.play(); }
    public int getScore() { return score; }

    public void incrementSpeed() {
        if (lvl + lvlInc <= maxLvl) { lvl += lvlInc; }
    }
}
