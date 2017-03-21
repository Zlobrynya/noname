package com.zlobrynya.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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


    public GameRenderer(GameWorld gameWorld, float gameHeight){
        batch = new SpriteBatch();
        this.gameWorld = gameWorld;
        mainChapter = gameWorld.getMainChapter();
        cam = new OrthographicCamera();
        //noinspection SuspiciousNameCombination
        cam.setToOrtho(true, gameHeight, 200);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        rope = gameWorld.getRope();
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(AssetLoad.textureMainChapter, mainChapter.getX(), mainChapter.getY(), mainChapter.getWidth(), mainChapter.getHeight());
        batcher.end();

        drawBlock();
     //   if (rope.isDraw()){
            drawRope();
      //  }
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
            shapeRenderer.rect(block.getX(), block.getY(),
                    block.getLength(), 10);

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
            shapeRenderer.rect(block.getX(), block.getY(),
                    block.getLength(), 10);

            shapeRenderer.end();
        }

    }
}
