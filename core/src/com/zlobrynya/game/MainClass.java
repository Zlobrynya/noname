package com.zlobrynya.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zlobrynya.game.screen.GameScreen;
import com.zlobrynya.game.tools.AssetLoad;

public class MainClass extends Game {

	@Override
	public void create () {
		AssetLoad.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoad.dispose();
	}

}
