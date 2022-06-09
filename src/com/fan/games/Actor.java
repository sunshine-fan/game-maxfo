package com.fan.games;

import com.fan.games.Tool.GameUtil;

import java.awt.*;
import java.util.ArrayList;

public class Actor extends GameObject {

    //背景图片
    private Image actordownImg = GameUtil.getImage("Statics/Actor/背2.png");
    //  控制背景
//    public GameMap gf1;
    // 攻击力
    public int atk = 135;
    // 方向判断
    public boolean pressedLeft, pressedUp, pressedRight, pressedDown;
    //角色受击状态
    private int Actorbeat = 0;
    //初始速度
    private int speed = 5;
    //初始化帧为0
    private int steps = 0;
    Bullet bullet;
    Config config;
    GameMap gameMap;
    //人物子弹容器
    static ArrayList<Bullet> bullets = new ArrayList<>();
    Image bulletImg = GameUtil.getImage("Statics/道具/搁置道具/武器3.png");
    //方块容器
    ArrayList<Square> squares = new ArrayList<>();
    //移动中碰撞检测
    private boolean ActorL = false;
    private boolean ActorR = false;
    private boolean ActorU = false;
    private boolean ActorD = false;

    //无参构造
    public Actor() {
    }

    /**
     * 构造
     *
     * @param x
     * @param y
     */
    public Actor(int x, int y, int width, int height, Rectangle rectangle, Direction direction, Bullet bullet, GameMap gameMap, Config config) {
        super(x, y, width, height, rectangle, direction);
        this.img = actordownImg;
        this.speed = 6;
        this.bullet = bullet;
        this.gameMap = gameMap;
        this.config = config;
    }

    //改变图片
    private void changeImg(String img) {
        this.img = GameUtil.getImage(img);
    }

    //轮播图片
    private void leftward() {
        changeImg("Statics/Actor/左边" + GameFrame.frame + ".png");
    }

    private void upward() {
        changeImg("Statics/Actor/背" + GameFrame.frame + ".png");
    }

    private void rightward() {
        changeImg("Statics/Actor/右" + GameFrame.frame + ".png");
    }

    private void dowmward() {
        changeImg("Statics/Actor/正面" + GameFrame.frame + ".png");
    }

    //角色受击
    public void Actorbeat() {
        //平A受击
        if (Actorbeat == 1) {
            //创建帧数消亡对象
            Effects effect0 = new Effects(this.x - 40, this.y - 50, img, 1, 10);
            GameMap.effects.add(effect0);
            Actorbeat = 0;
        }
    }

    //人物移动
    public void move() {
        if (pressedLeft) {
            leftward();
            if (!ActorL) {
                x -= speed;
            }
        } else if (pressedRight) {
            rightward();
            if (!ActorR) {
                x += speed;
            }
        } else if (pressedUp) {
            upward();
            if (!ActorU) {
                y -= speed;
            }
        } else if (pressedDown) {
            dowmward();
            if (!ActorD) {

                y += speed;
            }
        }
    }

    //  人物子弹位置修正
    public void ACTOR_fire() {
            int BulletX = x;
            int BulletY = y;
            switch (direction) {
                case DOWN: {
                    BulletY += 15;
                    BulletX += 5;
                }
                break;
                case UP: {
                    BulletY -= 55;
                    BulletX += 5;
                }
                break;
                case RIGHT: {
                    BulletX += 10;
                }
                break;
                case LEFT: {
                    BulletX -= 60;
                    BulletX += 5;
                }
                break;
            }
            //子弹对象池抽离
            Bullet bullet = BulletsPool.get();
            bullet.setX(BulletX);
            bullet.setY(BulletY);
            bullet.setWidth(32);
            bullet.setHeight(32);
            bullet.setatk(300);
            bullet.setSpeed(15);
            bullet.setId(1);
            bullet.setDirection(direction);
            bullet.setVisible(true);
            bullets.add(bullet);
    }

    //火焰子弹位置修正
    public void Flame_fire() {
        int BulletX = x;
        int BulletY = y;
        switch (direction) {
            case DOWN: {
                BulletY += 15;
                BulletX -= 5;
            }
            break;
            case UP: {
                BulletY -= 85;
                BulletX -= 5;
            }
            break;
            case RIGHT: {
                BulletY -= 10;
                BulletX -= 15;
            }
            break;
            case LEFT: {
                BulletY -= 10;
                BulletX -= 35;
            }
            break;
        }
        //子弹对象池抽离
        Bullet bullet = BulletsPool.get();
        bullet.setX(BulletX);
        bullet.setY(BulletY);
        bullet.setWidth(32);
        bullet.setHeight(32);
        bullet.setatk(7);
        bullet.setSpeed(15);
        bullet.setId(3);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bullets.add(bullet);
    }

