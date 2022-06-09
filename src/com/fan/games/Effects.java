package com.fan.games;

import com.fan.games.Tool.GameUtil;
import org.omg.CORBA.INTERNAL;

import java.awt.*;

public class Effects extends GameObject {
    //收击与持续受击
    private int id;
    //特效种类
    public int uid;
    //记时
    private long aitime;
    private Image img;
    //判断时间消亡
    public boolean visible = true;
    //判断帧数消亡
    private int steps = 0;
    private int step;
    private int bossflame;
    private int flame = 1;
    Monster monster;
    Effects effects;
//    //局部帧数
//  private int steps = 0;

    /**
     * 构造方法
     *
     * @param x
     * @param y
     * @param img
     * @param id
     */
    public Effects(int x, int y, Image img, int id, int uid, Monster monster, Actor actor) {
        super(x, y);
        this.id = id;
        this.uid = uid;
        this.monster = monster;
        aitime = System.currentTimeMillis();
        step = 1;
        EffectsThead effect = new EffectsThead();
        effect.start();
    }

    public Effects(int x, int y, Image img, int id, int uid) {
        super(x, y);
        this.id = id;
        this.uid = uid;
        aitime = System.currentTimeMillis();
        step = 1;
        EffectsThead effect = new EffectsThead();
        effect.start();

    }

    //动画帧数
    public void frame() {

        if (steps % 3 == 0) {
            flame++;
            if (step < 12) {
                step++;
            }
            if (flame % 5 == 0) {
                flame = 1;
            }

        }
        if (steps % 12 == 0) {
            if (bossflame < 15) {
                bossflame++;
            }
        }
    }

    // TODO 9.22 从这里开始
    //火焰位置矫正  2火焰受击  3寒冰受击  4毒物受击
    private void move() {
        if (uid == 5) {
            this.x = monster.getX() - 3;
            this.y = monster.getY() - 20;
            monster.setSpeed(2);
            if (System.currentTimeMillis() - aitime > 1500) {
                monster.setLife(monster.getLife() - 1);
            }
        } else if (uid == 6) {
            this.x = monster.getX() - 25;
            this.y = monster.getY() - 10;
            monster.setSpeed(0);
            if (System.currentTimeMillis() - aitime > 2000) {
                monster.setSpeed(1);
            }
        } else if (uid == 7) {
            this.x = monster.getX() - 3;
            this.y = monster.getY() - 20;
            if (System.currentTimeMillis() - aitime > 1500) {
                monster.setLife(monster.getLife() - 1);
            }
        }
    }

    //改变图片方法
    private void CutImg(String img) {
        this.img = GameUtil.getImage(img);
    }

    //改变图片
    private void ChangeImg(int dir) {
        switch (dir) {
            //普通爆炸
            case 1:
                if (step < 4) {
                    CutImg("Statics/Effects/ef1_" + step + ".png");
                }
                break;
            //火焰受击
            case 2:
                if (step < 4) {
                    CutImg("Statics/Effects/ef3_" + step + ".png");
                }
                break;
            case 3:
                if (step < 4) {
                    CutImg("Statics/Effects/ef2_" + step + ".png");
                }
                break;
            case 4:
                if (step < 4) {
                    CutImg("Statics/Effects/ef4_" + step + ".png");
                }
                break;
            //角色受击
            case 10:
                if (step < 4) {
                    CutImg("Statics/Effects/ef10_" + step + ".png");
                }
                break;
            //boss进场动画
            case 14:
                CutImg("Statics/boss/eye_2.png");
                break;
            //持续受击
            //火焰
            case 5:
                CutImg("Statics/Effects/ef7_" + flame + ".png");
                break;
            //寒冰
            case 6:
                CutImg("Statics/Effects/ef9_" + flame + ".png");
                break;
            //毒物
            case 7:
                CutImg("Statics/Effects/ef8_" + flame + ".png");

                break;
            //boss 特效
            case 8:
                CutImg("Statics/Effects/ef10_" + flame + ".png");
                break;
            case 9:
                CutImg("Statics/Effects/ef2_" + flame + ".png");
                break;
        }
    }

    //受击与持续受击的销毁判断
    private void effectsremove() {
        if (id == 1) {
            //使用帧数消亡
            if (uid == 14) {
                if (bossflame == 13) {
                    visible = false;

                }
            } else if (uid != 14) {
                if (step > 4) {
                    visible = false;

                }
            }

        } else if (id == 2) {
            move();
            if (System.currentTimeMillis() - aitime > 2000) {
                visible = false;

            }
        }
    }
    //uid  :1  怪物收到普通攻击
    //uid  :2  火焰
    //uid  :3  寒冰
    //uid  :4  藤曼
    //对象绘制方法
    public void drawEffcts(Graphics g, int uid) {
        frame();
        //判断消亡方法
        effectsremove();
        //特效进行回收
        for (int i = 0; i < GameMap.effects.size(); i++) {
            if (!GameMap.effects.get(i).visible) {
                GameMap.effects.remove(i);
            }
        }
        //当时间到了不执行此方法
        g.drawImage(img, x, y, null);
        if (id == 1) {
            switch (uid) {
                //怪物普通攻击受击
                case 1:
                    ChangeImg(1);
                    break;
                //火焰受击
                case 2:
                    ChangeImg(2);
                    break;
                //冰霜受击
                case 3:
                    ChangeImg(3);
                    break;
                //藤曼受击
                case 4:
                    ChangeImg(4);
                    break;
                //角色受击
                case 10:
                    ChangeImg(10);
                    break;
                //角色进场动画
                case 14:
                    ChangeImg(14);
                    break;
            }
        }
        if (id == 2) {
            switch (uid) {
                //火焰受击
                case 5:
                    //当生命值<10时 提前结束动画
                    if (monster.getLife() < 10) this.visible = false;
                    ChangeImg(5);
                    break;
                //冰霜受击
                case 6:
                    if (monster.getLife() < 10) this.visible = false;
                    ChangeImg(6);
                    break;
                //藤曼受击
                case 7:
                    //当生命值<10时 提前结束动画
                    if (monster.getLife() < 10) this.visible = false;
                    ChangeImg(7);
                    break;
                case 8:
                    ChangeImg(8);
                    break;
            }

        }
    }

    //窗口线程-
    class EffectsThead extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(15);  //1s=1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                steps++;
            }
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
