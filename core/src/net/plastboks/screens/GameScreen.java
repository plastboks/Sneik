package net.plastboks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import net.plastboks.gameworld.GameRenderer;
import net.plastboks.gameworld.GameWorld;
import net.plastboks.sneikhelpers.InputHandler;

/**
 * Created by alex on 12/12/14.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;

    public static final int GAME_WIDTH = 136;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameHeight = screenHeight / (screenWidth / GAME_WIDTH);

        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY);

        Gdx.input.setInputProcessor(new InputHandler(world));
        Gdx.app.log("GameScreen", "Attached");
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // beh
    }

}
