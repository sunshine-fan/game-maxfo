package com.fan.games.Tool;

        import javax.imageio.ImageIO;
        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.net.URL;

public class GameUtil {
    // 工具类最好将构造器私有化。
    private GameUtil() {
    }
    //  指定 路径图片的相关对象
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}