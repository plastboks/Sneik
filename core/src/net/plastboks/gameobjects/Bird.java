package net.plastboks.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.plastboks.sneikhelpers.AssetLoader;

/**
 * Created by alex on 12/15/14.
 */
public class Bird extends Autonomous
{
    private float lvl = 0.2f;

    public Bird(int width, int height) {
        super(width, height);
        setFactor(1000);
        setDivider(97);
    }

    public void update(float delta) {
        changeDir();
        move(delta + lvl);
    }

    public void setLvl(float lvl) { this.lvl = lvl; }

    public TextureRegion getTexture() { return AssetLoader.bird; }
}
