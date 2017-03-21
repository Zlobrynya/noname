package com.zlobrynya.game.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nikita on 16.03.2017.
 */

public class Rope {
    private Vector2 startPosition;
    private Vector2 endPosition;
    private boolean isDraw = false;

    public Rope(){
        startPosition = new Vector2(10,190);
        endPosition = new Vector2(0,0);
    }

    public void setStartPosition(float x, float y){
        startPosition.set(x,y);
    }

    public void setDraw(boolean draw) {
        isDraw = draw;
    }

    public void setEndPosition(float x, float y){
        endPosition.set(x,y);
    }

    public boolean isDraw() {
        return isDraw;
    }

    public float getStartX(){
        return startPosition.x;
    }

    public float getEndX(){
        return endPosition.x;
    }

    public float getStartY(){
        return startPosition.y;
    }

    public float getEndY(){
        return endPosition.y;
    }


}
