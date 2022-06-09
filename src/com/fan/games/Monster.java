package com.fan.games;

import com.fan.games.Tool.GameUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * 怪物类
 */

public class Monster extends GameObject {
    //生命值
    private int life;
    //攻击力
    private int atk;
    //速度
    private int speed = 1;
    //随机数
    private double degree;
    //敌人的随机状态
    private int Radom;
    //记录五秒开始的时间
    private long aitime;
    //怪物是否消亡
    public boolean visible = true;

    //怪物是否受击
    public int acttackcount = 0;
    //移动中碰撞检测
    public boolean Orientationl = false;
    public boolean Orientationr = false;
    public boolean Orientationu = false;
    public boolean Orientationd = false;
    //方向判断
    private boolean MonsterLeft, MonsterRight, MonsterUp, MonsterDown;
    //0 默认   受击后恢复默认值
    //1 普通攻击受击
    //2 火焰受击
    //3 寒冰受击
    //4 缠绕受击
    //红色宝石
    Image RED = GameUtil.getImage("Statics/UI/red.png");
    //敌人子弹容器
    static ArrayList<Bullet> bulletsMonster = new ArrayList<>();

//    //敌方子弹是否飞出屏幕
//    private boolean visible = true ;

    Random random = new Random();
    //结束时间
    Date endTime;
    GameMap gameMap;
    Actor actor;
    int id;
    //id: 1 黑衣人
    //id：2 僵尸
    //id：3 飞龙
    //id：4 boss
    public Monster() {
    }

    /**
     * d
     *
     * @param x
     * @param y
     * @param life
     * @param atk
     * @param direction
     */
    public Monster(int x, int y, int width, int height, Rectangle rectangle, int life, int atk, Direction direction, GameMap gameMap, int id) {
        super(x, y, width, height, rectangle, direction);
        this.img = RED;
        this.life = life;
        this.id = id;
        this.atk = atk;
        this.gameMap = gameMap;
        aitime = System.currentTimeMillis();   //敌人创建,开始计算时间
    }

    //修正状态
    public void ai() {
        //敌人三秒更新一次状态
        if (System.currentTimeMillis() - aitime > 2000) {
            aitime = System.currentTimeMillis();
            Radom = random.nextInt(11);
            if (Radom == 1) {
                this.direction = Direction.LEFT;
                MonsterLeft = true;
                MonsterRight = false;
                MonsterUp = false;
                MonsterDown = false;
            } else if (Radom == 2) {
                this.direction = Direction.RIGHT;
                MonsterLeft = false;
                MonsterRight = true;
                MonsterUp = false;
                MonsterDown = false;
            } else if (Radom == 3) {
                this.direction = Direction.UP;
                MonsterLeft = false;
                MonsterRight = false;
                MonsterUp = true;
                MonsterDown = false;
            } else if (Radom == 4) {
                this.direction = Direction.DOWN;
                MonsterLeft = false;
                MonsterRight = false;
                MonsterUp = false;
                MonsterDown = true;
            } else if (Radom == 5 || Radom == 6) {
                if (id == 4) {
                    bossAfire();
                } else {
                    fire();
                }
            } else if (Radom == 7 || Radom == 8) {
                if (id == 4) {
                    bossAfire();
                    bossBfire();
                    bossCfire();
                } else {
                    fire();
                }
            }else if (Radom == 9 || Radom == 10) {
                if (id == 4) {
                    bossAfire();
                    bossBfire();
                    bossCfire();
                } else {
                    fire();
                }
            }
        }
    }

    //改变图片的方法
    private void changeImg(String img) {
        this.img = GameUtil.getImage(img);
    }

    //黑衣怪物移动
    public void move() {
        if (MonsterLeft) {
            leftward();
            if (!Orientationl) {
                setX(getX() - getSpeed());

            }
        } else if (MonsterRight) {
            rightward();
            if (!Orientationr) {
                setX(getX() + getSpeed());
            }
        } else if (MonsterUp) {
            upward();

            if (!Orientationu) {
                setY(getY() - getSpeed());
            }
        } else if (MonsterDown) {

            dowmward();
            if (!Orientationd) {
                setY(getY() + getSpeed());
            }
        }
    }

