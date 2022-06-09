package com.fan.games;

import java.awt.*;
import java.awt.Image;

/**
 * 游戏物体
 */
public class GameObject {
    // 坐标
    int x, y;
    //宽跟高
    int width, height;
    // 图像
    Image img;
    //游戏物体默认方向
    public Direction direction;
    // 矩形
    Rectangle rectangle;

    /**
     * 构造方法
     */
    public GameObject() {
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public GameObject(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    //墙体
    public GameObject(int x, int y, int width, int height, Rectangle rectangle) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = rectangle;
    }
    //掉落道具
    public GameObject(int x, int y, int width, int height,double hp,double mp, Image img,Rectangle rectangle) {
        this.x = x;
        this.y = y;
        this.img=img;
        this.width = width;
        this.height = height;
        this.rectangle = rectangle;
    }
    public GameObject(int x, int y, int width, int height, Rectangle rectangle, Direction direction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = rectangle;
        this.direction = direction;
    }

    //拥有默认方向的构造函数
    public GameObject(int x, int y, int width, int height, Image img, Rectangle rectangle,Direction direction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.rectangle = rectangle;
        this.direction = direction;
}

    //物体矩形获取
    public Rectangle getRect() {
         return new Rectangle( x,  y, width, height);
    }
    //修改这个矩形物体
    public void setRect(int x , int y,int width , int height) {
        this.rectangle = new Rectangle( x, y, width, height);
    }
    /**
     * get/set
     * @return
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return width;
    }

    public Image getImg() {
        return img;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(){

    }
    public void setHeight(){

    }
    public void setImg(Image img) {
        this.img = img;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * 绘制
     *
     * @param g
     */
    public void drawSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

}
