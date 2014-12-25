package net.plastboks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

        int midPointY = (int) (getHeight() / 2);

        world = new GameWorld(midPointY);
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world, renderer));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) { }
    @Override
    public void show() { }
    @Override
    public void hide() { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void dispose() { }

    public static int getHeight() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameHeight = screenHeight / (screenWidth / GAME_WIDTH);
        return (int) gameHeight;
    }
}
