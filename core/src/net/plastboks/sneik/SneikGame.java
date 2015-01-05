package net.plastboks.sneik;

import com.badlogic.gdx.Game;
import net.plastboks.screens.GameScreen;
import net.plastboks.sneikhelpers.AssetLoader;

public class SneikGame extends Game {

    public static final double version = 0.02;

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
