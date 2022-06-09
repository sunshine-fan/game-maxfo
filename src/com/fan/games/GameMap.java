package com.fan.games;

import com.fan.games.Tool.GameUtil;
import com.sun.org.apache.bcel.internal.generic.FADD;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.message.MessageWritable;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Random;

public class GameMap {

    // 属性值
    private int x = 20;
    private int y = -1035;
    private int w;
    private int h;
    private Image img;

    //读取地图数据
    private List<String> lineData = new ArrayList<>();
    private int[][] originData;

    //游戏背景  主背景
    private Image GameBg = GameUtil.getImage("Statics/bg/white.png");
    public Image GameBg2 = GameUtil.getImage("Statics/bg/bg08.png");
    //Tree
    private Image tree = GameUtil.getImage("Statics/bg/tree01.png");
    //Image GameBg = GameUtil.getImage("Statics/bg/bg2.png");
    //阴影
    private Image UI_black = GameUtil.getImage("Statics/UI/UI_black.png");
    //商店
    private Image shopImg = GameUtil.getImage("Statics/Actor/shop4.png");
    //BOSS黑幕
    private Image IMGBK = GameUtil.getImage("Statics/boss/IMGBK.png");
    private Image win = GameUtil.getImage("Statics/over/win.png");

    /**
     * 游戏道具
     */
    //HP MP
    private Image HP = GameUtil.getImage("Statics/consumables/item_4.png");
    private Image MP = GameUtil.getImage("Statics/consumables/item_5.png");
    //补给品
    private Image apple = GameUtil.getImage("Statics/consumables/item_1.png");
    private Image grape = GameUtil.getImage("Statics/consumables/item_2.png");
    private Image bread = GameUtil.getImage("Statics/consumables/item_3.png");
    //魔法书
    private Image Redbook = GameUtil.getImage("Statics/consumables/item_6.png");
    private Image Bluebook = GameUtil.getImage("Statics/consumables/item_7.png");
    //卷轴
    private Image beamer = GameUtil.getImage("Statics/consumables/item_8.png");
    //头盔1 2
    private Image hat_a = GameUtil.getImage("Statics/consumables/hat1.png");
    private Image hat_b = GameUtil.getImage("Statics/consumables/hat2.png");
    //盔甲4
    private Image corselet_a = GameUtil.getImage("Statics/consumables/equip2.png");
    private Image corselet_b = GameUtil.getImage("Statics/consumables/equip3.png");
    //武器
    private Image weapon_a = GameUtil.getImage("Statics/consumables/weapon_1.png");
    private Image weapon_b = GameUtil.getImage("Statics/consumables/weapon_2.png");
    //金币
    private Image Coin = GameUtil.getImage("Statics/consumables/coin.png");

    //钥匙
    private Image keyImg = GameUtil.getImage("Statics/consumables/I_Key01.png");
    //UI
    private Image skill_bar = GameUtil.getImage("Statics/UI/item_back.png");
    private Image STOP = GameUtil.getImage("Statics/UI/stop.png");
    //对象引用  1.碰撞检测2.子弹类3.公共数据4.角色5.不通关卡敌人
    Rectangle rectangle;
    //引用子弹对象
    Bullet bullet;
    //接受数据共享
    Config config;
    public Actor actor = new Actor(184, 400, 32, 35, rectangle, Direction.UP, bullet, this, config);
    //生成测试网格
    Grid grid = new Grid(x, y);
    //窗口对象
    GameFrame gameFrame;
    //随机数
    private Random random = new Random();


    //空气墙容器
    public static ArrayList<Square> wallList = new ArrayList<>();
    //ALL掉落落集合：铠甲 头盔  hp mp 葡萄 苹果 面包 武器 金币 钥匙 STOP
    public static ArrayList<ItemDrop> Allconsumables = new ArrayList<>();
    //ALL背包容器
    public static ArrayList<ItemDrop> Allconsumablesl = new ArrayList<>();
    //葡萄容器    ID:1
    public static ArrayList<ItemDrop> dropA = new ArrayList<>();
    //苹果容器    ID:2
    public static ArrayList<ItemDrop> dropB = new ArrayList<>();
    //面包容器   ID:3
    public static ArrayList<ItemDrop> dropC = new ArrayList<>();
    //HP容器   ID:4
    public static ArrayList<ItemDrop> dropD = new ArrayList<>();
    //MP容器      ID:5
    public static ArrayList<ItemDrop> dropE = new ArrayList<>();
    //红色魔法书    ID:6
    public static ArrayList<ItemDrop> dropF = new ArrayList<>();
    //蓝色魔法书     ID:7
    public static ArrayList<ItemDrop> dropG = new ArrayList<>();
    //卷轴容器     ID:8
    public static ArrayList<ItemDrop> dropH = new ArrayList<>();
    //金币容器   ID:9
    public static ArrayList<ItemDrop> dropI = new ArrayList<>();
    //头盔容器     ID:10
    public static ArrayList<ItemDrop> dropJ = new ArrayList<>();
    //铠甲容器    ID:11
    public static ArrayList<ItemDrop> dropK = new ArrayList<>();
    //武器容器         ID 16 17
    public static ArrayList<ItemDrop> dropZ = new ArrayList<>();
    //金币容器     ID:12
    public static ArrayList<ItemDrop> dropM = new ArrayList<>();
    //钥匙容器     ID:13
    public static ArrayList<ItemDrop> dropN = new ArrayList<>();
    //初始化钥匙位置容器
    //STOP容器     ID:14
    public static ArrayList<ItemDrop> dropO = new ArrayList<>();
    //商城容器
    public static ArrayList<ItemDrop> dropP = new ArrayList<>();
    //特效容器
    static ArrayList<Effects> effects = new ArrayList<>();

