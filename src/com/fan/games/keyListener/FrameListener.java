package com.fan.games.keyListener;

import com.fan.games.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * GameStart 按键监听
 */
public class FrameListener extends KeyAdapter {

    GameFrame gameFrame;
    Actor actor;
    GameMap gameMap;
    Config config;
    Image img;
    public FrameListener(GameFrame gameFrame, Actor actor, GameMap gameMap, Image img) {
        this.gameFrame= gameFrame;
        this.actor = actor;
        this.gameMap = gameMap;
        this.img= img;
    }

    public void keyTyped(KeyEvent e) {
        int key = e.getKeyChar();
        switch (key){
            case 99:
                if (!GameFrame.isFail||GameMap.dropN.size()==5){
                    GameMap.wallList.clear();
                    GameMap.Allconsumables.clear();
                    GameMap.Allconsumablesl.clear();
                    GameMap.dropA.clear();
                    GameMap.dropB.clear();
                    GameMap.dropC.clear();
                    GameMap.dropD.clear();
                    GameMap.dropE.clear();
                    GameMap.dropF.clear();
                    GameMap.dropG.clear();
                    GameMap.dropH.clear();
                    GameMap.dropP.clear();
                    GameMap.dropN.clear();
                    GameMap.dropO.clear();
                    GameMap.dropI.clear();
                    GameMap.monsters.clear();
                    Config.HP=100;
                    Config.MP=100;
                    Config.HPmax=100;
                    Config.MPmax=100;
                    gameMap.setX(20);
                    gameMap.setY(-1035);
                    actor.setX(184);
                    actor.setY(400);
                    Config.ScollX=0;
                    Config.ScollY=0;
                    gameMap.Initialize();
                    gameMap.initialized();
                }
                break;
            case 67:
                if (!GameFrame.isFail||GameMap.dropN.size()==5){
                    GameMap.wallList.clear();
                    GameMap.Allconsumables.clear();
                    GameMap.Allconsumablesl.clear();
                    GameMap.dropA.clear();
                    GameMap.dropB.clear();
                    GameMap.dropC.clear();
                    GameMap.dropD.clear();
                    GameMap.dropE.clear();
                    GameMap.dropF.clear();
                    GameMap.dropG.clear();
                    GameMap.dropH.clear();
                    GameMap.dropP.clear();
                    GameMap.dropN.clear();
                    GameMap.dropO.clear();
                    GameMap.dropI.clear();
                    GameMap.monsters.clear();
                    Config.HP=100;
                    Config.MP=100;
                    Config.HPmax=100;
                    Config.MPmax=100;
                    Config.ScollX=0;
                    Config.ScollY=0;
                    gameMap.setX(20);
                    gameMap.setY(-1035);
                    actor.setX(184);
                    actor.setY(400);
                    gameMap.Initialize();
                    gameMap.initialized();
                }
                break;
            case 98:
                if (GameMap.dropN.size()==5){
                    System.exit(0);
                }
                break;
            default: break;
        }
    }
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_F12:
                config.istext = !config.istext;
                break;
            case KeyEvent.VK_F3:
                break;
            default: break;
        }


    }

    public void keyReleased(KeyEvent e) {

    }
}
