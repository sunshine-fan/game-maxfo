package com.fan.games;

import com.fan.games.Tool.GameUtil;
import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;

// TODO 发射子弹 子弹跟踪 碰撞检测
public class Bullet extends GameObject {

    int atk = 5;
    Direction direction;
    //我方子弹状态是否可见飞出屏幕
    private boolean visible = true;
    int speed;
    public int id;
    double degree;
    /**
     * 构造方法
     */
    public Bullet() {
        degree = Math.random()*Math.PI*2;
    }
    //子弹改变图片的方法
    private void CutImg(String img) {
        this.img = GameUtil.getImage(img);
    }
    //改变图片
    private void ChangeImg(int dir) {
        //人物子弹
        switch (dir) {
            case 1:
                CutImg("Statics/BulletIMG/arm1_l_" + GameFrame.frame + ".png");
                break;
            case 2:
                CutImg("Statics/BulletIMG/arm1_r_" + GameFrame.frame + ".png");
                break;
            case 3:
                CutImg("Statics/BulletIMG/arm1_u_" + GameFrame.frame + ".png");
                break;
            case 4:
                CutImg("Statics/BulletIMG/arm1_d_" + GameFrame.frame + ".png");
                break;
            //  怪物子弹
            case  5:
                CutImg("Statics/BulletIMG/arm9_" + GameFrame.frame_five + ".png");
                break;
            //  火焰子弹
            case 6:
                 CutImg("Statics/BulletIMG/arm3_l_" + GameFrame.frame + ".png");
                 break;
            case 7:
                 CutImg("Statics/BulletIMG/arm3_r_" + GameFrame.frame + ".png");
                 break;
            case 8:
                 CutImg("Statics/BulletIMG/arm3_u_" + GameFrame.frame + ".png");
                 break;
            case 9:
                 CutImg("Statics/BulletIMG/arm3_d_" + GameFrame.frame + ".png");
                 break;
            //冰霜子弹
            case 10:
                 CutImg("Statics/BulletIMG/arm2_l_" + GameFrame.frame + ".png");
                 break;
            case 11:
                 CutImg("Statics/BulletIMG/arm2_r_" + GameFrame.frame + ".png");
                 break;
            case 12:
                 CutImg("Statics/BulletIMG/arm2_u_" + GameFrame.frame + ".png");
                 break;
            case 13:
                 CutImg("Statics/BulletIMG/arm2_d_" + GameFrame.frame + ".png");
                 break;

            // 藤曼子弹
            case 14:
                 CutImg("Statics/BulletIMG/arm4_l_" + GameFrame.frame + ".png");
                 break;
            case 15:
                CutImg("Statics/BulletIMG/arm4_r_" + GameFrame.frame + ".png");
                break;
            case 16:
                CutImg("Statics/BulletIMG/arm4_u_" + GameFrame.frame + ".png");
                break;
            case 17:
                CutImg("Statics/BulletIMG/arm4_d_" + GameFrame.frame + ".png");
                break;
            //boss 子弹
            case 18:
                CutImg("Statics/BulletIMG/boss_atk_" + GameFrame.frame_six + ".png");
                break;
        }
    }
//    //改变图片
//    public void ChangeImg2() {
//        CutImg("Statics/BulletIMG/arm9_" + GameFrame.frame_five + ".png");
//    }


    //根据uid 绘制所有子弹  根据id 判断子弹移动以及特效
    public void draw(Graphics g) {
        if (!visible) return;
        if (id==1){
            move(direction,1);
            g.drawImage(img, (int) x, (int) y, null);
        }else if (id==2){
            move(direction,2);
            g.drawImage(img, (int) x, (int) y, null);
        }else  if (id==3){
            move(direction,3);
            g.drawImage(img, (int) x, (int) y, null);
        }else  if (id==4){
            move(direction,4);
            g.drawImage(img, (int) x, (int) y, null);
        } else  if (id==5){
            move(direction,5);
            g.drawImage(img, (int) x, (int) y, null);
        } else  if (id==6){
            move(direction,6);
            g.drawImage(img, (int) x, (int) y, null);
        }

    }
    /**
     * 普通子弹移动
     *
     * @param direction
     */
    public void move(Direction direction,int id) {
        if (id==1){
            switch (direction) {
                case DOWN:
                    ChangeImg(4);
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    y -= speed;
                    ChangeImg(3);
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    ChangeImg(2);
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    ChangeImg(1);
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }//怪物子弹
        else if (id==2){
            ChangeImg(5);
            switch (direction) {
                case DOWN:
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    y -= speed;
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }
        //火焰
        else if (id==3){
            switch (direction) {
                case DOWN:
                    ChangeImg(9);
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    ChangeImg(8);
                    y -= speed;
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    ChangeImg(7);
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    ChangeImg(6);
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }
        //寒冰
        else if (id==4){
            switch (direction) {
                case DOWN:
                    ChangeImg(13);
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    ChangeImg(12);
                    y -= speed;
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    ChangeImg(11);
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    ChangeImg(10);
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }
        //藤曼
        else if (id==5){
            switch (direction) {
                case DOWN:
                    ChangeImg(17);
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    ChangeImg(16);
                    y -= speed;
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    ChangeImg(15);
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    ChangeImg(14);
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }
        //BOSS子弹
        else if (id==6){
            ChangeImg(18);
            switch (direction) {
                case DOWN:
                    y += speed;
                    if (y > 640) this.visible = false;
                    break;
                case UP:
                    y -= speed;
                    if (y < 0)
                        this.visible = false;
                    break;
                case RIGHT:
                    x += speed;
                    if (x > 960)
                        this.visible = false;
                    break;
                case LEFT:
                    x -= speed;
                    if (x < 0)
                        this.visible = false;
                    break;
            }
        }

    }

    /**
     * 怪物子弹移动逻辑
     * @param direction
     */
    public void MTmove(Direction direction) {

    }



    //寒冰子弹移动
    //火焰子弹
    //藤曼子弹
    /**
     * set get
     */
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public int getatk() {
        return atk;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setatk(int atk) {
        this.atk = atk;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setId(int id) {
        this.id = id;
    }
}
