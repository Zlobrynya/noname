package com.zlobrynya.game.object;

import com.badlogic.gdx.math.Vector2;
import com.zlobrynya.game.screen.GameScreen;

/**
 * Created by Nikita on 15.03.2017.
 */

public class MainChapter {
    private int width;
    private int height;
    private Vector2 position; //Позиция
    private Vector2 endPosition;
    private Vector2 velocity;
    private Vector2 downtime;
    private boolean motion;


    public MainChapter(int width, int height, float x, float y, int speend){
        this.width = width;
        this.height = height;
        motion = false;
        position = new Vector2(x,y);
        endPosition = new Vector2(x,y);
        velocity = new Vector2(10,-10);
        downtime = new Vector2(-speend,0);
    }

    public void update(float delta){
        /*
        if (position.x != endPosition.x && position.y != endPosition.y){
            position.add(velocity.cpy().scl(delta));
        }*/
        if (motion){
            position.add(velocity.cpy().scl(delta));
        }else {
            position.add(downtime.cpy().scl(delta));
        }
    }

    public void editPosition(float x, float y){
        position.set(x,y);
    }

    public void setEndPosition(float x, float y){
        motion = true;
        endPosition.x = x;
        endPosition.y = y;

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
