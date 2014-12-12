package net.plastboks.sneik.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.plastboks.sneik.SneikGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sneik";
		config.width = 272;
		config.height = 408;
		config.resizable = false;
		new LwjglApplication(new SneikGame(), config);
	}
}
