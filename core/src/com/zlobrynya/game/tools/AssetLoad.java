package com.zlobrynya.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Nikita on 15.03.2017.
 */

public class AssetLoad {
    public static Texture textureMainChapter;
    public static TextureRegion bg;
    public static BitmapFont font;
    public static BitmapFont shadow;
    private static Texture texture;


    public static void load(){
        texture = new Texture(Gdx.files.internal("texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

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
        texture.dispose();
    }
}
