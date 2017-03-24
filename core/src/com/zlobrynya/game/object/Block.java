package com.zlobrynya.game.object;

import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zlobrynya.game.screen.GameScreen;

import java.util.Random;

/**
 * Created by Nikita on 16.03.2017.
 */

public class Block {
    private int length;
    private Vector2 position;
    private Vector2 velocity;
    private Random r;
    private Rectangle rectangle;

    public Block(int x, int y, int speend){
        position = new Vector2(x,y);
        velocity = new Vector2(-speend,0);
        r = new Random();
        length = r.nextInt(20) + 10;
        rectangle = new Rectangle(x,y,length,10);
    }

    public void update(float delta){
        if ((position.x+length) < 0){
            length = r.nextInt(20) + 10;
            position.x = GameScreen.gameHeight + 50 - length;
            rectangle = new Rectangle(position.x,position.y,length,10);
        }
        position.add(velocity.cpy().scl(delta));
        rectangle.x = position.x;
        rectangle.y = position.y;
    }

    public void setSpeend(float speend){
        velocity.set(-speend,0);
    }


    public float getLength() {
        return length;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
