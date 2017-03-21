package com.zlobrynya.game.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Nikita on 15.03.2017.
 */

public class MainChapter {
    private int width;
    private int height;
    private Vector2 position; //Позиция

    public MainChapter(int width, int height, float x, float y){
        this.width = width;
        this.height = height;
        position = new Vector2(x,y);
    }

    public void editPosition(float x, float y){
        position.set(x,y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }
}
