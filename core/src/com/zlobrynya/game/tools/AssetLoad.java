package com.zlobrynya.game.tools;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Nikita on 15.03.2017.
 */

public class AssetLoad {
    public static Texture textureMainChapter;

    public static void load(){
        textureMainChapter = new Texture("badlogic.jpg");
    }

    public static void dispose(){
        textureMainChapter.dispose();
    }
}
