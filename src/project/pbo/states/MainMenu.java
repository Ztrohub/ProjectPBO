package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends State implements SIZE {

    private User user;
    private Clip clip;

    private final Rectangle overlayTop = new Rectangle(0, 0, width,65);
    private final Rectangle playBtn = new Rectangle(475, 128, 110, 50);

    // X BEDA 25
    // Y BEDA 31

    public MainMenu(Handler handler, User user) {
        super(handler);
        this.user = user;



        System.out.println("Masuk sebagai: " + user.getUsername());
    }

    public void playMusic(){
        clip = Assets.audioMenu;
        clip.start();
        handler.setVol(clip);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, width, height, null);
        g.drawImage(Assets.mainLogo, 296, 427, 450, 300, null);

        // OVERLAY TOP
        g.setColor(new Color(0x000000));
        ((Graphics2D) g).fill(overlayTop);

        // ICON DAN NAMA PLAYER
        g.drawImage(Assets.avatar, 14, 27, 30, 30, null);
        Text.drawString(g, "Logged in as :", 10, 20, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, user.getUsername(), 52, 49, false, Color.WHITE, Assets.regulerFont);

        // BUTTON PLAY
        g.setColor(new Color(0xB1B1B1));
        ((Graphics2D) g).fill(playBtn);
        Text.drawString(g, "Play", 500, 159, false, Color.BLACK, Assets.regulerFont);
    }

}
