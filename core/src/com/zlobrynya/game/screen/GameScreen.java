package com.zlobrynya.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.zlobrynya.game.object.Block;
import com.zlobrynya.game.tools.AssetLoad;
import com.zlobrynya.game.tools.GameRenderer;
import com.zlobrynya.game.tools.GameWorld;
import com.zlobrynya.game.tools.InputHandler;

import java.util.ArrayList;

/**
 * Created by Nikita on 15.03.2017.
 */

public class GameScreen implements Screen {
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    public static float gameHeight;
    public static float gameWidth = 200;
    public static float screenWidth;
    public static float screenHeight;


    public GameScreen(){
        //Gdx.app.log("GameScreen", "Attached");
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        gameHeight = screenHeight / (screenWidth / gameWidth);

        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld,gameHeight);
        Gdx.input.setInputProcessor((new GestureDetector(new InputHandler(gameWorld))));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
