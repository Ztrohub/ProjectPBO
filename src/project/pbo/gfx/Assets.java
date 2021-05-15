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
    public static BufferedImage[] slimeEnemy = new BufferedImage[8];
    public static BufferedImage[] necroEnemy = new BufferedImage[8];
    public static BufferedImage[] shamanEnemy = new BufferedImage[11];

    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG, rankingBG, popUp, textArea;
    public static BufferedImage playIcon, storeIcon, settingsIcon, rankingIcon, logout, xIcon;

    public static BufferedImage card, cardBG;
    public static BufferedImage heroMenu, gameBG;
    public static BufferedImage coin, sword, shield, heal, poison;

    public static Clip audioLogin, audioIntro, audioMenu, audioGame;

    public static Font smallerFont, smallFont, regulerFont, mediumFont, biggerFont, superMegaBigFont;
    public static Font menuSmallFont, menuRegulerFont;
    public static Font dungeonFont;
    public static Font warningFont;


    public static void init(){

//        AUDIO
        audioLogin = AudioLoader.loadAudio("res/audio/nando/login1.wav");
        audioIntro = AudioLoader.loadAudio("res/audio/intro.wav");
        audioMenu = AudioLoader.loadAudio("res/audio/nando/menu.wav");
        audioGame = AudioLoader.loadAudio("res/audio/game.wav");

//        FONT
        menuSmallFont = FontLoader.loadFont("res/font/mainfont.ttf", 14);
        menuRegulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 16);

        smallerFont = FontLoader.loadFont("res/font/mainfont.ttf", 14);
        smallFont = FontLoader.loadFont("res/font/mainfont.ttf", 15);
        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);
        mediumFont = FontLoader.loadFont("res/font/mainfont.ttf", 20);
        biggerFont = FontLoader.loadFont("res/font/mainfont.ttf", 24);
        superMegaBigFont = FontLoader.loadFont("res/font/mainfont.ttf", 55);
        warningFont = FontLoader.loadFont("res/font/mainfont.ttf", 17);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        // BACKGROUND IMAGE
        initBufferArr(menuBG, "menuBG");
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
        rankingBG = ImageLoader.loadImage("/images/background.png");

        // RANDOM IMAGE
        mainLogo = ImageLoader.loadImage("/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        avatar = ImageLoader.loadImage("/avatar.png");
        popUp = ImageLoader.loadImage("/images/asset/mainMenu/popUp.png");
        textArea = ImageLoader.loadImage("/images/asset/ranking/textArea.png");
        xIcon = ImageLoader.loadImage("/images/asset/ranking/xIcon.png");

        // ITEM & HERO MENU IMAGE
        heroMenu = ImageLoader.loadImage("/images/content/card/heroMenu1.png");
        gameBG = ImageLoader.loadImage("/images/content/card/gameBG.png");

        coin = ImageLoader.loadImage("/images/asset/item/coin.png");
        sword = ImageLoader.loadImage("/images/asset/item/sword.png");
        shield = ImageLoader.loadImage("/images/asset/item/shield.png");
        heal = ImageLoader.loadImage("/images/asset/item/heal.png");
        poison = ImageLoader.loadImage("/images/asset/item/poison.png");

//        ICON
        initBufferArr(coinsIcon, "coinsIcon");
        playIcon = ImageLoader.loadImage("/images/asset/mainMenu/play.png");
        storeIcon = ImageLoader.loadImage("/images/asset/mainMenu/store.png");
        settingsIcon = ImageLoader.loadImage("/images/asset/mainMenu/settings.png");
        rankingIcon = ImageLoader.loadImage("/images/asset/mainMenu/ranking.png");
        logout = ImageLoader.loadImage("/images/asset/mainMenu/logout.png");

//        ENEMY
        potongSprite(mummyEnemy, "mummy");
        potongSprite(slimeEnemy, "slime");
        potongSprite(necroEnemy, "necromancer");
        potongSprite(shamanEnemy, "shaman");

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
            else if(nameBuffer.equalsIgnoreCase("slime")){
                slimeEnemy[i] = ImageLoader.loadImage("/images/sprite/Enemy/slime.png").getSubimage(236*i, 0, 236, 132);
            } else if(nameBuffer.equalsIgnoreCase("necromancer")){
                necroEnemy[i] = ImageLoader.loadImage("/images/sprite/Enemy/necromancer.png").getSubimage(159*i, 0, 159, 196);
            } else if(nameBuffer.equalsIgnoreCase("shaman")){
                shamanEnemy[i] = ImageLoader.loadImage("/images/sprite/Enemy/shaman.png").getSubimage(160*i, 0, 160, 237);
            }
        }
    }
}
