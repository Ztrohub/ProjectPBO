package project.pbo.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage menuBG, logoSTTS;

    public static void init(){
        menuBG = ImageLoader.loadImage("/images/background.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
    }
}
