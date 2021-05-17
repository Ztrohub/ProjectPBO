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
    public static BufferedImage[] player = new BufferedImage[15];

    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG, rankingBG, popUp, textArea;
    public static BufferedImage playIcon, storeIcon, settingsIcon, rankingIcon, logout, xIcon, medalIcon;

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
        potongSprite(menuBG, "/images/sprite/mainMenuBG.png", 1076, 540);
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
        rankingBG = ImageLoader.loadImage("/images/rankingBG.png");

        // RANDOM IMAGE
        mainLogo = ImageLoader.loadImage("/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        avatar = ImageLoader.loadImage("/avatar.png");
        popUp = ImageLoader.loadImage("/images/asset/mainMenu/popUp.png");
        textArea = ImageLoader.loadImage("/images/asset/ranking/textArea.png");

        // ITEM & HERO MENU IMAGE
        heroMenu = ImageLoader.loadImage("/images/content/card/heroMenu1.png");
        gameBG = ImageLoader.loadImage("/images/content/card/gameBG.png");

        coin = ImageLoader.loadImage("/images/asset/item/coin.png");
        sword = ImageLoader.loadImage("/images/asset/item/sword.png");
        shield = ImageLoader.loadImage("/images/asset/item/shield.png");
        heal = ImageLoader.loadImage("/images/asset/item/heal.png");
        poison = ImageLoader.loadImage("/images/asset/item/poison.png");

//        ICON
        potongSprite(coinsIcon, "/images/sprite/Coins/", -1, -1);
        playIcon = ImageLoader.loadImage("/images/asset/mainMenu/pedang.png");
        storeIcon = ImageLoader.loadImage("/images/asset/mainMenu/store.png");
        settingsIcon = ImageLoader.loadImage("/images/asset/mainMenu/settings.png");
        rankingIcon = ImageLoader.loadImage("/images/asset/mainMenu/ranking.png");
        logout = ImageLoader.loadImage("/images/asset/mainMenu/logout.png");
        xIcon = ImageLoader.loadImage("/images/asset/ranking/xIcon.png");
        medalIcon = ImageLoader.loadImage("/images/asset/ranking/medal.png");

//        ENEMY
        potongSprite(mummyEnemy, "/images/sprite/Enemy/mummy.png", 292, 384);
        potongSprite(slimeEnemy, "/images/sprite/Enemy/slime.png", 236, 132);
        potongSprite(necroEnemy, "/images/sprite/Enemy/necromancer.png", 159, 196);
        potongSprite(shamanEnemy, "/images/sprite/Enemy/shaman.png", 160, 237);
        potongSprite(player, "/images/sprite/player.png", 640, 640);

//        GAME
        card = ImageLoader.loadImage("/images/content/test.png");
        cardBG = ImageLoader.loadImage("/images/content/card/cardBG.png");
    }

    public static void potongSprite(BufferedImage[] temp, String path, int width, int height){
        for (int i = 0; i < temp.length; i++) {
            if(width != -1)
                temp[i] = ImageLoader.loadImage(path).getSubimage(width*i, 0, width, height);
            else
                temp[i] = ImageLoader.loadImage(path + i + ".png"); // GAMBAR COIN
        }
    }
}
