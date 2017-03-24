package com.zlobrynya.game.object;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.zlobrynya.game.screen.GameScreen;

import org.w3c.dom.css.Rect;

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
    private Vector2 vector2;
    private boolean motion;
    private OrthographicCamera cam;

    private Rectangle rectangle;

    public MainChapter(int width, int height, float x, float y, int speend){
        this.width = width;
        this.height = height;
        motion = false;
        position = new Vector2(x,y);
        endPosition = new Vector2(x,y);
        velocity = new Vector2(speend*6,-speend*6);
        downtime = new Vector2(-speend,0);
        vector2 = new Vector2(0,0);
        rectangle = new Rectangle(x,y,width,height);
    }

    public void update(float delta){
        if (motion){
            position.add(vector2.cpy().scl(delta));
            rectangle.x = position.x;
            rectangle.y = position.y;
        }else {
           position.add(downtime.cpy().scl(delta));
           rectangle.x = position.x;
           rectangle.y = position.y;
        }
    }

    public void editPosition(float x, float y){
        position.set(x,y);
    }

    public void setEndPosition(float x, float y){
        if (!motion){
            motion = true;
            endPosition = conversionVector(x-(width),y);
            vector2.x = endPosition.x - position.x;
            vector2.y = position.y - endPosition.y;
            vector2 = vector2.nor();
            vector2 = vector2.scl(velocity);
        }
    }

    private Vector2 conversionVector(float x, float y){
        Vector3 vector3 = new Vector3(x,y,0);
        cam.unproject(vector3);
        return new Vector2(vector3.x,vector3.y);
    }

    public void collides(Rectangle rectangle){
        if (motion)
            if (Intersector.overlaps(this.rectangle, rectangle)) {
                motion = false;
                if (position.y < 20){
                    position.y = rectangle.y + rectangle.height + 1;
                    this.rectangle.y = rectangle.y + rectangle.height + 1;
                }
            }
    }

    public void restart(int x, int y){
        position.set(x,y);
        vector2.set(0,0);
        motion = false;
        rectangle.setPosition(x,y);
    }

    public void setSpeend(float speend){
        velocity.set(speend*6,-speend*6);
        downtime.set(-speend,0);
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

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }
}
