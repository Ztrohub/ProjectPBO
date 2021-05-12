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
    public static BufferedImage[] mummyEnemy = new BufferedImage[13];


    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG, popUp;
    public static BufferedImage playIcon, shopIcon, settingIcon, leaderBoardIcon, logout;

    public static BufferedImage card, cardBG;

    public static Clip audioLogin, audioIntro, audioMenu, audioGame;

    public static Font smallFont;
    public static Font regulerFont;
    public static Font dungeonFont;
    public static Font warningFont;
    public static Font smallerFont;
    public static Font biggerFont;


    public static void init(){

//        AUDIO
        audioLogin = AudioLoader.loadAudio("res/audio/nando/login1.wav");
        audioIntro = AudioLoader.loadAudio("res/audio/intro.wav");
        audioMenu = AudioLoader.loadAudio("res/audio/nando/menu.wav");
        audioGame = AudioLoader.loadAudio("res/audio/game.wav");

//        FONT
        smallerFont = FontLoader.loadFont("res/font/mainfont.ttf", 14);
        smallFont = FontLoader.loadFont("res/font/mainfont.ttf", 15);
        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);
        biggerFont = FontLoader.loadFont("res/font/mainfont.ttf", 24);
        warningFont = FontLoader.loadFont("res/font/mainfont.ttf", 17);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        initBufferArr(menuBG, "menuBG");
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
        popUp = ImageLoader.loadImage("/images/menuAsset/popUp.png");

        mainLogo = ImageLoader.loadImage("/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        avatar = ImageLoader.loadImage("/avatar.png");

//        ICON
        initBufferArr(coinsIcon, "coinsIcon");
        playIcon = ImageLoader.loadImage("/images/menuAsset/swordIcon.png");
        shopIcon = ImageLoader.loadImage("/images/menuAsset/shopIcon.png");
        settingIcon = ImageLoader.loadImage("/images/menuAsset/settingIcon.png");
        leaderBoardIcon = ImageLoader.loadImage("/images/menuAsset/leaderBoardIcon.png");
        logout = ImageLoader.loadImage("/images/menuAsset/logoutIcon.png");

//        ENEMY
        potongSprite(mummyEnemy, "mummy");

//        GAME
        card = ImageLoader.loadImage("/images/content/test.png");
        cardBG = ImageLoader.loadImage("/images/content/card/cardBG.png");

    }

    public static void initBufferArr(BufferedImage[] temp, String nameBuffer){
        for (int i = 0; i < temp.length; i++) {
            if(nameBuffer.equalsIgnoreCase("menuBG")){
                menuBG[i] = ImageLoader.loadImage("/images/sprite/bgMenu/" + i + ".png");
            } else if(nameBuffer.equalsIgnoreCase("coinsIcon")){
                coinsIcon[i] = ImageLoader.loadImage("/images/sprite/Coins/" + i + ".png");
            }
        }
    }

    public static void potongSprite(BufferedImage[] temp, String nameBuffer){
        for (int i = 0; i < temp.length; i++) {
            if(nameBuffer.equalsIgnoreCase("mummy")){
                mummyEnemy[i] = ImageLoader.loadImage("/images/sprite/Enemy/mummy.png").getSubimage(292*i, 0, 292, 384);
            }
        }
    }
}
