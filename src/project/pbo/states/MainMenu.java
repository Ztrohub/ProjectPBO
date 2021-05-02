package project.pbo.states;

import project.pbo.Handler;
import project.pbo.account.User;
import project.pbo.gfx.Assets;
import project.pbo.window.SIZE;

import javax.sound.sampled.Clip;
import java.awt.*;

public class MainMenu extends State implements SIZE {

    private User user;
    private final Clip clip;

    public MainMenu(Handler handler, User user) {
        super(handler);
        this.user = user;
        clip = Assets.audioMenu;
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        System.out.println("Masuk sebagai: " + user.getUsername());
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.menuBG, 0, 0, width, height, null);


    }
}
