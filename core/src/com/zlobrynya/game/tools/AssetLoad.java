package com.zlobrynya.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Nikita on 15.03.2017.
 */

public class AssetLoad {
    public static Texture textureMainChapter;
    public static BitmapFont font;
    public static BitmapFont shadow;


    public static void load(){
        textureMainChapter = new Texture("badlogic.jpg");
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.getData().setScale(.10f, -.15f);
        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        shadow.getData().setScale(.10f, -.15f);
    }

    public static void dispose(){
        textureMainChapter.dispose();
        font.dispose();
        shadow.dispose();
    }
}
