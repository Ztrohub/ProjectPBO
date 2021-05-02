package project.pbo.gfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    /*
        Semua File diupload di sini!
        Panggil dengan Asset.nama

        Load gambar ==> ImageLoader.loadImage("/alamat/namafile.extensi");
     */

    public static BufferedImage menuBG, logoSTTS;
    public static BufferedImage loginBG, loadingBG;
    public static Clip audioLogin, audioIntro, audioMenu;

    public static Font regulerFont;
    public static Font dungeonFont;

    public static void init(){

//        AUDIO
        audioLogin = AudioLoader.loadAudio("res/audio/login.wav");
        audioIntro = AudioLoader.loadAudio("res/audio/IntroNando.wav");
        audioMenu = AudioLoader.loadAudio("res/audio/menu.wav");

//        FONT
        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);
        dungeonFont = FontLoader.loadFont("res/font/dungeon.ttf", 35);

//        IMAGE
        menuBG = ImageLoader.loadImage("/images/background.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");
        loadingBG = ImageLoader.loadImage("/images/loadingBG.jpg");

        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
    }
}
