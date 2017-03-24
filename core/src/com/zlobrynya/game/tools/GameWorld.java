package com.zlobrynya.game.tools;

import com.badlogic.gdx.utils.Timer;
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
    private int SPEEND = 40;
    private final int UP = 11;
    private final int DOWN = 180;
    private GameState currentState;
    private int kolTimer;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(){
        currentState = GameState.READY;
        mainChapter = new MainChapter(10,20,50,100,SPEEND);
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
        startTimer();
    }

    public void update(float delta){
        switch (currentState){
            case RUNNING:
                updateRunning(delta);
                break;
            case READY:
                break;
        }

        if (kolTimer <= 0){
            startTimer();
        }
        //float x = mainChapter.getX() + 1;
        //mainChapter.editPosition(x,mainChapter.getY());
    }

    private void startTimer(){
        kolTimer = 60;
        //таймер для увелечения скорости игры.
        new Timer().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                SPEEND += 5;
                kolTimer--;
                setSpend();
            }
        },10,50,kolTimer);
    }

    private void updateRunning(float delta){
        for(int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            block.update(delta);
            blocks.set(i,block);
        }

        mainChapter.update(delta);

        if (mainChapter.getY() <= UP && mainChapter.getY() > 0){
            for (int i = 0; i < blocks.size(); i += 2){
                Block block = blocks.get(i);
                mainChapter.collides(block.getRectangle());
            }
        }

        float posHeight = mainChapter.getY()+mainChapter.getHeight();
        if (posHeight >= DOWN && posHeight <= 200){
            for (int i = 1; i < blocks.size(); i += 2){
                Block block = blocks.get(i);
                mainChapter.collides(block.getRectangle());
            }
        }

        if (mainChapter.getY() < 0 || mainChapter.getY() > 200
                || (mainChapter.getX() + mainChapter.getHeight() / 2) < 0 || mainChapter.getX() > GameScreen.gameHeight){
            currentState = GameState.GAMEOVER;
            restart();
        }
    }

    private void restart(){
        mainChapter.restart(50,100);
        setSpend();
    }

    private void setSpend(){
        mainChapter.setSpeend(SPEEND);
        for(int i = 0; i < blocks.size(); i++){
            Block block = blocks.get(i);
            block.setSpeend(SPEEND);
            blocks.set(i,block);
        }
    }


    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    ArrayList<Block> getBlocks() {
        return blocks;
    }

    public MainChapter getMainChapter() {
        return mainChapter;
    }

    public Rope getRope() {
        return rope;
    }
}
