package net.plastboks.sneikhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by alex on 12/12/14.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg;

    public static TextureRegion snakeHead, snakeBody;
    public static TextureRegion bird, mouse;

    public static Sound dead, coin, flap;
    public static BitmapFont font, shadow;

    public static Preferences prefs;

    private static String hs = "highscore";

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/sneiktexture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, 30, 30);
        bg.flip(false, true);

        snakeHead = new TextureRegion(texture, 32, 0, 15, 15);
        snakeHead.flip(false, true);
        snakeBody = new TextureRegion(texture, 48, 0, 15, 15);
        snakeBody.flip(false, true);

        bird = new TextureRegion(texture, 32, 15, 15, 15);
        bird.flip(false, true);
        mouse = new TextureRegion(texture, 48, 15, 15, 15);
        mouse.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        prefs = Gdx.app.getPreferences("Sneik");

        if (!prefs.contains(hs)) { prefs.putInteger(hs, 0); }
    }

    public static void dispose() {
        texture.dispose();
        dead.dispose();
        coin.dispose();
        flap.dispose();
        font.dispose();
        shadow.dispose();
    }

    public static void setHighScore(int val) {
        prefs.putInteger(hs, val);
        prefs.flush();
    }

    public static int getHighScore() { return prefs.getInteger(hs); }
}
