package com.fan.games;

import com.fan.games.Tool.GameUtil;
import com.fan.games.keyListener.ActorListener;
import com.fan.games.keyListener.FrameListener;

import javax.swing.*;
import java.util.Date;
import java.awt.*;

//游戏窗体
public class GameFrame extends JFrame {
    // 双缓冲
    private Image offScreenImage = null;
    //游戏是否进行   角色死亡时
    public static boolean isFail = true;
    public static boolean iswin = false;
    // 动画帧数
    static int frames = 0;
    // 六帧动画
    static int frame = 1;
    // 六帧   五次一循环
    static int frame_five = 1;
    // 六帧   六次一循环
    static int frame_six = 1;
    // 六帧   四次一循环
    static int frame_four = 1;
    // 受击计时帧
    static int frameatk = 1;
    // 八帧动画
    static int framee = 2;
    //结束图片
    private Image over = GameUtil.getImage("Statics/over.png");
    //不跟随窗体卷动 UI  注意层级顺序

    private final Config config = new Config();
    GameMap OurObj = new GameMap(config, this);
    // 对象
    GameFrame gameFrame;  // 用于传参

    //无参构造
    public GameFrame() {
        contentFrame();
    }

    //初始化窗体
    public void contentFrame() {
        setTitle("GAME-" + Config.AUTHOR);
        setSize(970, 640);//初始大小
        //使屏幕居中
        setLocationRelativeTo(null);
        //关闭停止程序运行
        setDefaultCloseOperation(3);
        //使用时不能调整大小
        setResizable(false);
        //取消布局管理器
        setLayout(null);
        setVisible(true);
        //setUndecorated(true);
        //在窗口中添加默认监听A
        this.addKeyListener(new FrameListener(gameFrame, OurObj.actor, OurObj, OurObj.GameBg2));
        this.addKeyListener(new ActorListener(OurObj.actor, OurObj.monster));
        new GameFrame.PaintThead().start();
    }

    //paint 方法
    public void paint(Graphics g) {
        //新画布
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1000, 1000);
        }
        if (config.HP <= 0) {
            isFail = false;
        } else {
            isFail = true;
        }
        //画笔（获得）
        Graphics gImage = offScreenImage.getGraphics();
        //绘制
        if (isFail) {
            OurObj.update(gImage);
        } else if (!isFail) {
            gImage.drawImage(over, 0, 0, null);
        }
        //复刻
        g.drawImage(offScreenImage, -20, 0, null);
        //动画帧数
        frame();
        framee();
    }

    //动画帧数（6帧数）
    public void frame() {
        if (frames % 6 == 0) {
            frame++;
            frame_six++;
            frame_five++;
            if (frameatk < 4) {
                frameatk++;
            }
        }
        if (frame > 3) {
            frame = 1;
        }
        if (frame_five > 5) {
            frame_five = 1;
        }
        if (frame_six > 6) {
            frame_six = 1;
        }
    }

    //动画帧数（10帧数）
    public void framee() {
        if (frames % 10 == 0) {
            framee++;
            frame_four++;
        }
        if (framee > 3) {
            framee = 1;
        }
        if (frame_four > 4) {
            frame_four = 1;
        }
    }

    //窗口重载 --角色死亡
    public void isFail() {
        if (config.HP < 0) {
            isFail = false;
            config.HP = 100;
        }
    }
    //销毁方法
    public void isdos() {
        GameMap.wallList.clear();
        GameMap.Allconsumables.clear();
        GameMap.Allconsumablesl.clear();
        GameMap.dropA.clear();
        GameMap.dropB.clear();
        GameMap.dropC.clear();
        GameMap.dropD.clear();
        GameMap.dropE.clear();
        GameMap.dropF.clear();
        GameMap.dropG.clear();
        GameMap.dropH.clear();
        GameMap.dropP.clear();
        GameMap.dropN.clear();
        GameMap.dropO.clear();
        GameMap.dropI.clear();
        GameMap.monsters.clear();
        Config.HP = 100;
        Config.MP = 100;
        Config.HPmax = 100;
        Config.MPmax = 100;
        OurObj.Initialize();
        OurObj.initialized();
        this.dispose();
        new GameStart();
    }

    //窗口线程-
    class PaintThead extends Thread {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(15);  //1s=1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frames++;
            }
        }
    }
}

