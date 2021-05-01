package project.pbo.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage menuBG, logoSTTS;
    public static BufferedImage loginBG;

    public static Font regulerFont;

    public static void init(){

        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);

        menuBG = ImageLoader.loadImage("/images/background.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");

        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
    }
}
