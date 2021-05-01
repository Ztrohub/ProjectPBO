package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;
import project.pbo.gfx.Text;
import project.pbo.window.SIZE;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.IOException;

public class LoginState extends State implements SIZE {

    public LoginState(Handler handler) {
        super(handler);

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert clip != null;
            clip.open(Assets.audioLogin);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.loginBG, 0, 0, width, height, null);
        Text.drawString(g, "Username: ", 20, 200, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Password: ", 20, 280, false, Color.WHITE, Assets.regulerFont);
        g.setColor(Color.red);
        g.fillRect(280,370,110,50);
        g.fillRect(55,370, 150, 50 );
        Text.drawString(g, "Register", 75, 400, false, Color.WHITE, Assets.regulerFont);
        Text.drawString(g, "Login", 300, 400, false, Color.WHITE, Assets.regulerFont);

    }
}
