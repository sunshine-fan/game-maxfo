package com.fan.games;

import com.fan.games.Tool.GameUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BulletsPool {

    public static final int POOL_SIZE = 200;
    //池塘最多容纳
    public static final int POOL_MAXSIZE = 200;
    // 用于保存所有子弹的容器
    private static List<Bullet> pool = new ArrayList<>();

    //    在类加载的时候创建200个子弹对象添加到容器中去
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(new Bullet());
        }
    }

    /**
     * 从池塘中获取一个子弹对象
     * @return
     */
    public static Bullet get() {

        Bullet bullet = null;
        // 池塘被掏空
        if (pool.size() == 0) { //池塘中没对象 创建对象
            bullet = new Bullet();
        } else {//池塘中还有对象,  拿走第一个位置的对象
            bullet = pool.remove(0);
        }
        return bullet;
    }

    /**
     * 归还子弹
     */
    // 子弹被销毁的过程中 归还到池塘中来
    public static void theReturn(Bullet bullet) {
        // 子弹池 到达最大值就不再归还
        if (pool.size() == POOL_MAXSIZE) {
            return;
        }
        pool.add(bullet);
    }
}