    //第一关（忍者  近身攻击）
    Monster monster = new Monster(750, 350, 32, 35, rectangle, 100, 5, Direction.DOWN, this, 1);
    //怪物容器
    public static ArrayList<Monster> monsters = new ArrayList<>();

    //初始化
    public GameMap(Config config, GameFrame gameFrame) {
        this.img = GameBg;
        this.x = 20;
        this.y = -1035;
        this.config = config;
        this.gameFrame = gameFrame;
        //动态初始化
        Initialize();
        //静态初始化
        initialized();
    }

    //关于关卡
    //初始化地图上已经拥有的对象  （当钥匙数量发生改变时 自调用当前对象）
    public void Initialize() {
        //初始化怪物位置
        if (dropN.size() == 0) {
            for (int i = 0; i < 5; i++) {
                monsters.add(new Monster(650 + Config.ScollX + random.nextInt(6) * 45, 250 + Config.ScollY + random.nextInt(5) * 35, 32, 30, rectangle, 200, 5, Direction.DOWN, this, 1));
            }
        } else if (dropN.size() == 1) {
            for (int i = 0; i < 5; i++) {
                monsters.add(new Monster(980 + Config.ScollX + random.nextInt(6) * 45, -320 + Config.ScollY + random.nextInt(5) * 30, 32, 30, rectangle, 300, 5, Direction.DOWN, this, 2));
            }
            for (int i = 0; i < 3; i++) {
                monsters.add(new Monster(680 + Config.ScollX + random.nextInt(6) * 45, -320 + Config.ScollY + random.nextInt(5) * 30, 32, 30, rectangle, 200, 10, Direction.DOWN, this, 1));
            }
        } else if (dropN.size() == 2) {
            for (int i = 0; i < 5; i++) {
                monsters.add(new Monster(250 + Config.ScollX + random.nextInt(5) * 45, -610 + Config.ScollY + random.nextInt(5) * 35, 32, 30, rectangle, 300, 10, Direction.DOWN, this, 2));
            }
            for (int i = 0; i < 3; i++) {
                monsters.add(new Monster(150 + Config.ScollX + random.nextInt(5) * 45, -910 + Config.ScollY + random.nextInt(5) * 35, 32, 30, rectangle, 400, 10, Direction.DOWN, this, 3));
            }
        } else if (dropN.size() == 4) {
            //BOSS出现
            Effects effect0 = new Effects(10, 0, img, 1, 14);
            effects.add(effect0);
            for (int i = 0; i < 3; i++) {
                monsters.add(new Monster(960 + Config.ScollX + random.nextInt(5) * 45, -810 + Config.ScollY + random.nextInt(5) * 35, 32, 30, rectangle, 400, 15, Direction.DOWN, this, 3));
                monsters.add(new Monster(1100 + Config.ScollX + random.nextInt(5) * 45, -710 + Config.ScollY + random.nextInt(5) * 35, 32, 30, rectangle, 300, 15, Direction.DOWN, this, 2));
            }
            monsters.add(new Monster(960 + Config.ScollX + random.nextInt(5) * 45, -850+ Config.ScollY + random.nextInt(5) * 25, 80, 80, rectangle, 1000, 30, Direction.DOWN, this, 4));
        } else if (dropN.size() == 5) {
        }
    }

