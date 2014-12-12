package net.plastboks.sneik;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.plastboks.screens.GameScreen;

public class SneikGame extends Game {

    @Override
    public void create() {
        Gdx.app.log("SneikGame", "created");
        setScreen(new GameScreen());
    }
}
