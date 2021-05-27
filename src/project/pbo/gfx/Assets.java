package project.pbo.gfx;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Assets {

    /*
        Semua File diupload di sini!
        Panggil dengan Asset.nama

        Load gambar ==> ImageLoader.loadImage("/alamat/namafile.extensi");
     */
//

    public static BufferedImage[] menuBG = new BufferedImage[20], blurBG = new BufferedImage[10];
    public static BufferedImage[] coinsIcon = new BufferedImage[7];
    public static BufferedImage[] mummyEnemy = new BufferedImage[13];
    public static BufferedImage[] slimeEnemy = new BufferedImage[8];
    public static BufferedImage[] necroEnemy = new BufferedImage[8];
    public static BufferedImage[] shamanEnemy = new BufferedImage[11];
    public static BufferedImage[] player = new BufferedImage[21];
    public static BufferedImage shop;
    public static BufferedImage bybuttonlight;
    public static BufferedImage bybuttondrk;
    public static BufferedImage soundon;
    public static BufferedImage soundsilent;
    public static BufferedImage sliderbar;
    public static BufferedImage slidericon;

    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG, popUp, textArea;
    public static BufferedImage playIcon, storeIcon, settingsIcon, rankingIcon, logout, xIcon, medalIcon;

    public static BufferedImage card;
    public static BufferedImage heroMenu, gameBG;
    public static BufferedImage coin, sword, shield, heal, poison;

    public static Clip audioLogin, audioIntro, audioMenu, audioGame, audioClick;

    public static Font smallerFont, smallFont, regulerFont, mediumFont, biggerFont, superMegaBigFont;
    public static Font menuSmallFont, menuRegulerFont;
    public static Font dungeonFont;
    public static Font warningFont;

    public static void initMenu(){
//        BACKGROUND
        potongSprite(menuBG, "/images/BG/mainMenuBG.png", 1076, 540);
        potongSprite(blurBG, "/images/BG/blurMenuBG.png", 1076, 540);

        xIcon = ImageLoader.loadImage("/images/asset/ranking/xIcon.png");
        popUp = ImageLoader.loadImage("/images/asset/mainMenu/popUp.png");

//        MAIN MENU
        playIcon = ImageLoader.loadImage("/images/asset/mainMenu/pedang.png");
        storeIcon = ImageLoader.loadImage("/images/asset/mainMenu/store.png");
        settingsIcon = ImageLoader.loadImage("/images/asset/mainMenu/settings.png");
        rankingIcon = ImageLoader.loadImage("/images/asset/mainMenu/ranking.png");
        logout = ImageLoader.loadImage("/images/asset/mainMenu/logout.png");

//        RANKING
        medalIcon = ImageLoader.loadImage("/images/asset/ranking/medal.png");
        textArea = ImageLoader.loadImage("/images/asset/ranking/textArea.png");

//        SHOP
        bybuttondrk = ImageLoader.loadImage("/images/asset/shop/buybutton1.png");
        bybuttonlight = ImageLoader.loadImage("/images/asset/shop/buybutton2.png");
        shop =  ImageLoader.loadImage("/images/asset/shop/shop.png");
        sword = ImageLoader.loadImage("/images/asset/game/item/sword.png");
        shield = ImageLoader.loadImage("/images/asset/game/item/shield.png");
        heal = ImageLoader.loadImage("/images/asset/game/item/heal.png");

        potongSprite(coinsIcon, "/images/sprite/Coins/", -1, -1);

//        SETTING
        soundon = ImageLoader.loadImage("/images/asset/setting/sound.png");
        soundsilent = ImageLoader.loadImage("/images/asset/setting/silentsound.png");
        sliderbar = ImageLoader.loadImage("/images/asset/setting/soundbar.png");
        slidericon = ImageLoader.loadImage("/images/asset/setting/soundicon.png");
    }

    public static void initLogin(){
        loginBG = ImageLoader.loadImage("/images/BG/loginBG.jpg");
    }

    public static void initGame(){
        gameBG = ImageLoader.loadImage("/images/BG/gameBG.png");
        xIcon = ImageLoader.loadImage("/images/asset/ranking/xIcon.png");
        potongSprite(coinsIcon, "/images/sprite/Coins/", -1, -1);

//        GAME
        heroMenu = ImageLoader.loadImage("/images/asset/game/heroMenu.png");
        card = ImageLoader.loadImage("/images/asset/game/card.png");
        coin = ImageLoader.loadImage("/images/asset/game/item/coin.png");
        poison = ImageLoader.loadImage("/images/asset/game/item/poison.png");
        sword = ImageLoader.loadImage("/images/asset/game/item/sword.png");
        shield = ImageLoader.loadImage("/images/asset/game/item/shield.png");
        heal = ImageLoader.loadImage("/images/asset/game/item/heal.png");
        popUp = ImageLoader.loadImage("/images/asset/mainMenu/popUp.png");


//        SPRITE
        potongSprite(mummyEnemy, "/images/sprite/Enemy/mummy.png", 292, 384);
        potongSprite(slimeEnemy, "/images/sprite/Enemy/slime.png", 236, 132);
        potongSprite(necroEnemy, "/images/sprite/Enemy/necromancer.png", 159, 196);
        potongSprite(shamanEnemy, "/images/sprite/Enemy/shaman.png", 160, 237);
        potongSprite(player, "/images/sprite/player.png", 640, 640);
    }

    public static void nullThisYeah(){
        playIcon = null;
        storeIcon = null;
        settingsIcon = null;
        rankingIcon = null;
        logout = null;
        medalIcon = null;
        loginBG = null;
        gameBG = null;
        heroMenu = null;
        textArea = null;
        card = null;
        coin = null;
        poison = null;
        sword = null;
        shield = null;
        heal = null;
        bybuttondrk = null;
        bybuttonlight = null;
        shop = null;
        xIcon = null;
        popUp = null;

        arrayNuller(coinsIcon);
        arrayNuller(menuBG);
        arrayNuller(blurBG);
        arrayNuller(mummyEnemy);
        arrayNuller(slimeEnemy);
        arrayNuller(necroEnemy);
        arrayNuller(shamanEnemy);
        arrayNuller(player);
    }

    public static void arrayNuller(BufferedImage[] temp){
        Arrays.fill(temp, null);
    }

    public static void init(){

//        AUDIO
        audioLogin = AudioLoader.loadAudio("res/audio/login.wav");
        audioIntro = AudioLoader.loadAudio("res/audio/intro.wav");
        audioMenu = AudioLoader.loadAudio("res/audio/menu.wav");
        audioGame = AudioLoader.loadAudio("res/audio/game.wav");
        audioClick = AudioLoader.loadAudio("res/audio/click.wav");

//        FONT
        menuSmallFont = FontLoader.loadFont("res/font/mainfont.ttf", 14);
        menuRegulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 16);
        smallerFont = FontLoader.loadFont("res/font/mainfont.ttf", 14);
        smallFont = FontLoader.loadFont("res/font/mainfont.ttf", 15);
        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);
        mediumFont = FontLoader.loadFont("res/font/mainfont.ttf", 20);
        biggerFont = FontLoader.loadFont("res/font/mainfont.ttf", 24);
        superMegaBigFont = FontLoader.loadFont("res/font/mainfont.ttf", 50);
        warningFont = FontLoader.loadFont("res/font/mainfont.ttf", 17);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        // BACKGROUND IMAGE
        loadingBG = ImageLoader.loadImage("/images/BG/loadingBG.jpg");

        // UTILS IMAGE
        mainLogo = ImageLoader.loadImage("/images/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/images/istts.png");
        avatar = ImageLoader.loadImage("/images/avatar.png");

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
