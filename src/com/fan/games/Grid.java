package com.fan.games;

import com.fan.games.Tool.GameUtil;

import java.awt.*;

public class Grid extends GameObject{

    // 网格
    Image gridImg = GameUtil.getImage("Statics/WHITE.png");
    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
        this.img = gridImg;

    }


}
