package com.zlobrynya.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.zlobrynya.game.object.MainChapter;
import com.zlobrynya.game.object.Rope;
import com.zlobrynya.game.screen.GameScreen;

/**
 * Created by Nikita on 15.03.2017.
 */

public class InputHandler implements GestureDetector.GestureListener {
    private Rope rope;
    private MainChapter mainChapter;

    public InputHandler(Rope rope, MainChapter mainChapter){
        this.rope = rope;
        this.mainChapter = mainChapter;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
      //  Gdx.app.log("touchDown", x+" "+y);

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
     //   rope.setEndPosition(x ,y);

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
      //  Gdx.app.log("pan", velocityX+" "+velocityY);

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
      //  rope.setStartPosition(x-deltaX,y-deltaY);
      //  Gdx.app.log("pan", x+" "+y+" "+deltaX+" "+deltaY);
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("-------", "-------");
        Gdx.app.log("panStop", x+" "+y);
        Gdx.app.log("GameScreen", GameScreen.screenHeight +" "+ GameScreen.screenWidth);
        rope.setEndPosition(x,y);
        mainChapter.setEndPosition(x,y);
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
