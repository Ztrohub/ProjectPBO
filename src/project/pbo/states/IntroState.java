package project.pbo.states;

import project.pbo.Handler;
import project.pbo.gfx.Assets;

import javax.sound.sampled.Clip;
import java.awt.*;

public class IntroState extends State{

    private float alhpa = 0.1f;
    private int count = 0;
    private Clip clip;

    public IntroState(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {
        if (count < 100)
            alhpa += 0.01f;

        if (alhpa >= 1.0f){
            alhpa = 1.0f;
            if (count < 100)
            count++;
        }

        if (count >= 100)
            alhpa -= 0.01f;
        if (alhpa <= 0.0f){
            clip.stop();

            Assets.initLogin();
            State state = new LoginState(handler);
            state.playMusic();
            State.setCurrentState(state);
        }
    }

    @Override
    public void render(Graphics g) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alhpa));
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(Assets.logoSTTS, 400, 180, 250, 250, null);
    }

    @Override
    public void playMusic() {
        clip = Assets.audioIntro;
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
        handler.setVol(clip, 0.1f);
    }

    @Override
    public void loadFile() {

    }
}
