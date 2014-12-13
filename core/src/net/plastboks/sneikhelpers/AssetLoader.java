package net.plastboks.sneikhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.plastboks.screens.GameScreen;

/**
 * Created by alex on 12/12/14.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation snakeAnimation;
    public static TextureRegion snake, snakeDown, snakeUp;

    public static TextureRegion skullUp, skullDown, bar;

    public static Sound dead, coin, flap;
    public static BitmapFont font, shadow;

    public static Preferences prefs;

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0, 0, GameScreen.GAME_WIDTH, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        snakeDown = new TextureRegion(texture, GameScreen.GAME_WIDTH, 0, 17, 12);
        snakeDown.flip(false, true);

        snake = new TextureRegion(texture, 153, 0, 17, 12);
        snake.flip(false, true);

        snakeUp = new TextureRegion(texture, 170, 0, 17, 12);
        snakeUp.flip(false, true);

        TextureRegion[] snakes = {snakeDown, snake, snakeUp};
        snakeAnimation = new Animation(0.06f, snakes);
        snakeAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, GameScreen.GAME_WIDTH, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);

        prefs = Gdx.app.getPreferences("Sneik");

        if (!prefs.contains("highscore")) { prefs.putInteger("highscore", 0); }
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
        prefs.putInteger("highscore", val);
        prefs.flush();
    }

    public static int getHighScore() { return prefs.getInteger("highscore"); }
}
