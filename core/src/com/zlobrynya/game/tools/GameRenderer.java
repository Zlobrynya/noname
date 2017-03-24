package com.zlobrynya.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.StringBuilder;
import com.zlobrynya.game.object.Block;
import com.zlobrynya.game.object.MainChapter;
import com.zlobrynya.game.object.Rope;
import com.zlobrynya.game.screen.GameScreen;

import java.util.ArrayList;

/**
 * Created by Nikita on 15.03.2017.
 * Отрисовка всего и вся.
 */

public class GameRenderer {
    private SpriteBatch batch;
    private GameWorld gameWorld;
    private MainChapter mainChapter;
    private SpriteBatch batcher;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private Rope rope;
    private float gameHeight;
    private Vector3 posText;

    public GameRenderer(GameWorld gameWorld, float gameHeight){
        this.gameHeight = gameHeight;
        batch = new SpriteBatch();
        this.gameWorld = gameWorld;
        mainChapter = gameWorld.getMainChapter();
        cam = new OrthographicCamera();
        //noinspection SuspiciousNameCombination
        cam.setToOrtho(true, gameHeight, 200);
        posText = new Vector3(GameScreen.screenWidth / 4, GameScreen.screenHeight / 2, 0);
        posText = cam.unproject(posText);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        rope = gameWorld.getRope();
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        mainChapter.setCam(cam);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        switch (gameWorld.getCurrentState()){
            case READY:
                renderText("Touch me!");
                break;
            case RUNNING:
                renderRunning(delta);
                break;
            case GAMEOVER:
                renderText("Game Over!");
                break;
        }
    }

    private void renderRunning(float delta){
        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoad.textureMainChapter, mainChapter.getX(), mainChapter.getY(), mainChapter.getWidth(), mainChapter.getHeight());
        batcher.end();
        drawBlock();
    }

    private void renderText(String text){
        batcher.begin();
        batcher.disableBlending();
        // Отрисуем сначала тень
        AssetLoad.shadow.draw(batcher, text, posText.x, posText.y);
        // А теперь сам текст
        AssetLoad.font.draw(batcher, text, posText.x , posText.y);
        batcher.end();
    }

    private Vector3 conversionVector(float x, float y){
        Vector3 vector3 = new Vector3(x,y,0);
        cam.unproject(vector3);
        return vector3;
    }

    private void drawRope(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        //Перевод в координатную систему камеры
        Vector3 startPosition = new Vector3(rope.getStartX(),rope.getStartY(),0);
        cam.unproject(startPosition);

        Vector3 endPosition = new Vector3(rope.getEndX(), rope.getEndY(),0);
        cam.unproject(endPosition);

        // Выбираем цвет RGB Color 255, 109, 120, не прозрачный
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Отрисовываем квадрат из myWorld (Используем ShapeType.Filled)
        shapeRenderer.line(rope.getStartX(), rope.getStartY(),
                endPosition.x, endPosition.y);

        rope.setDraw(false);
        shapeRenderer.end();
    }

    private void drawBlock(){
        ArrayList<Block> blocks = gameWorld.getBlocks();

        for (Block block: blocks){
            // Говорим shapeRenderer начинать отрисовывать формы
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            // Выбираем RGB Color 87, 109, 120, не прозрачный
            shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

            // Отрисовываем квадрат из myWorld (Используем ShapeType.Filled)
            /*shapeRenderer.rect(block.getX(), block.getY(),
                    block.getLength(), 10);*/
            shapeRenderer.rect(block.getRectangle().x,block.getRectangle().y,
                    block.getRectangle().width,block.getRectangle().height);
            // говорим shapeRenderer прекратить отрисовку
            // Мы ДОЛЖНЫ каждый раз это делать
            shapeRenderer.end();

        /*
         * 3. Мы отрисовываем рамку для квадрата
         */

            // Говорим shapeRenderer нарисовать рамку следующей формы
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            // Выбираем цвет RGB Color 255, 109, 120, не прозрачный
            shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

            // Отрисовываем квадрат из myWorld (Используем ShapeType.Filled)
          /*  shapeRenderer.rect(block.getX(), block.getY(),
                    block.getLength(), 10);*/

            shapeRenderer.end();
        }

    }
}