    //丧尸移动
    public void Smove() {
        if (MonsterLeft) {
            leftwardS();
            if (!Orientationl) {
                setX(getX() - getSpeed());
            }
        } else if (MonsterRight) {
            rightwardS();
            if (!Orientationr) {
                setX(getX() + getSpeed());
            }
        } else if (MonsterUp) {
            upwardS();
            if (!Orientationu) {
                setY(getY() - getSpeed());
            }
        } else if (MonsterDown) {
            dowmwardS();
            if (!Orientationd) {
                setY(getY() + getSpeed());
            }
        }
    }

    //飞龙
    public void F_move() {
        if (MonsterLeft) {
            c_leftward();
            if (!Orientationl) {
                setX(getX() - getSpeed());
            }
        } else if (MonsterRight) {
            c_rightwardS();
            if (!Orientationr) {
                setX(getX() + getSpeed());
            }
        } else if (MonsterUp) {
            c_upwardS();
            if (!Orientationu) {
                setY(getY() - getSpeed());
            }
        } else if (MonsterDown) {
            c_dowmwardS();
            if (!Orientationd) {
                setY(getY() + getSpeed());
            }
        }
    }

    //BOSS
    public void Boss_move() {
        if (MonsterLeft) {
            B_leftward();
            if (!Orientationl) {
                setX(getX() - getSpeed());
            }
        } else if (MonsterRight) {
            B_rightwardS();
            if (!Orientationr) {
                setX(getX() + getSpeed());
            }
        } else if (MonsterUp) {
            B_upwardS();
            if (!Orientationu) {
                setY(getY() - getSpeed());
            }
        } else if (MonsterDown) {
            B_dowmwardS();
            if (!Orientationd) {
                setY(getY() + getSpeed());
            }
        }
    }

    //黑衣怪物
    private void leftward() {
        changeImg("Statics/Monster/Ab" + GameFrame.framee + ".png");
    }

    private void upward() {
        changeImg("Statics/Monster/Ad" + GameFrame.framee + ".png");
    }

    private void rightward() {
        changeImg("Statics/Monster/Ac" + GameFrame.framee + ".png");
    }

    private void dowmward() {
        changeImg("Statics/Monster/Aa" + GameFrame.framee + ".png");
    }

    //丧尸
    private void leftwardS() {
        changeImg("Statics/Monster/Sangl" + GameFrame.framee + ".png");
    }

    private void upwardS() {
        changeImg("Statics/Monster/Sangu" + GameFrame.framee + ".png");
    }

    private void rightwardS() {
        changeImg("Statics/Monster/Sangr" + GameFrame.framee + ".png");
    }

    private void dowmwardS() {
        changeImg("Statics/Monster/Sangd" + GameFrame.framee + ".png");
    }

    //第三个怪物移动
    private void c_leftward() {
        changeImg("Statics/Monster/左边" + GameFrame.framee + ".png");
    }

    private void c_upwardS() {
        changeImg("Statics/Monster/背面" + GameFrame.framee + ".png");
    }

    private void c_rightwardS() {
        changeImg("Statics/Monster/右边" + GameFrame.framee + ".png");
    }

    private void c_dowmwardS() {
        changeImg("Statics/Monster/正面" + GameFrame.framee + ".png");
    }

    //BOSS移动
    private void B_leftward() {
        changeImg("Statics/Actor/boss_l_" + GameFrame.framee + ".png");
    }

    private void B_upwardS() {
        changeImg("Statics/Actor/boos_u_" + GameFrame.framee + ".png");
    }

    private void B_rightwardS() {
        changeImg("Statics/Actor/boos_r_" + GameFrame.framee + ".png");
    }

    private void B_dowmwardS() {
        changeImg("Statics/Actor/boos_d_" + GameFrame.framee + ".png");
    }