    public void initialized() {
        //自己调用地图读取
        {
            try {
                createSquare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //初始化STOP
        dropO.add(new ItemDrop(982, 182, 96, 32, 0, 0, 14, 1, 1, STOP, rectangle));
        dropO.add(new ItemDrop(405, -330, 96, 32, 0, 0, 14, 2, 2, STOP, rectangle));
        dropO.add(new ItemDrop(632, -810, 32, 96, 0, 0, 14, 3, 3, STOP, rectangle));
        //初始化商店
        dropP.add(new ItemDrop(440, 90, 32, 32, 15, 15, 0, 1, shopImg, rectangle));
        dropP.add(new ItemDrop(1100, -70, 32, 32, 15, 15, 0, 1, shopImg, rectangle));
        dropP.add(new ItemDrop(500, -240, 32, 32, 15, 15, 0, 1, shopImg, rectangle));
        dropP.add(new ItemDrop(300, -990, 32, 32, 15, 15, 0, 1, shopImg, rectangle));
        //初始化商品
        Allconsumables.add(new ItemDrop(400, 120, 32, 32, 0, 0, 4, 1, 10, 0, true, HP, rectangle));
        Allconsumables.add(new ItemDrop(440, 120, 32, 32, 10, 0, 10, 1, 0, 10, true, hat_a, rectangle));
        Allconsumables.add(new ItemDrop(480, 120, 32, 32, 0, 0, 5, 1, 0, 10, true, MP, rectangle));

        Allconsumables.add(new ItemDrop(1060, -43, 32, 32, 0, 15, 5, 1, 0, 10, true, MP, rectangle));
        Allconsumables.add(new ItemDrop(1100, -43, 32, 32, 15, 0, 12, 1, 0, 10, true, corselet_a, rectangle));
        Allconsumables.add(new ItemDrop(1140, -43, 32, 32, 0, 0, 16, 1, 5, 10, true, weapon_a, rectangle));

        Allconsumables.add(new ItemDrop(460, -210, 32, 32, 10, 0, 6, 1, 0, 10, true, Redbook, rectangle));
        Allconsumables.add(new ItemDrop(500, -210, 32, 32, 35, 0, 11, 1, 0, 10, true, hat_b, rectangle));
        Allconsumables.add(new ItemDrop(540, -210, 32, 32, 0, 15, 7, 1, 0, 10, true, Bluebook, rectangle));

        Allconsumables.add(new ItemDrop(260, -960, 32, 32, 35, 15, 15, 1, 1, 10, true, corselet_b, rectangle));
        Allconsumables.add(new ItemDrop(300, -960, 32, 32, 25, 15, 17, 1, 15, 10, true, weapon_b, rectangle));
        Allconsumables.add(new ItemDrop(340, -960, 32, 32, 0, 15, 7, 1, 1, 10, true, Bluebook, rectangle));

        //初始化金币
        for (int i = 0; i < 4; i++) {
            dropI.add(new ItemDrop(440, 120, 32, 32, 15, 15, 13, 1, 0, 10, true, keyImg, rectangle));
        }
    }

    /**
     * 验证map类的readMap() 方法 确实把地图配置文件加载成了二维数组 map.txt
     */
//    public void testResult() throws Exception {
//        int[][] result = readGetMap();
//        // 二维数组的内容输出，看一下是否是地图的配置信息
//        for(int i = 0 ; i < result.length ; i++ ){
//            for(int j = 0 ; j < result[i].length ; j++) {
//                System.out.print(result[i][j]+" ");
//            }
//            System.out.println();
//        }
//    }
    public void readGetMap() throws Exception {

        FileInputStream fis = new FileInputStream("src/map.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        for (String value = br.readLine(); value != null; value = br.readLine()) {
            lineData.add(value);
        }
        // 关闭输入流
        br.close();
        //  确定行高
        int row = lineData.size();
        int cloum = 0;
        // 读取一行 确定列数
        for (int i = 0; i < 1; i++) {
            String str = lineData.get(i);
            //用 , 分割 str 字符串，并且将分割后的字符串数组赋值给 buff。
            String[] values = str.split(",");
            cloum = values.length;
        }
        //行列清楚表明
        originData = new int[row][cloum];
        //将读到的字符转换为整数，并赋值给二维数组map
        for (int i = 0; i < lineData.size(); i++) {
            String str = lineData.get(i);
            String[] values = str.split(",");
            for (int j = 0; j < values.length; j++) {
                originData[i][j] = Integer.parseInt(values[j]);
            }
        }
    }
    //地图矩形绘制
    public void createSquare() throws Exception {
        // 读取地图
        readGetMap();
        for (int i = 0; i < originData.length; i++) {
            for (int j = 0; j < originData[0].length; j++) {
                if (originData[i][j] == 1) {
                    wallList.add(new Square(x + j * 32, y + 32 * i, 32, 32, rectangle));
                }
            }
        }
    }

    //水平移动
    public void moveHorizon() {
        //空气墙碰撞
        for (int i = 0; i < wallList.size(); i++) {
            if (actor.x > 640 && actor.x < 960) {
                if (x < -515) return;
                Config.ScollX -= actor.getSpeed();
                x -= actor.getSpeed();
                //人物卷动
                actor.setX(actor.getX() - actor.getSpeed());  //静态
                //怪物卷动
                for (int j = 0; j < monsters.size(); j++) {
                    monsters.get(j).setX(monsters.get(j).getX() - actor.getSpeed());
                }

                //网格卷动
                grid.setX(x);
                //我方子弹卷动
                for (int j = 0; j < Actor.bullets.size(); j++) {
                    Actor.bullets.get(j).setX(Actor.bullets.get(j).getX() - actor.getSpeed());
                }
                //敌方子弹卷动
                for (int j = 0; j < Monster.bulletsMonster.size(); j++) {
                    Monster.bulletsMonster.get(j).setX(Monster.bulletsMonster.get(j).getX() - actor.getSpeed());
                }
                //空气墙卷动
                for (int j = 0; j < wallList.size(); j++) {
                    wallList.get(j).setX(wallList.get(j).getX() - actor.getSpeed());
                }
                //掉落物品卷动
                for (ItemDrop allconsumable : Allconsumables) {
                    allconsumable.setX(allconsumable.getX() - actor.getSpeed());
                }
                //STOP符卷动
                for (ItemDrop drop : dropO) {
                    drop.setX(drop.getX() - actor.getSpeed());
                }
                //特效卷动
//                for (Effects effect :effects) {
//                    effect.setX(effect.getX()-actor.getSpeed());
//                }
                //商店卷动
                for (ItemDrop drop : dropP) {
                    drop.setX(drop.getX() - actor.getSpeed());
                }

            } else if (actor.x < 320 && actor.x > 0) {
                if (x > 15) return;
                Config.ScollX += actor.getSpeed();
                x += actor.getSpeed();
                //人物卷动
                actor.setX(actor.getX() + actor.getSpeed());
                //怪物卷动
                for (int j = 0; j < monsters.size(); j++) {
                    monsters.get(j).setX(monsters.get(j).getX() + actor.getSpeed());
                }
                //网格卷动
                grid.setX(x);
                wallList.get(i).setX(wallList.get(i).getX() + actor.getSpeed());
                for (int j = 0; j < wallList.size(); j++) {
                    wallList.get(j).setX(wallList.get(j).getX() + actor.getSpeed());
                }
                //我方子弹卷动
                for (int j = 0; j < Actor.bullets.size(); j++) {
                    Actor.bullets.get(j).setX(Actor.bullets.get(j).getX() + actor.getSpeed());
                }
                //敌方子弹卷动
                for (int j = 0; j < Monster.bulletsMonster.size(); j++) {
                    Monster.bulletsMonster.get(j).setX(Monster.bulletsMonster.get(j).getX() + actor.getSpeed());
                }
                //挑落物品卷动
                for (ItemDrop allconsumable : Allconsumables) {
                    allconsumable.setX(allconsumable.getX() + actor.getSpeed());
                }
                //STOP符卷动
                for (ItemDrop drop : dropO) {
                    drop.setX(drop.getX() + actor.getSpeed());
                }
                //特效卷动
//                for (Effects effect : effects) {
//                    effect.setX(effect.getX()+actor.getSpeed());
//                }
                //商店卷动
                for (ItemDrop drop : dropP) {
                    drop.setX(drop.getX() + actor.getSpeed());
                }
            } else {
            }
        }
    }

    /**
     * 垂直移动
     */
    public void moveVertical() {
        if (actor.y < 188 && actor.y > 0) {
            if (y > 10) return;
            //地图卷动X
            Config.ScollY += actor.getSpeed();
            y += actor.getSpeed();
            //人物卷动
            actor.setY(actor.getY() + actor.getSpeed());
            //怪网格卷动物卷动
            for (int j = 0; j < monsters.size(); j++) {
                monsters.get(j).setY(monsters.get(j).getY() + actor.getSpeed());
            }
            //
            grid.setY(y);
            for (int i = 0; i < wallList.size(); i++) {
                wallList.get(i).setY(wallList.get(i).getY() + actor.getSpeed());
            }
            //我方子弹卷动
            for (int j = 0; j < Actor.bullets.size(); j++) {
                Actor.bullets.get(j).setY(Actor.bullets.get(j).getY() + actor.getSpeed());
            }
            //敌方子弹卷动
            for (int j = 0; j < Monster.bulletsMonster.size(); j++) {
                Monster.bulletsMonster.get(j).setY(Monster.bulletsMonster.get(j).getY() + actor.getSpeed());
            }
            //挑落物品卷动
            for (ItemDrop allconsumable : Allconsumables) {
                allconsumable.setY(allconsumable.getY() + actor.getSpeed());
            }
            //STOP符卷动
            for (ItemDrop drop : dropO) {
                drop.setY(drop.getY() + actor.getSpeed());
            }
            for (ItemDrop drop : dropP) {
                drop.setY(drop.getY() + actor.getSpeed());
            }
        } else if (actor.y > 458 && actor.y < 610) {
            if (y < -1160) return;
            //地图卷动X
            Config.ScollY -= actor.getSpeed();
            y -= actor.getSpeed();
            //人物卷动
            actor.setY(actor.getY() - actor.getSpeed());
            //怪物卷动
            for (int j = 0; j < monsters.size(); j++) {
                monsters.get(j).setY(monsters.get(j).getY() - actor.getSpeed());
            }

            //网格卷动
            grid.setY(y);
            for (int i = 0; i < wallList.size(); i++) {
                wallList.get(i).setY(wallList.get(i).getY() - actor.getSpeed());
            }
            //我方子弹卷动
            for (int j = 0; j < Actor.bullets.size(); j++) {
                Actor.bullets.get(j).setY(Actor.bullets.get(j).getY() - actor.getSpeed());
            }
            //敌方子弹卷动
            for (int j = 0; j < Monster.bulletsMonster.size(); j++) {
                Monster.bulletsMonster.get(j).setY(Monster.bulletsMonster.get(j).getY() - actor.getSpeed());
            }
            //挑落物品卷动
            for (ItemDrop allconsumable : Allconsumables) {
                allconsumable.setY(allconsumable.getY() - actor.getSpeed());
            }
            //STOP符卷动
            for (ItemDrop drop : dropO) {
                drop.setY(drop.getY() - actor.getSpeed());
            }
            for (ItemDrop drop : dropP) {
                drop.setY(drop.getY() - actor.getSpeed());
            }

        } else {
        }

        if (actor.x < 0) {
            x += actor.getSpeed();
        }
    }

    //绘制所有元素
    public void update(Graphics g) {
        //背景层
        g.drawImage(GameBg2, x, y, null);

        //绘制空气墙体
        if (config.istext) {
            for (int i = 0; i < this.wallList.size(); i++) {
                wallList.get(i).drawX(g);
            }
        }
        //网格
        //grid.drawSelf(g);
        MonsterandActor();
        //怪物消失
        remove();
        //地图卷动方法
        this.moveHorizon();
        this.moveVertical();
        //子弹碰撞检测
        bulletCollideActor(g);
        //绘制怪物及怪物血条    //一关  怪物
        if (dropN.size() == 0) {
            if (monsters.size() != 0) {
                for (int i = 0; i < monsters.size(); i++) {
                    monsters.get(i).drawSelf(g);
                    monsters.get(i).ai();
                    monsters.get(i).move();
                    monsters.get(i).coordonate();
                    g.setColor(Color.blue);
                    g.drawRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, 40, 2);
                    g.setColor(Color.red);
                    g.fillRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, (int) (monsters.get(i).getLife() * 0.2), 3);
                }
            }
        }
        //第二关怪物绘制
        if (dropN.size() == 1) {
            if (monsters.size() != 0) {
                for (int i = 0; i < monsters.size(); i++) {
                    monsters.get(i).drawSelf(g);
                    monsters.get(i).ai();
                    monsters.get(i).coordonate();
                    if (monsters.get(i).id == 2) {
                        monsters.get(i).Smove();
                    } else if (monsters.get(i).id == 1) {
                        monsters.get(i).move();
                    }
                    g.setColor(Color.blue);
                    g.drawRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, 40, 2);
                    g.setColor(Color.red);
                    g.fillRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, (int) (monsters.get(i).getLife() * 0.2), 3);
                }
            }

        }
        //第三关怪物绘制
        if (dropN.size() == 2) {
            if (monsters.size() != 0) {
                for (int i = 0; i < monsters.size(); i++) {
                    monsters.get(i).drawSelf(g);
                    monsters.get(i).coordonate();
                    monsters.get(i).ai();
//                    monsters.get(i).move();
                    if (monsters.get(i).id == 2) {
                        monsters.get(i).Smove();
                    } else if (monsters.get(i).id == 3) {
                        monsters.get(i).F_move();
                    }
                    g.setColor(Color.blue);
                    g.drawRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, 40, 2);
                    g.setColor(Color.red);
                    g.fillRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, (int) (monsters.get(i).getLife() * 0.2), 3);
                }
            }
        }
        //BOSS怪物绘制
        if (dropN.size() == 4) {
            g.drawImage(IMGBK, 25, 0, null);
            if (monsters.size() != 0) {
                for (int i = 0; i < monsters.size(); i++) {
                    monsters.get(i).drawSelf(g);
                    monsters.get(i).coordonate();
                    monsters.get(i).ai();
                    if (monsters.get(i).id == 2) {
                        monsters.get(i).Smove();
                        g.setColor(Color.blue);
                        g.drawRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, 40, 2);
                        g.setColor(Color.red);
                        g.fillRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, (int) (monsters.get(i).getLife() * 0.2), 3);
                    } else if (monsters.get(i).id == 3) {
                        monsters.get(i).F_move();
                        g.setColor(Color.blue);
                        g.drawRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, 40, 2);
                        g.setColor(Color.red);
                        g.fillRect(monsters.get(i).getX() - 12, monsters.get(i).getY() - 8, (int) (monsters.get(i).getLife() * 0.2), 3);
                    } else if (monsters.get(i).id == 4) {
                        monsters.get(i).Boss_move();
                        //敌方HP
                        g.setColor(Color.black);
                        g.drawRect(monsters.get(i).getX() - 60, monsters.get(i).getY() - 15, 80, 10);
                        g.setColor(Color.red);
                        g.fillRect(monsters.get(i).getX() - 60, monsters.get(i).getY() - 15, (int) (monsters.get(i).getLife() * 0.2), 11);
                        g.setColor(Color.white);
                        g.drawString("Boss" + monsters.get(i).getLife(), monsters.get(i).getX(), monsters.get(i).getY() - 30);
                    }
                }
            }
        }
        actor.move();
        actor.drawSelf(g);
        //画我方子弹
        actor.drawBullets(g);
        monster.drawBullets(g);
        //小树层
        g.drawImage(tree, x, y, null);
        //商店绘制
        for (ItemDrop drop : dropP) {
            drop.drawSelf(g);
        }
        //绘制空气墙
        for (int i = 0; i < wallList.size(); i++) {
            wallList.get(i).drawX(g);
        }
        //绘制所有掉落物品  跟商店物品
        for (ItemDrop drop : Allconsumables) {
            Font l = new Font("宋体", Font.CENTER_BASELINE, 12);
            g.setFont(l);
            drop.drawSelf(g);
            g.setColor(Color.white);
            switch (drop.id) {
                case 1:
                    g.drawString("葡萄", drop.getX(), drop.getY() + 35);
                    break;
                case 2:
                    g.drawString("苹果", drop.getX(), drop.getY() + 35);
                    break;
                case 3:
                    g.drawString("面包", drop.getX(), drop.getY() + 35);
                    break;
                case 4:
                    g.drawString("HP", drop.getX() + 3, drop.getY() + 35);
                    break;
                case 5:
                    g.drawString("MP", drop.getX() + 8, drop.getY() + 35);
                    break;
                case 6:
                    g.drawString("红色魔法书", drop.getX() - 10, drop.getY() + 35);
                    break;
                case 7:
                    g.drawString("蓝色魔法书", drop.getX() - 20, drop.getY() + 35);
                    break;
                case 8:
                    g.drawString("卷轴", drop.getX() - 20, drop.getY() + 35);
                    break;
                case 9:
                    g.drawString("金币", drop.getX(), drop.getY() + 35);
                    break;
                case 10:
                    g.drawString("头盔", drop.getX(), drop.getY() + 35);
                    break;
                case 11:
                    g.drawString("遗失的头盔", drop.getX(), drop.getY() + 35);
                    break;
                case 12:
                    g.drawString("盔甲", drop.getX() - 40, drop.getY() + 35);
                    break;
                case 13:
                    g.setColor(Color.yellow);
                    g.drawString("钥匙", drop.getX(), drop.getY() + 35);
                    break;
                case 14:
                    g.drawString("STOP", drop.getX(), drop.getY() + 25);
                    break;
                case 15:
                    g.drawString("遗失的盔甲", drop.getX(), drop.getY() + 25);
                    break;
                case 16:
                    g.drawString("弓弩", drop.getX(), drop.getY() + 25);
                    break;
                case 17:
                    g.drawString("传说中的法杖", drop.getX(), drop.getY() + 25);
                    break;
            }
        }
        //画打击特效
        monster.drawEffects(g);
        //绘制top
        for (ItemDrop drop : dropO) {
            drop.drawSelf(g);
        }
        //UI  栏目阴影
        g.drawImage(UI_black, 0, 0, null);
        //UI  装备栏目
        g.drawImage(skill_bar, 30, -17, null);

        //头盔切换 判断
        if (dropJ.size() != 0) {
            if (dropJ.get(0).id == 10) {
                g.drawImage(hat_a, 46, 442, null);
            } else if (dropJ.get(0).id == 11) {
                g.drawImage(hat_b, 46, 442, null);
            }
        }
        //盔甲切换判断
        if (dropM.size() != 0) {
            if (dropM.get(0).id == 12) {
                g.drawImage(corselet_a, 46, 490, null);
            } else if (dropM.get(0).id == 15) {
                g.drawImage(corselet_b, 46, 490, null);
            }
        }
        //武器切换
        if (dropZ.size() != 0) {
            if (dropZ.get(0).id == 16) {
                g.drawImage(weapon_a, 142, 584, null);
            } else if (dropZ.get(0).id == 17) {
                g.drawImage(weapon_b, 142, 584, null);
            }
        }
        //我方HP
        g.setColor(Color.white);
        Font f = new Font("宋体", Font.CENTER_BASELINE, 10);
        g.setFont(f);
        g.drawString("HPMAX" + config.HPmax + "/" + "HP" + config.HP, 250, 56);
        g.drawString("MPMAX" + config.MPmax + "/" + "MP" + config.MP, 250, 76);
        g.setColor(Color.black);
        g.drawRect(40, 48, (int) config.HPmax, 10);
        g.setColor(new Color(177, 17, 22));
        g.fillRect(40, 48, (int) config.HP, 11);
        //我方MP
        g.setColor(Color.yellow);
        g.drawRect(40, 68, (int) config.MPmax, 10);
        g.setColor(Color.white);
        g.fillRect(40, 68, (int) config.MP, 11);
        //绘制数量
        count(g);
        if (dropN.size() == 5) {
            g.drawImage(win, 0, 0, null);
        }

    }

    //绘制道具数量的方法
    public void count(Graphics g) {
        if (config.HP > config.HPmax) {
            config.HP = config.HPmax;
        }
        if (config.MP > config.MPmax) {
            config.MP = config.MPmax;
        }
        g.setColor(Color.white);
        //血瓶数量
        g.drawString("*" + dropD.size(), 330, 620);
        //MP数量
        g.drawString("*" + dropE.size(), 370, 620);
        //葡萄数量
        g.drawString("*" + dropA.size(), 250, 620);
        //苹果数量
        g.drawString("*" + dropB.size(), 210, 620);
        //面包数量
        g.drawString("*" + dropC.size(), 290, 620);
        g.setColor(Color.yellow);
        //金币数量
        g.drawString("*" + dropI.size(), 485, 620);
        //钥匙数量
        g.drawString(dropN.size() + "/5", 550, 620);
        //现存敌人数量
//        g.drawString("*"+dropE.size(),x,y);
    }

    //子弹改变图片的方法
    private void CutImg(String img) {
        this.img = GameUtil.getImage(img);
    }

    // 敌人与角色子弹的碰撞逻辑
    private void bulletCollideActor(Graphics g) {
        //子弹集合不为空
        if (Actor.bullets != null) {
            for (Bullet bullet : Actor.bullets) {
                //我方子弹与敌人碰撞
                for (Monster monster : this.monsters) {
                    if (bullet.getRect().intersects(monster.getRect())) {
                        //子弹消失
                        //1.角色普通攻击2.怪物普通攻击3.角色火焰4.角色寒冰5.角色藤曼
                        if (bullet.id == 1) {
                            monster.setActtackcount(1);
                            bullet.setVisible(false);
                            //怪物减去血量
                            monster.setLife(monster.getLife() - actor.getAtk());
                        } else if (bullet.id == 3) {
                            monster.setActtackcount(2);
//                            bullet.setVisible(false);
                            //怪物减去血量
                            monster.setLife(monster.getLife() - actor.getAtk());
                        } else if (bullet.id == 4) {
                            monster.setActtackcount(3);
//                            bullet.setVisible(false);
                            //怪物减去血量
                            monster.setLife(monster.getLife() - actor.getAtk());
                        } else if (bullet.id == 5) {
                            monster.setActtackcount(4);
//                            bullet.setVisible(false);
                            //怪物减去血量
                            monster.setLife(monster.getLife() - actor.getAtk());
                        }
                        break;
                    }
                }
                //我方子弹与墙体碰撞
                for (Square square : wallList) {
                    if (bullet.getRect().intersects(square.getRect())) {
                        bullet.setVisible(false);
                        break;
                    }
                }
            }
        }
        //遍历特效子弹
        //敌人所有子弹与角色的碰撞
        if (Monster.bulletsMonster != null) {
            for (Bullet bullet1 : Monster.bulletsMonster) {
                if (bullet1.getRect().intersects(actor.getRect())) {
                    //子弹消失
                    actor.setActorbeat(1);
                    bullet1.setVisible(false);
                    config.HP -= bullet1.getatk();
                }
            }
            //敌人子弹与墙体
            for (Bullet bullet1 : Monster.bulletsMonster) {
                for (Square square : wallList) {
                    if (bullet1.getRect().intersects(square.getRect())) {
                        //子弹消失
                        if (bullet1.id == 4) {
                            break;
                        } else if (bullet1.id != 4) {
                            bullet1.setVisible(false);
                        }
                    }
                }
            }
        }
    }

    //敌人与角色    的碰撞逻辑
    public void MonsterandActor() {
        for (Monster monster1 : monsters) {
            //每一个敌人状态重置
            monster1.Orientationl = false;
            monster1.Orientationr = false;
            monster1.Orientationu = false;
            monster1.Orientationd = false;
            //敌人四个方向的提前预判（左 右 上 下 矩形块）
            Rectangle rectanglel = new Rectangle(monster1.getRect().x - 5, monster1.getRect().y, monster1.getWidth(), monster1.getHeight());
            Rectangle rectangler = new Rectangle(monster1.getRect().x + 5, monster1.getRect().y, monster1.getWidth(), monster1.getHeight());
            Rectangle rectangleu = new Rectangle(monster1.getRect().x, monster1.getRect().y - 5, monster1.getHeight(), monster1.getWidth());
            Rectangle rectangled = new Rectangle(monster1.getRect().x, monster1.getRect().y + 5, monster1.getHeight(), monster1.getWidth());
            //敌人与墙发生碰撞就切换敌人状态为移动，计时器清零操作没有完成
            for (Square square : wallList) {
                if (rectanglel.intersects(square.getRect())) {
                    monster1.setOrientationl(true);
                    monster1.setRadom(random.nextInt(4));
                }
                if (rectangler.intersects(square.getRect())) {
                    monster1.setOrientationr(true);
                    monster1.setRadom(random.nextInt(4));
                }
                if (rectangleu.intersects(square.getRect())) {
                    monster1.setOrientationu(true);
                    monster1.setRadom(random.nextInt(4));
                }
                if (rectangled.intersects(square.getRect())) {
                    monster1.setOrientationd(true);
                    monster1.setRadom(random.nextInt(4));
                }
            }
            //敌人与角色
            if (rectanglel.intersects(actor.getRect()))
                monster1.setOrientationl(true);
            if (rectangler.intersects(actor.getRect()))
                monster1.setOrientationr(true);
            if (rectangleu.intersects(actor.getRect()))
                monster1.setOrientationu(true);
            if (rectangled.intersects(actor.getRect()))
                monster1.setOrientationd(true);
            //敌人与商店
            for (ItemDrop drop : dropP) {
                if (rectanglel.intersects(drop.getRect()))
                    monster1.setOrientationl(true);
                if (rectangler.intersects(drop.getRect()))
                    monster1.setOrientationr(true);
                if (rectangleu.intersects(drop.getRect()))
                    monster1.setOrientationu(true);
                if (rectangled.intersects(drop.getRect()))
                    monster1.setOrientationd(true);
            }
        }
        //角色状态重置
        actor.setActorL(false);
        actor.setActorR(false);
        actor.setActorU(false);
        actor.setActorD(false);
        for (Square square : wallList) {
            //角色四个方向的提前预判（左 右 上 下 矩形块）
            Rectangle rectanglels = new Rectangle(actor.getRect().x - 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectanglers = new Rectangle(actor.getRect().x + 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectangleus = new Rectangle(actor.getRect().x, actor.getRect().y - 6, actor.getHeight(), actor.getWidth());
            Rectangle rectangleds = new Rectangle(actor.getRect().x, actor.getRect().y + 6, actor.getHeight(), actor.getWidth());
            //角色与墙体
            if (rectanglels.intersects(square.getRect()))
                actor.setActorL(true);
            if (rectanglers.intersects(square.getRect()))
                actor.setActorR(true);
            if (rectangleus.intersects(square.getRect()))
                actor.setActorU(true);
            if (rectangleds.intersects(square.getRect()))
                actor.setActorD(true);
        }
        //角色与敌人
        for (Monster monster1 : monsters) {
            Rectangle rectanglels = new Rectangle(actor.getRect().x - 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectanglers = new Rectangle(actor.getRect().x + 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectangleus = new Rectangle(actor.getRect().x, actor.getRect().y - 5, actor.getHeight(), actor.getWidth());
            Rectangle rectangleds = new Rectangle(actor.getRect().x, actor.getRect().y + 5, actor.getHeight(), actor.getWidth());
            if (rectanglels.intersects(monster1.getRect()))
                actor.setActorL(true);
            if (rectanglers.intersects(monster1.getRect()))
                actor.setActorR(true);
            if (rectangleus.intersects(monster1.getRect()))
                actor.setActorU(true);
            if (rectangleds.intersects(monster1.getRect()))
                actor.setActorD(true);
        }
        //角色与禁止
        for (ItemDrop drop : dropO) {
            Rectangle rectanglels = new Rectangle(actor.getRect().x - 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectanglers = new Rectangle(actor.getRect().x + 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectangleus = new Rectangle(actor.getRect().x, actor.getRect().y - 5, actor.getHeight(), actor.getWidth());
            Rectangle rectangleds = new Rectangle(actor.getRect().x, actor.getRect().y + 5, actor.getHeight(), actor.getWidth());
            if (rectanglels.intersects(drop.getRect()))
                actor.setActorL(true);
            if (rectanglers.intersects(drop.getRect()))
                actor.setActorR(true);
            if (rectangleus.intersects(drop.getRect()))
                actor.setActorU(true);
            if (rectangleds.intersects(drop.getRect()))
                actor.setActorD(true);
        }
        //角色与商城
        for (ItemDrop drop : dropP) {
            Rectangle rectanglels = new Rectangle(actor.getRect().x - 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectanglers = new Rectangle(actor.getRect().x + 5, actor.getRect().y, actor.getWidth(), actor.getHeight());
            Rectangle rectangleus = new Rectangle(actor.getRect().x, actor.getRect().y - 5, actor.getHeight(), actor.getWidth());
            Rectangle rectangleds = new Rectangle(actor.getRect().x, actor.getRect().y + 5, actor.getHeight(), actor.getWidth());
            if (rectanglels.intersects(drop.getRect()))
                actor.setActorL(true);
            if (rectanglers.intersects(drop.getRect()))
                actor.setActorR(true);
            if (rectangleus.intersects(drop.getRect()))
                actor.setActorU(true);
            if (rectangleds.intersects(drop.getRect()))
                actor.setActorD(true);
        }
        //遍历钥匙 对对禁止进行删除
        //禁止
        for (int k = 0; k < dropO.size(); k++) {
            for (int j = 0; j < dropN.size(); j++) {
                if (dropO.get(k).uid == dropN.get(j).uid) {
                    dropO.remove(k);
                    return;
                }

            }
        }
        //角色与道具
        for (int i = 0; i < Allconsumables.size(); i++) {
            if (Allconsumables.get(i).getRect().intersects(actor.getRect())) {

                if (Allconsumables.get(i).buy) {
                    if (dropI.size() >= Allconsumables.get(i).price / 10) {
                        Allconsumablesl.add(Allconsumables.get(i));
                        if (Allconsumables.get(i).id == 4) {
                            dropD.add(Allconsumables.get(i));
                        } else if (Allconsumables.get(i).id == 5) {
                            dropE.add(Allconsumables.get(i));
                        } else if (Allconsumables.get(i).id == 6) {
                            dropF.add(Allconsumables.get(i));
                            Config.HPmax += 5;
                            Config.HP += 5;
                        } else if (Allconsumables.get(i).id == 7) {
                            Config.MPmax += 5;
                            Config.MP += 5;
                            dropG.add(Allconsumables.get(i));
                        } else if (Allconsumables.get(i).id == 8) {
                            dropH.add(Allconsumables.get(i));
                        } else if (Allconsumables.get(i).id == 9) {
                            dropI.add(Allconsumables.get(i));
                        } else if (Allconsumables.get(i).id == 10 || Allconsumables.get(i).id == 11) {
                            if (dropJ.size() != 0) {
                                for (int q = 0; q < dropJ.size(); q++) {
                                    if (dropJ.get(q).hp > Allconsumables.get(i).hp) {
                                        return;
                                    } else {
                                        dropJ.remove(q);
                                        dropJ.add(Allconsumables.get(i));
                                        break;
                                    }
                                }
                            } else {
                                dropJ.add(Allconsumables.get(i));
                                Config.HPmax += 15;
                                Config.HP += 10;
                            }

                        }
                        //盔甲类碰撞检测
                        else if (Allconsumables.get(i).id == 12 || Allconsumables.get(i).id == 15) {
                            if (dropM.size() != 0) {
                                for (int q = 0; q < dropM.size(); q++) {
                                    if (dropM.get(q).hp > Allconsumables.get(i).hp) {
                                        return;
                                    } else {
                                        dropM.remove(q);
                                        dropM.add(Allconsumables.get(i));
                                        break;
                                    }
                                }
                            } else {
                                dropM.add(Allconsumables.get(i));
                                Config.HPmax += 20;
                                Config.HP += 10;
                            }
                        }
                        //武器类数值检测
                        else if (Allconsumables.get(i).id == 16 || Allconsumables.get(i).id == 17) {
                            if (dropZ.size() != 0) {
                                for (int q = 0; q < dropZ.size(); q++) {
                                    if (dropZ.get(q).atk > Allconsumables.get(i).atk) {
                                        return;
                                    } else {
                                        dropZ.remove(q);
                                        dropZ.add(Allconsumables.get(i));
                                        break;
                                    }
                                }
                            } else {
                                dropZ.add(Allconsumables.get(i));
                                actor.setAtk(actor.getAtk() + 10);
                            }
                        }

                        Allconsumables.remove(i);
                    } else {
                        return;
                    }
                    int count = Allconsumables.get(i).price / 10;
                    for (int k = 0; k < count; k++) {
                        dropI.remove(0);
                    }
                } else if (!Allconsumables.get(i).buy) {
                    Allconsumablesl.add(Allconsumables.get(i));
                    if (Allconsumables.get(i).id == 1) {
                        dropA.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 2) {
                        dropB.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 3) {
                        dropC.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 4) {
                        dropD.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 5) {
                        dropE.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 6) {
                        Config.HPmax += 5;
                        Config.HP += 5;
                        dropF.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 7) {
                        Config.MPmax += 5;
                        Config.MP += 5;
                        dropG.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 8) {
                        dropH.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 9) {
                        dropI.add(Allconsumables.get(i));
                    } else if (Allconsumables.get(i).id == 13) {
                        dropN.add(Allconsumables.get(i));
                        Initialize();
                    }
                    Allconsumables.remove(i);
                }
            }
        }
    }

    /**
     * 怪物消失
     */
    public void remove() {
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getLife() < 1) {
                int X = monsters.get(i).getX();
                int Y = monsters.get(i).getY();
                //怪物随机掉落
                switch (random.nextInt(14)) {
                    case 1:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 1, 0, grape, rectangle));
                        break;
                    case 2:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 2, 0, apple, rectangle));
                        break;
                    case 3:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 3, 0, bread, rectangle));
                        break;

                    case 4:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 4, 0, HP, rectangle));
                        break;
                    case 5:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 5, 0, MP, rectangle));
                        break;
                    case 6:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 6, 0, Redbook, rectangle));
                        break;
                    case 7:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 7, 0, Bluebook, rectangle));
                        break;
                    case 8:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 8, 0, beamer, rectangle));
                        break;
                    case 9:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 9, 0, Coin, rectangle));
                        break;
                    case 10:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 9, 0, Coin, rectangle));
                        break;
                    case 11:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 9, 0, Coin, rectangle));
                        break;
                    case 12:
                        Allconsumables.add(new ItemDrop(X, Y, 32, 32, 15, 15, 9, 0, Coin, rectangle));
                        break;
                    case 13:
                        break;
                }
                monsters.remove(i);
                switch (dropN.size()) {
                    case 0:
                        if (monsters.size() < 1) {
                            Allconsumables.add(new ItemDrop(1300 + Config.ScollX, 310 + Config.ScollY, 32, 32, 15, 15, 13, 1, 1, keyImg, rectangle));
                        }
                        break;
                    case 1:
                        if (monsters.size() < 1) {
                            Allconsumables.add(new ItemDrop(242 + Config.ScollX, -140 + Config.ScollY, 32, 32, 15, 15, 13, 2, 2, keyImg, rectangle));
                        }
                        break;
                    case 2:
                        if (monsters.size() < 1) {
                            Allconsumables.add(new ItemDrop(502 + Config.ScollX, -845 + Config.ScollY, 32, 32, 15, 15, 13, 3, 3, keyImg, rectangle));
                            Allconsumables.add(new ItemDrop(1100 + Config.ScollX, -820 + Config.ScollY, 32, 32, 15, 15, 13, 3, 3, keyImg, rectangle));
                        }
                        break;
                    case 4:
                        if (monsters.size() < 1) {
                            System.out.println("5钥匙出现");
                            Allconsumables.add(new ItemDrop(1300 + Config.ScollX, -620 + Config.ScollY, 32, 32, 15, 15, 13, 3, 3, keyImg, rectangle));

                        }
                        break;
                    case 5:
                        GameFrame.iswin = true;

                }

            }
        }
    }


    public Actor getActor() {
        return actor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
