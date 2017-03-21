package com.zlobrynya.game.tools;

import com.zlobrynya.game.object.Block;
import com.zlobrynya.game.object.MainChapter;
import com.zlobrynya.game.object.Rope;
import com.zlobrynya.game.screen.GameScreen;

import java.util.ArrayList;

/**
 * Created by Nikita on 15.03.2017.
 * Обновление игровых данных.
 */

public class GameWorld {
    private MainChapter mainChapter;
    private Rope rope;
    private ArrayList<Block> blocks;
    private final int SPEEND = 50;

    public GameWorld(){
        mainChapter = new MainChapter(10,20,10,160);
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
<<<<<<< HEAD
        //f

=======
>>>>>>> origin/master
    }

    public void update(float delta){
        for(int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            block.update(delta);
            blocks.set(i,block);
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
