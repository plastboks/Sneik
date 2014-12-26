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

    public Mouse(int width, int height) {
        super(width, height);
        setFactor(2000);
        setDivider(543);
    }

    public void update(float delta) {
        changeDir();
        move(delta + lvl);
    }

    public void setLvl(float lvl) { this.lvl = lvl; }

    public TextureRegion getTexture() { return AssetLoader.mouse; }

    public void playSound() { AssetLoader.mousefx.play(); }
    public int getScore() { return score; }
}