    //怪物受击
    public void coordonate() {
        int atkplaceX = x - 80;
        int atkplaceY = y - 80;

        //平A受击
        if (acttackcount == 1) {
            //创建帧数消亡对象
            Effects effect0 = new Effects(atkplaceX, atkplaceY, img, 1, 1);
            GameMap.effects.add(effect0);
            acttackcount = 0;
        }
        //火焰受击
        else if (acttackcount == 2) {
            Effects effect0 = new Effects(atkplaceX, atkplaceY, img, 1, 2);
            Effects effect1 = new Effects(this.x, this.y, img, 2, 5, this, actor);
            GameMap.effects.add(effect1);
            GameMap.effects.add(effect0);
            acttackcount = 0;
        }
        //寒冰受击
        else if (acttackcount == 3) {
            Effects effect0 = new Effects(atkplaceX - 40, atkplaceY - 50, img, 1, 3);
            Effects effect1 = new Effects(this.x - 90, this.y - 75, img, 2, 6, this, actor);
            GameMap.effects.add(effect1);
            GameMap.effects.add(effect0);
            acttackcount = 0;
        }
        //毒物受击
        else if (acttackcount == 4) {
            Effects effect0 = new Effects(atkplaceX, atkplaceY, img, 1, 4);
            Effects effect1 = new Effects(this.x - 80, this.y - 80, img, 2, 7, this, actor);
            GameMap.effects.add(effect1);
            GameMap.effects.add(effect0);
            acttackcount = 0;
        }
    }

    //初始化子弹并且修正子弹初始位置（默认为怪味朝向 10）
    private void fire() {
        //使子弹默认在怪物上方
        int MonsterX = x;
        int MonsterY = y;
        switch (direction) {
            case DOWN:
                MonsterY += 10;
                break;
            case UP:
                MonsterY -= 10;
                break;
            case RIGHT:
                MonsterX += 10;
                break;
            case LEFT:
                MonsterX -= 10;
                break;
        }
        //初始化子弹
        Bullet bullet = BulletsPool.get();
        bullet.setX(MonsterX);
        bullet.setY(MonsterY);
//        bullet.setImg(bulletImg);
        bullet.setWidth(32);
        bullet.setHeight(32);
        bullet.setSpeed(3);
        bullet.setId(2);
        bullet.setatk(15);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bulletsMonster.add(bullet);
    }

    //初始化子弹并且修正子弹初始位置（默认为怪味朝向 10）
    private void bossAfire() {
        //使子弹默认在怪物上方
        int MonsterX = x;
        int MonsterY = y;
        switch (direction) {
            case DOWN:
                MonsterX -= 23;
                MonsterY += 10;
                break;
            case UP:
                MonsterX -= 23;
                MonsterY -= 10;
                break;
            case RIGHT:
                MonsterY += 50;
                MonsterX += 10;
                break;
            case LEFT:
                MonsterY += 50;
                MonsterX -= 10;
                break;
        }
        //初始化子弹
        Bullet bullet = BulletsPool.get();
        bullet.setX(MonsterX);
        bullet.setY(MonsterY);
//        bullet.setImg(bulletImg);
        bullet.setWidth(140);
        bullet.setHeight(172);
        bullet.setSpeed(4);
        bullet.setId(6);
        bullet.setatk(15);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bulletsMonster.add(bullet);
    }

    //三发子弹
    private void bossBfire() {
        //使子弹默认在怪物上方
        int MonsterX = x;
        int MonsterY = y;
        switch (direction) {
            case DOWN:
                MonsterX -= 63;
                MonsterY += 10;
                break;
            case UP:
                MonsterX -= 63;
                MonsterY -= 10;
                break;
            case RIGHT:
                MonsterY += 115;
                MonsterX += 10;
                break;
            case LEFT:
                MonsterY += 115;
                MonsterX -= 10;
                break;
        }
        //初始化子弹
        Bullet bullet = BulletsPool.get();
        bullet.setX(MonsterX);
        bullet.setY(MonsterY);
//        bullet.setImg(bulletImg);
        bullet.setWidth(140);
        bullet.setHeight(172);
        bullet.setSpeed(4);
        bullet.setId(6);
        bullet.setatk(15);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bulletsMonster.add(bullet);
    }

