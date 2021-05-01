package project.pbo.gfx;

import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    /*
        Semua File diupload di sini!
        Panggil dengan Asset.nama

        Load gambar ==> ImageLoader.loadImage("/alamat/namafile.extensi");
     */

    public static BufferedImage menuBG, logoSTTS;
    public static BufferedImage loginBG;
    public static AudioInputStream audioLogin;

    public static Font regulerFont;

    public static void init(){

        audioLogin = AudioLoader.loadAudio("res/audio/login.wav");

        regulerFont = FontLoader.loadFont("res/font/mainfont.ttf", 18);

        menuBG = ImageLoader.loadImage("/images/background.png");
        logoSTTS = ImageLoader.loadImage("/istts.png");

        loginBG = ImageLoader.loadImage("/images/loginBG.jpg");
    }
}
