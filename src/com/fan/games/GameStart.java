package com.fan.games;
// todo 需要完成   重绘加上线程？

import com.fan.games.Tool.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// TODO  创建桌面窗口  继承  JFame
public class GameStart extends JFrame implements ActionListener {

    JButton Start , End;
    // 添加双缓冲图片
    private Image SecondCanvas = null;
    private Image keyImg = GameUtil.getImage("Statics/consumables/I_Key01.png");
    private Image start = GameUtil.getImage("Statics/start.png");
    private Image BK = GameUtil.getImage("Statics/over/bk.png");
    /**
     * 构造方法
     */
    public GameStart(){
        menuFrame();
    }
    /**
     * 窗体绘制
     */
    public void menuFrame(){
//        startJfame = new JFrame();
        this.setTitle("GAME-"+ Config.AUTHOR);
        this.setSize(640, 488);//初始大小
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        //使屏幕居中
        this.setLocationRelativeTo(null);
//        startJfame.setResizable(true);
        //清除默认布局
        this.setLayout(null);
        //logo图标
        this.setIconImage(keyImg);
        //加入按钮
        setButton();
        this.setVisible(true);
//        repaint();
//        new GameStart.PaintThead().start();
    }
    public void setButton(){
        Start = new JButton("start");
        End = new JButton("exit");
        this.add(Start);
        this.add(End);
        Start.setBounds(255, 300, 130, 30);
        End.setBounds(255, 350, 130, 30);
         // 消除背景
        Start.setContentAreaFilled(false);
        End.setContentAreaFilled(false);
        // 消除文字周围的虚线框
        Start.setFocusPainted(false);
        End.setFocusPainted(false);
        //鼠标监听
        Start.addActionListener(this);
        End.addActionListener(this);
    }
    public void paint(Graphics g) {
        g.drawImage(BK,0,0,null);
        g.drawImage(start,0,0,null);
        Start.requestFocus();
        End.requestFocus();

    }
//    //窗口线程-
//    class PaintThead extends Thread {
//        public void run() {
//            while (true) {
//                try {
//                    Thread.sleep(15);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    // 动作监听
    @Override
    public void actionPerformed(ActionEvent e) {
        // getSource获得按钮，事件源转为监听的对象
        if (e.getSource() == Start) {
            // 释放窗口资源
            this.dispose();
            // 新建游戏窗体
            new GameFrame();

        } else if (e.getSource() == End) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new GameStart();
    }

}

