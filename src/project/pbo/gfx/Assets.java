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

    public static BufferedImage card;
    public static BufferedImage heroMenu, gameBG;
    public static BufferedImage coin, sword, shield, heal, poison;

    public static Clip audioLogin, audioIntro, audioMenu, audioGame, audioClick;

    public static Font smallerFont, smallFont, regulerFont, mediumFont, biggerFont, superMegaBigFont;
    public static Font menuSmallFont, menuRegulerFont;
    public static Font dungeonFont;
    public static Font warningFont;

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
        superMegaBigFont = FontLoader.loadFont("res/font/mainfont.ttf", 55);
        warningFont = FontLoader.loadFont("res/font/mainfont.ttf", 17);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        // BACKGROUND IMAGE
        potongSprite(menuBG, "/images/BG/mainMenuBG.png", 1076, 540);
        loadingBG = ImageLoader.loadImage("/images/BG/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/BG/loginBG.jpg");
        rankingBG = ImageLoader.loadImage("/images/BG/rankingBG.png");
        gameBG = ImageLoader.loadImage("/images/BG/gameBG.png");

        // UTILS IMAGE
        mainLogo = ImageLoader.loadImage("/images/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/images/istts.png");
        avatar = ImageLoader.loadImage("/images/avatar.png");
        potongSprite(coinsIcon, "/images/sprite/Coins/", -1, -1);

//        MAIN MENU IMAGE
        popUp = ImageLoader.loadImage("/images/asset/mainMenu/popUp.png");
        playIcon = ImageLoader.loadImage("/images/asset/mainMenu/pedang.png");
        storeIcon = ImageLoader.loadImage("/images/asset/mainMenu/store.png");
        settingsIcon = ImageLoader.loadImage("/images/asset/mainMenu/settings.png");
        rankingIcon = ImageLoader.loadImage("/images/asset/mainMenu/ranking.png");
        logout = ImageLoader.loadImage("/images/asset/mainMenu/logout.png");

//        Ranking
        textArea = ImageLoader.loadImage("/images/asset/ranking/textArea.png");
        xIcon = ImageLoader.loadImage("/images/asset/ranking/xIcon.png");
        medalIcon = ImageLoader.loadImage("/images/asset/ranking/medal.png");

//        GAME
        heroMenu = ImageLoader.loadImage("/images/asset/game/heroMenu.png");
        card = ImageLoader.loadImage("/images/asset/game/card.png");
        coin = ImageLoader.loadImage("/images/asset/game/item/coin.png");
        sword = ImageLoader.loadImage("/images/asset/game/item/sword.png");
        shield = ImageLoader.loadImage("/images/asset/game/item/shield.png");
        heal = ImageLoader.loadImage("/images/asset/game/item/heal.png");
        poison = ImageLoader.loadImage("/images/asset/game/item/poison.png");


//        SPRITE
        potongSprite(mummyEnemy, "/images/sprite/Enemy/mummy.png", 292, 384);
        potongSprite(slimeEnemy, "/images/sprite/Enemy/slime.png", 236, 132);
        potongSprite(necroEnemy, "/images/sprite/Enemy/necromancer.png", 159, 196);
        potongSprite(shamanEnemy, "/images/sprite/Enemy/shaman.png", 160, 237);
        potongSprite(player, "/images/sprite/player.png", 640, 640);

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
