package project.pbo.gfx;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    /*
        Semua File diupload di sini!
        Panggil dengan Asset.nama

        Load gambar ==> ImageLoader.loadImage("/alamat/namafile.extensi");
     */
//

    public static BufferedImage[] menuBG = new BufferedImage[20], coinsIcon = new BufferedImage[7];
    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG;
    public static BufferedImage playIcon, shopIcon, settingIcon, creditIcon, logout;
    public static Clip audioLogin, audioIntro, audioMenu, audioGame;

    public static Font regulerFont;
    public static Font dungeonFont;

    public static void init(){

//        AUDIO
        audioLogin = AudioLoader.loadAudio("res/audio/login.wav");
        audioIntro = AudioLoader.loadAudio("res/audio/intro.wav");
        audioMenu = AudioLoader.loadAudio("res/audio/menu.wav");
        audioGame = AudioLoader.loadAudio("res/audio/game.wav");

//        FONT
        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        initBufferArr(menuBG, "menuBG");
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");

        mainLogo = ImageLoader.loadImage("/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        avatar = ImageLoader.loadImage("/avatar.png");


//        ICON
        initBufferArr(coinsIcon, "coinsIcon");
        playIcon = ImageLoader.loadImage("/images/menuAsset/swordIcon.png");
        shopIcon = ImageLoader.loadImage("/images/menuAsset/shopIcon.png");
        settingIcon = ImageLoader.loadImage("/images/menuAsset/settingIcon.png");
        creditIcon = ImageLoader.loadImage("/images/menuAsset/creditIcon.png");
        logout = ImageLoader.loadImage("/images/menuAsset/logoutIcon.png");
    }

    public static void initBufferArr(BufferedImage[] temp, String nameBuffer){
        for (int i = 0; i < temp.length; i++) {
            if(nameBuffer.equalsIgnoreCase("menuBG")){
                menuBG[i] = ImageLoader.loadImage("/images/menuAsset/bgMenu/" + i + ".png");
            } else if(nameBuffer.equalsIgnoreCase("coinsIcon")){
                coinsIcon[i] = ImageLoader.loadImage("/images/sprite/Coins/" + i + ".png");
            }
        }
    }
}
