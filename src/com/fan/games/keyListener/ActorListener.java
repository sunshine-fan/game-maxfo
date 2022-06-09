package com.fan.games.keyListener;

import com.fan.games.Actor;
import com.fan.games.Config;
import com.fan.games.Direction;
import com.fan.games.Monster;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ActorListener extends KeyAdapter {

    Actor actor;
    Monster mter;
    public ActorListener(Actor actor , Monster mter) {
         this.actor=actor;
         this.mter = mter;
}
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_A:
                actor.pressedLeft = true;
                actor.direction = Direction.LEFT;
                break;
            case KeyEvent.VK_S:
                actor.pressedDown = true;
                actor.direction = Direction.DOWN;
                break;
            case KeyEvent.VK_D:
                actor.pressedRight = true;
                actor.direction = Direction.RIGHT;
                break;
            case KeyEvent.VK_W:
                actor.pressedUp = true;
                actor.direction = Direction.UP;
                break;
            case KeyEvent.VK_F1:
                actor.F1();
                break;
            case KeyEvent.VK_F2:
                actor.F2();
                break;
            case KeyEvent.VK_F3:
                actor.F3();
                break;
            case KeyEvent.VK_F4:
                actor.F4();
                break;
            case KeyEvent.VK_F5:
                actor.F5();
                break;
            case KeyEvent.VK_U:
                if (Config.MP>10){
                    actor.Flame_fire();
                    Config.MP-=10;
                }
                break;
            case KeyEvent.VK_I:
                if (Config.MP>15){
                    actor.Ice_fire();
                    Config.MP-=15;
                }
                break;
            case KeyEvent.VK_O:
                if (Config.MP>15){
                    actor.Creeper_fire();
                    Config.MP-=15;
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_A:
                actor.pressedLeft = false;
                break;
            case KeyEvent.VK_S:
                actor.pressedDown = false;
                break;
            case KeyEvent.VK_D:
                actor.pressedRight = false;
                break;
            case KeyEvent.VK_W:
                actor.pressedUp = false;
                break;
            case KeyEvent.VK_J:
                actor.ACTOR_fire();
                break;
        }
    }
}
