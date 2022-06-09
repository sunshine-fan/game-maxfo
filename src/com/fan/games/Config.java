package com.fan.games;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 配置类
 */
public class Config {
    /**
     * 作者信息
     */
    public static final String AUTHOR = "zhanglifan";

    /**
     * 游戏菜单
     */
    public static final int STATE_MENU = 0;
    public static final int STATE_HELP = 1;
    //地图快宽高
    private static final int MAP_X = 32;
    private static final int MAP_Y = 32;
    // 动画帧数
    public static int frames = 0;
    public static int frame = 1;
    //测试状态
    public static boolean istext = false;
    //HP上限
    public static double HPmax = 150;
    //MP上限
    public static double MPmax = 150;
    //初始化HP
    public static double HP = 100;
    //初始化MP
    public static double MP = 88;
    //数量consumables（消耗品）
    //初始化消耗品数量A B C
    public static double consumablesA = 3;
    //B道具
    //卷动总x值
    public static int ScollX = 0;
    public static int ScollY = 0;
    //关于人物四个方向的碰撞检测
    static boolean collisionXL;
    static boolean collisionXR;
    static boolean collisionYL;
    static boolean collisionYR;
    public static final String[] MENUS = {
//        "游戏指南",
//        "关于游戏",
//        "退出游戏",
    };

    public Config() {
    }
}
