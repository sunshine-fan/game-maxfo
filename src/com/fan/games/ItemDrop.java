package com.fan.games;

import java.awt.*;
//游戏道具
public class ItemDrop extends GameObject {
    int id;
    int uid;
    int price;
    double hp;
    double mp;
    int atk;
    boolean buy=false;
    public ItemDrop(int x,int y ,Image img,int id){
        super(x,y,img);
        this.id=id;
    }
    public ItemDrop(int x, int y, int width, int height, double hp,double mp,int id,int uid,int atk,Image img,Rectangle rectangle){
        super(x,y,width,height,hp,mp,img,rectangle);
        this.id = id;
        this.uid=uid;
        this.hp=hp;
        this.mp=mp;
        this.atk = atk;
    }
    public ItemDrop(int x, int y, int width, int height, double hp,double mp,int id,int uid,int atk,int price,boolean buy,Image img,Rectangle rectangle){
        super(x,y,width,height,hp,mp,img,rectangle);
        this.id = id;
        this.uid=uid;
        this.price=price;
        this.buy=buy;
        this.hp=hp;
        this.mp=mp;
        this.atk = atk;
    }
    public ItemDrop(int x, int y, int width, int height, double hp,double mp,int id,int atk,Image img,Rectangle rectangle){
        super(x,y,width,height,hp,mp,img,rectangle);
        this.id = id;
        this.hp=hp;
        this.mp=mp;
        this.atk = atk;
    }
    public void draw(Graphics g){
        g.drawImage(img,x,y,null);
    }

}