    //寒冰子弹位置修正
    public void Ice_fire() {
        int BulletX = x;
        int BulletY = y;
        switch (direction) {
            case DOWN: {
                BulletY += 15;
                BulletX -= 2;
            }
            break;
            case UP: {
                BulletY -= 85;
                BulletX -= 2;
            }
            break;
            case RIGHT: {
                BulletY -= 10;
                BulletX -= 5;
            }
            break;
            case LEFT: {
                BulletY -= 10;
                BulletX -= 35;
            }
            break;
        }
        //子弹对象池抽离
        Bullet bullet = BulletsPool.get();
        bullet.setX(BulletX);
        bullet.setY(BulletY);
        bullet.setWidth(32);
        bullet.setHeight(32);
        bullet.setId(4);
        bullet.setatk(7);
        bullet.setSpeed(15);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bullets.add(bullet);
    }

    //藤曼子弹修正
    public void Creeper_fire() {
        int BulletX = x;
        int BulletY = y;
        switch (direction) {
            case DOWN: {
                BulletY += 15;
                BulletX -= 20;
            }
            break;
            case UP: {
                BulletY -= 85;
                BulletX -= 20;
            }
            break;
            case RIGHT: {
                BulletY -= 15;
                BulletX -= 5;
            }
            break;
            case LEFT: {
                BulletY -= 15;
                BulletX -= 35;
            }
            break;
        }
        //子弹对象池抽离
        Bullet bullet = BulletsPool.get();
        bullet.setX(BulletX);
        bullet.setY(BulletY);
        bullet.setWidth(32);
        bullet.setHeight(32);
        bullet.setatk(7);
        bullet.setSpeed(15);
        bullet.setId(5);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bullets.add(bullet);
    }

    //绘制子弹
    public void drawBullets(Graphics g) {
        Actorbeat();
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        //遍历所有的子弹 将子弹还原至对象池分为两种状态  1.消失窗体外  2.与人物的碰撞检测  3.与墙体的碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isVisible()) {
                BulletsPool.theReturn(bullets.remove(i));
            }
        }

    }

    /**
     * 使用道具
     *
     * @F1
     * @F2
     * @F3
     * @F4
     * @F5
     */
    //苹果   HP+10
    public void F1() {
        for (int i = 0; i < GameMap.dropB.size(); i++) {
//            GameMap.dropH.get(i).
            if (Config.HP < Config.HPmax) {
                Config.HP += 5;
                if (Config.HP > Config.HPmax) {
                    Config.HP = Config.HPmax;
                }
                GameMap.dropB.remove(i);
                break;
            }
        }
    }

    //葡萄   MP+10
    public void F2() {
        for (int i = 0; i < GameMap.dropA.size(); i++) {
            if (Config.MP < Config.MPmax) {
                Config.MP += 5;
                if (Config.MP > Config.MPmax) {
                    Config.MP = Config.MPmax;
                }
                GameMap.dropA.remove(i);
                break;
            }
        }
    }

    //面包   HP+10  MP +10
    public void F3() {
        for (int i = 0; i < GameMap.dropC.size(); i++) {
//            GameMap.dropH.get(i).
            if (Config.HP < Config.HPmax || Config.MPmax < Config.MPmax) {
                Config.HP += 5;
                Config.MP += 5;
                if (Config.HP > Config.HPmax) {
                    Config.HP = Config.HPmax;
                }
                if (Config.MP > Config.MPmax) {
                    Config.MP = Config.MPmax;
                }
                GameMap.dropC.remove(i);
                break;
            }
        }
    }

    //HP+25
    public void F4() {
        for (int i = 0; i < GameMap.dropD.size(); i++) {
//            GameMap.dropH.get(i).
            if (Config.HP < Config.HPmax) {
                Config.HP += 15;
                if (Config.HP > Config.HPmax) {
                    Config.HP = Config.HPmax;
                }
                GameMap.dropD.remove(i);
                break;
            }
        }
    }

    //MP+25
    public void F5() {
        for (int i = 0; i < GameMap.dropE.size(); i++) {
            if (Config.MP < Config.MPmax) {
                Config.MP += 15;
                if (Config.MP > Config.MPmax) {
                    Config.MP = Config.MPmax;
                }
                GameMap.dropE.remove(i);
                break;
            }
        }
    }
    // Get set方法
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setActorL(boolean actorL) {
        ActorL = actorL;
    }

    public void setActorR(boolean actorR) {
        ActorR = actorR;
    }

    public void setActorU(boolean actorU) {
        ActorU = actorU;
    }

    public void setActorD(boolean actorD) {
        ActorD = actorD;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setActorbeat(int actorbeat) {
        Actorbeat = actorbeat;
    }

    //物体矩形获取
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    //修改这个矩形物体
    public void setRect(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }


}


