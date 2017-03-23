package com.zlobrynya.game.tools;

import com.zlobrynya.game.object.Block;
import com.zlobrynya.game.object.MainChapter;
import com.zlobrynya.game.object.Rope;
import com.zlobrynya.game.screen.GameScreen;

import java.util.ArrayList;

/**
 * Created by Nikita on 15.03.2017.
 * Обновление игровых данных. Логика.
 */

public class GameWorld {
    private MainChapter mainChapter;
    private Rope rope;
    private ArrayList<Block> blocks;
    private final int SPEEND = 25;
    private final int UP = 11;
    private final int DOWN = 180;

    public GameWorld(){
        mainChapter = new MainChapter(10,20,10,160,SPEEND);
        //mainChapter.setEndPosition(250,10);
        blocks = new ArrayList<Block>();
        rope = new Rope();
        int tmp = 50;
        //Для верха
        for (int i = 0; i < 3; i++) {
            Block block = new Block(tmp, 0, SPEEND);
            blocks.add(block);
            Block block2 = new Block(tmp, 190, SPEEND);
            blocks.add(block2);
            tmp += GameScreen.gameHeight / 2;
        }
    }

    public void update(float delta){
        for(int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            block.update(delta);
            blocks.set(i,block);
        }
        /*for (Block block: blocks){
            mainChapter.collides(block.getRectangle());
        }*/
        mainChapter.update(delta);
        if (mainChapter.getY() <= UP && mainChapter.getY() > (UP-5)){
            for (int i = 0; i < blocks.size(); i += 2){
                Block block = blocks.get(i);
                mainChapter.collides(block.getRectangle());
            }
        }
        /*&& (mainChapter.getY()+mainChapter.getHeight()) < 185*/
        float posHeight = mainChapter.getY()+mainChapter.getHeight();
        if (posHeight >= DOWN && posHeight <= 200){
            for (int i = 1; i < blocks.size(); i += 2){
                Block block = blocks.get(i);
                mainChapter.collides(block.getRectangle());
            }
        }

        //float x = mainChapter.getX() + 1;
        //mainChapter.editPosition(x,mainChapter.getY());
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public MainChapter getMainChapter() {
        return mainChapter;
    }

    public Rope getRope() {
        return rope;
    }
}