    private void bossCfire() {
        //使子弹默认在怪物上方
        int MonsterX = x;
        int MonsterY = y;
        switch (direction) {
            case DOWN:
                MonsterX -= -17;
                MonsterY += 10;
                break;
            case UP:
                MonsterX -= -17;
                MonsterY -= 10;
                break;
            case RIGHT:
                MonsterY += 0;
                MonsterX += 10;
                break;
            case LEFT:
                MonsterY += 0;
                MonsterX -= 10;
                break;
        }
        //初始化子弹
        Bullet bullet = BulletsPool.get();
        bullet.setX(MonsterX);
        bullet.setY(MonsterY);
//        bullet.setImg(bulletImg);
        bullet.setWidth(140);
        bullet.setHeight(172);
        bullet.setSpeed(4);
        bullet.setId(6);
        bullet.setatk(15);
        bullet.setDirection(direction);
        bullet.setVisible(true);
        bulletsMonster.add(bullet);
    }

    //绘制子弹（怪物子弹 id==2）
    public void drawBullets(Graphics g) {
        for (Bullet bullet : bulletsMonster) {
            bullet.draw(g);
        }
        //遍历所有子弹 将子弹还原至对象池  1. 碰撞墙体  2.打击敌人
        for (int i = 0; i < bulletsMonster.size(); i++) {
            if (!bulletsMonster.get(i).isVisible()) {
                BulletsPool.theReturn(bulletsMonster.remove(i));
            }
        }
    }

    //绘制短时 长时特效
    public void drawEffects(Graphics g) {
        if (visible) {
            for (int k = 0; k < GameMap.effects.size(); k++) {
                if (GameMap.effects.get(k).uid == 1) {
                    GameMap.effects.get(k).drawEffcts(g, 1);
                } else if (GameMap.effects.get(k).uid == 2) {
                    GameMap.effects.get(k).drawEffcts(g, 2);
                } else if (GameMap.effects.get(k).uid == 3) {
                    GameMap.effects.get(k).drawEffcts(g, 3);
                } else if (GameMap.effects.get(k).uid == 4) {
                    GameMap.effects.get(k).drawEffcts(g, 4);
                } else if (GameMap.effects.get(k).uid == 5) {
                    GameMap.effects.get(k).drawEffcts(g, 5);
                } else if (GameMap.effects.get(k).uid == 6) {
                    GameMap.effects.get(k).drawEffcts(g, 6);
                } else if (GameMap.effects.get(k).uid == 7) {
                    GameMap.effects.get(k).drawEffcts(g, 7);
                } else if (GameMap.effects.get(k).uid == 8) {
                    GameMap.effects.get(k).drawEffcts(g, 8);
                } else if (GameMap.effects.get(k).uid == 9) {
                    GameMap.effects.get(k).drawEffcts(g, 9);
                }
                //角色受击
                else if (GameMap.effects.get(k).uid == 10) {
                    GameMap.effects.get(k).drawEffcts(g, 10);
                }
                //boss 进场特效
                else if (GameMap.effects.get(k).uid == 14) {
                    GameMap.effects.get(k).drawEffcts(g, 14);
                }

            }
        }

    }

    //绘制特效

    //怪物子弹与墙体 与 我方的碰撞检测


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //物体矩形获取
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    //修改这个矩形物体
    public void setRect(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    //碰撞方向

    //获取生命值
    public int getLife() {
        return life;
    }

    //修改生命值
    public void setLife(int life) {
        this.life = life;
    }

    public void setOrientationl(boolean orientationl) {
        Orientationl = orientationl;
    }

    public void setOrientationr(boolean orientationr) {
        Orientationr = orientationr;
    }

    public void setOrientationu(boolean orientationu) {
        Orientationu = orientationu;
    }

    public void setOrientationd(boolean orientationd) {
        Orientationd = orientationd;
    }

    public void setRadom(int radom) {
        Radom = radom;
    }

    public void setAitime(long aitime) {
        this.aitime = aitime;
    }

    public void setActtackcount(int acttackcount) {
        this.acttackcount = acttackcount;
    }

    public String toString() {
        return " x： " + x + " y： " + y + " 方向： " + direction;
    }

}
