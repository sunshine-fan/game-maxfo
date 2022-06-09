package com.fan.games;

import com.fan.games.Tool.GameUtil;

import java.awt.*;

/**
 * 地图元素快
 */
public class Square extends GameObject {

    Image IMGX = GameUtil.getImage("Statics/Z.png");
//  Image IMGX = GameUtil.getImage("Statics/X.png");
    //无参构造
    public Square() {
    }

    /**
     * 有参构造
     * @param x
     * @param y
     * @param rectangle
     */
    public Square(int x, int y, int width, int height, Rectangle rectangle) {
        super(x, y, width, height, rectangle);
        super.img = IMGX;
    }
    public Image getIMGX() {
        return IMGX;
    }
    public void setIMGX(Image IMGX) {
        this.IMGX = IMGX;
    }

    public void drawX(Graphics g) {
        g.drawImage(IMGX,(int)x,(int)y,32,32,null);
    }

}
