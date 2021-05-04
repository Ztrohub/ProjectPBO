package project.pbo.gfx;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class Assets {

    /*
        Semua File diupload di sini!
        Panggil dengan Asset.nama

        Load gambar ==> ImageLoader.loadImage("/alamat/namafile.extensi");
     */

    static URL url;

    static {
        try {
            url = new URL("https://s3.gifyu.com/images/menuGIF.gif");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static Image menuBG;
    public static BufferedImage mainLogo, logoSTTS, avatar;
    public static BufferedImage loginBG, loadingBG;
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
        menuBG = new ImageIcon(url).getImage();
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");
        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");

        mainLogo = ImageLoader.loadImage("/mainLogo.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        avatar = ImageLoader.loadImage("/avatar.png");

    }
}
